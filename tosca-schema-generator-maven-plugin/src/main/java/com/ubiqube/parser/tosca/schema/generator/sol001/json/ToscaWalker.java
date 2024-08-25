package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.DataType;
import com.ubiqube.parser.tosca.GroupType;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.PolicyType;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.ToscaParser;
import com.ubiqube.parser.tosca.ToscaProperties;
import com.ubiqube.parser.tosca.ValueObject;
import com.ubiqube.parser.tosca.schema.generator.sol001.json.stmt.PropertyBlock;

public class ToscaWalker {
	private static final String TOSCA_ARTIFACTS_ROOT = "tosca.artifacts.Root";
	private static final String TOSCA_INTERFACE_ROOT = "tosca.interfaces.Root";
	private static final String TOSCA_NODES_ROOT = "tosca.nodes.Root";
	private static final String TOSCA_POLICIES_ROOT = "tosca.policies.Root";
	private static final String STRING = "string";

	private static final Logger LOG = LoggerFactory.getLogger(ToscaWalker.class);
	private ToscaContext root = null;
	private final Map<String, DataType> primitive = new HashMap<>();
	private final Set<String> cache = new HashSet<>();
	private final Set<String> basic = Set.of("string", "integer", "scalar-unit.time", "boolean");

	public ToscaWalker() {
		//
	}

	public void generate(final String file, final ToscaListener listener) {
		final ToscaParser tp = new ToscaParser(new File(file));
		root = tp.getContext();

		listener.startDocument();
		handleImport(listener);
		handleTopologyTemplate(listener);
		handlePolicies(listener);
		handleGroupType(listener);
		listener.terminateDocument();
		final Object res = listener.serialize();
		System.out.println(res);

	}

	private void handleGroupType(final ToscaListener listener) {
		final PropertyBlock grp = listener.createObject("groups");

		final Map<String, GroupType> grd = root.getGroupType();
		grd.forEach((k, v) -> {
			v.getDerivedFrom();
			final ToscaProperties props = v.getProperties();
			// System.out.println(props);
		});

	}

	private void handlePolicies(final ToscaListener listener) {
		final Map<String, Object> schemaRoot = listener.getRoot();
		final PropertyBlock grp = listener.createObject("policies");
		final PropertyBlock pattern = new PropertyBlock();
//		pattern.setType("object");
		final PropertyBlock sp = new PropertyBlock();
		sp.setType("string");
		final Map<String, PropertyBlock> map = new LinkedHashMap<>();
		map.put("type", sp);
		pattern.setProperties(map);
		pattern.setRequired(List.of("type"));
		final Map<String, PropertyBlock> defs = new LinkedHashMap<>();
		schemaRoot.put("$defs", defs);
		grp.setPatternProperties(Map.of("^", pattern));
		final Map<String, PolicyType> grd = root.getPoliciesType();
		final List<PropertyBlock> allOf = grd.entrySet().stream().map(x -> {
			final PolicyType v = x.getValue();
			final String k = x.getKey();
			final ToscaProperties props = v.getProperties();
			final PropertyBlock res = handleProperties(props);
			if (res == null) {
				System.err.println("Res is null");
				return new PropertyBlock();
			}
			final PropertyBlock ifSmt = createIf(k);
			final PropertyBlock nw = new PropertyBlock();
			nw.setIfSmt(ifSmt);
			final PropertyBlock props2 = new PropertyBlock();
			res.setType("object");
			props2.setProperties(Map.of("properties", res));
			final PropertyBlock ref = new PropertyBlock();
			ref.setRef("#/$defs/%s".formatted(k));
			nw.setThen(ref);
			defs.put(k, props2);
			return nw;
		}).toList();
		pattern.setAllOf(allOf);
	}

	private static void addDefaultField(final PropertyBlock pattern) {
		final PropertyBlock target = new PropertyBlock();
		target.setType("array");
		final PropertyBlock item = new PropertyBlock();
		item.setType("string");
		target.setItems(item);
		pattern.getProperties().put("targets", target);
		//
		final PropertyBlock object = new PropertyBlock();
		object.setType("object");
		pattern.getProperties().put("triggers", object);
	}

	private static PropertyBlock createIf(final String k) {
		final PropertyBlock ifSm = new PropertyBlock();
		final PropertyBlock cnst = new PropertyBlock();
		cnst.setCnst(k);
		final Map<String, PropertyBlock> map = new LinkedHashMap<>();
		map.put("type", cnst);
		ifSm.setProperties(map);
		return ifSm;
	}

	private PropertyBlock handleProperties(final ToscaProperties props) {
		if (null == props) {
			return null;
		}
		final PropertyBlock pb = new PropertyBlock();
		pb.setProperties(new LinkedHashMap<>());
		pb.setAdditionalProperties(false);
		props.getProperties().forEach((k, v) -> {
			final PropertyBlock res = handleProperty(k, v);
			pb.getProperties().put(k, res);
		});
		final List<String> mandatries = pb.getProperties().entrySet().stream()
				.filter(x -> x.getValue().isMandatory())
				.map(Entry::getKey).toList();
		if (!mandatries.isEmpty()) {
			pb.setRequired(mandatries);
		}
		return pb;
	}

	private PropertyBlock handleProperty(final String k, final ValueObject v) {
		final PropertyBlock pb = new PropertyBlock();
		final String type = v.getType();
		if ("map".equals(type)) {
			pb.setType("object");
			final String tm = v.getEntrySchema().getType();
			final PropertyBlock pattern = handleContainer(tm);
			pb.setPatternProperties(Map.of("^", pattern));
		} else if ("list".equals(type)) {
			pb.setType("array");
			final String tm = v.getEntrySchema().getType();
			final PropertyBlock pattern = handleContainer(tm);
			pb.setItems(pattern);
		} else if (isBasic(type)) {
			pb.setType(convertType(type));
		} else {
			final DataType res = lookupToscaClass(type);
			final ToscaProperties p2 = res.getProperties();
			final PropertyBlock rin = handleProperties(p2);
			pb.setProperties(rin.getProperties());
		}
		pb.setMandatory(v.getRequired());
		return pb;
	}

	private PropertyBlock handleContainer(final String type) {
		final PropertyBlock pb = new PropertyBlock();
		pb.setAdditionalProperties(false);
		if ("map".equals(type) || ("list".equals(type))) {
			throw new ParseException("Nesteed container.");
		}
		if (isBasic(type)) {
			pb.setType(convertType(type));
		} else {
			final DataType res = lookupToscaClass(type);
			final ToscaProperties p2 = res.getProperties();
			final PropertyBlock rout = handleProperties(p2);
			pb.setProperties(rout.getProperties());
		}
		return pb;
	}

	private DataType lookupToscaClass(final String type) {
		final DataType res = root.getDataTypes().get(type);
		if (null == res) {
			throw new ParseException("Unable to find tosca type: " + type);
		}
		return res;
	}

	private static String convertType(final String type) {
		return switch (type) {
		case "string" -> "string";
		case "boolean" -> "boolean";
		case "scalar-unit.time" -> "string";
		case "integer" -> "number";
		default -> throw new IllegalArgumentException("Unexpected value: " + type);
		};
	}

	private boolean isBasic(final String type) {
		return basic.contains(type);
	}

	private void handleTopologyTemplate(final ToscaListener listener) {
		// TODO Auto-generated method stub

	}

	private void handleImport(final ToscaListener listener) {
		final Field f = listener.createArrayField("imports", "string");

	}

}
