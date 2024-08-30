package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.CapabilityDefinition;
import com.ubiqube.parser.tosca.CapabilityTypes;
import com.ubiqube.parser.tosca.DataType;
import com.ubiqube.parser.tosca.GroupType;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.PolicyType;
import com.ubiqube.parser.tosca.ToscaClass;
import com.ubiqube.parser.tosca.ToscaClassHolder;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.ToscaParser;
import com.ubiqube.parser.tosca.ToscaProperties;
import com.ubiqube.parser.tosca.ValueObject;
import com.ubiqube.parser.tosca.constraints.Constraint;
import com.ubiqube.parser.tosca.constraints.Equal;
import com.ubiqube.parser.tosca.constraints.GreaterOrEqual;
import com.ubiqube.parser.tosca.constraints.GreaterThan;
import com.ubiqube.parser.tosca.constraints.InRange;
import com.ubiqube.parser.tosca.constraints.Length;
import com.ubiqube.parser.tosca.constraints.LessOrEqual;
import com.ubiqube.parser.tosca.constraints.MaxLength;
import com.ubiqube.parser.tosca.constraints.MinLength;
import com.ubiqube.parser.tosca.constraints.Pattern;
import com.ubiqube.parser.tosca.constraints.ValidValues;
import com.ubiqube.parser.tosca.schema.generator.sol001.json.stmt.PropertyBlock;

public class ToscaWalker {
	private static final Logger LOG = LoggerFactory.getLogger(ToscaWalker.class);
	private static final String BOOLEAN = "boolean";
	private static final String OBJECT = "object";
	private static final String STRING = "string";

	private ToscaContext root = null;
	private final Set<String> basic = Set.of(STRING, "integer", "scalar-unit.time", BOOLEAN, "version", "scalar-unit.size", "float", "scalar-unit.frequency", "range");
	private final Set<String> noAttributesNodes = new HashSet<>();

	public ToscaWalker() {
		//
	}

	public String generate(final String file, final ToscaListener listener) {
		final ToscaParser tp = new ToscaParser(new File(file));
		root = tp.getContext();
		listener.startDocument(root.getDescription());
		handleTopologyTemplate(listener);
		handlePolicies(listener);
		handleGroupType(listener);
		listener.terminateDocument();
		return listener.serialize();
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
		final PropertyBlock grp = listener.createObject("policies");
		final PropertyBlock pattern = createDefaultFields();
		final Map<String, PropertyBlock> defs = listener.getDefs();
		grp.setPatternProperties(Map.of("^", pattern));
		final Map<String, PolicyType> grd = root.getPoliciesType();
		final List<PropertyBlock> allOf = grd.entrySet().stream().map(x -> {
			final PolicyType policyType = x.getValue();
			final String k = x.getKey();
			final ToscaProperties props = policyType.getProperties();
			final PropertyBlock res = Optional.ofNullable(handleProperties(props)).orElseGet(() -> {
				final PropertyBlock ret = new PropertyBlock();
				ret.setProperties(new LinkedHashMap<>());
				return ret;
			});
			final Map<String, PropertyBlock> parent = handleDerivedFrom(policyType.getDerivedFrom());
			res.getProperties().putAll(parent);
			final PropertyBlock props2 = new PropertyBlock();
			props2.setProperties(Map.of("properties", res));
			defs.put(k, props2);
			return createIfBlock(k, res);
		}).toList();
		pattern.setAllOf(allOf);
	}

	private static PropertyBlock createIfBlock(final String type, final PropertyBlock props) {
		final PropertyBlock ifSmt = createIf(type);
		final PropertyBlock nw = new PropertyBlock();
		nw.setIfSmt(ifSmt);
		props.setType(OBJECT);
		final PropertyBlock ref = new PropertyBlock();
		ref.setRef("#/$defs/%s".formatted(type));
		nw.setThen(ref);
		return nw;
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
		final List<String> mandatories = pb.getProperties().entrySet().stream()
				.filter(x -> x.getValue().isMandatory())
				.map(Entry::getKey).toList();
		if (!mandatories.isEmpty()) {
			pb.setRequired(mandatories);
		}
		return pb;
	}

	private PropertyBlock handleProperty(final String k, final ValueObject v) {
		final PropertyBlock pb = new PropertyBlock();
		final String type = v.getType();
		if ("map".equals(type)) {
			pb.setType(OBJECT);
			final String tm = v.getEntrySchema().getType();
			final PropertyBlock pattern = handleContainer(tm);
			pattern.setDescription(v.getDescription());
			pb.setPatternProperties(Map.of("^", pattern));
		} else if ("list".equals(type)) {
			pb.setType("array");
			final String tm = v.getEntrySchema().getType();
			final PropertyBlock pattern = handleContainer(tm);
			pattern.setDescription(v.getDescription());
			pb.setItems(pattern);
		} else if (isBasic(type)) {
			pb.setType(convertType(type));
			applyConstraints(pb, v.getConstraints());
			pb.setDescription(v.getDescription());
		} else {
			final DataType res = lookupToscaClass(type);
			final ToscaProperties p2 = res.getProperties();
			final PropertyBlock rin = handleProperties(p2);
			if (null != rin) {
				pb.setProperties(rin.getProperties());
				pb.setRequired(rin.getRequired());
				pb.setDescription(v.getDescription());
			}
		}
		pb.setMandatory(v.getRequired());
		return pb;
	}

	private static void applyConstraints(final PropertyBlock pb, final List<Constraint> constraints) {
		constraints.forEach(x -> {
			switch (x) {
			case final Length l -> {
				pb.setMinLength((Integer) l.getValue());
				pb.setMaxLength((Integer) l.getValue());
			}
			case final MinLength ml -> pb.setMinLength((Integer) ml.getValue());
			case final MaxLength ml -> pb.setMaxLength((Integer) ml.getValue());
			case final Pattern p -> pb.setPattern((String) p.getValue());
			case final LessOrEqual loe -> {
//				pb.setMinimum(Integer.valueOf(goe.getValue().toString()));
			}
			case final GreaterOrEqual goe -> {
//				pb.setMinimum(Integer.valueOf(goe.getValue().toString()));
			}
			case final GreaterThan gt -> {
				//
			}
			case final InRange ir -> {
//				pb.setMinimum(Integer.valueOf(ir.getMin()));
//				pb.setMaximum(Integer.valueOf(ir.getMax()));
			}
			case final ValidValues vv -> pb.setEnumStmt(vv.getValues());
			case final Equal e -> {
				//
			}
			default -> throw new ParseException("Unknown constraint " + x.getClass().getSimpleName());
			}
		});
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
		case STRING -> STRING;
		case BOOLEAN -> BOOLEAN;
		case "scalar-unit.time" -> STRING;
		case "integer" -> "number";
		case "version" -> "string";
		case "scalar-unit.size" -> "string";
		case "scalar-unit.frequency" -> "string";
		case "float" -> "number";
		case "range" -> "string";
		default -> throw new IllegalArgumentException("Unexpected value: " + type);
		};
	}

	private boolean isBasic(final String type) {
		return basic.contains(type);
	}

	private void handleTopologyTemplate(final ToscaListener listener) {
		final Map<String, PropertyBlock> defs = listener.getDefs();
		final PropertyBlock tt = listener.createObject("topology_template");
		createTopologyFields(tt);
		final PropertyBlock grp = tt.getProperties().get("node_templates");
		final PropertyBlock pattern = createDefaultFields();
		grp.setPatternProperties(Map.of("^", pattern));
		final Map<String, ToscaClass> grd = root.getNodeType();
		final List<PropertyBlock> allOf = grd.entrySet().stream().map(x -> {
			LOG.info("✳️ Derived from: {}", x.getValue().getDerivedFrom());
			final ToscaClass toscaClass = x.getValue();
			final String type = x.getKey();
			final ToscaProperties props = toscaClass.getProperties();
			final PropertyBlock res = Optional.ofNullable(handleProperties(props)).orElseGet(() -> {
				final PropertyBlock ret = new PropertyBlock();
				ret.setProperties(new LinkedHashMap<>());
				return ret;
			});
			final Map<String, PropertyBlock> parent = handleDerivedFrom(toscaClass.getDerivedFrom());
			res.getProperties().putAll(parent);
			final PropertyBlock props2 = new PropertyBlock();
			final Map<String, PropertyBlock> lvl0 = new LinkedHashMap<>();
			lvl0.put("properties", res);
			props2.setProperties(lvl0);
			// Capabilities
			final Map<String, CapabilityDefinition> caps = toscaClass.getCapabilities();
			final PropertyBlock capabilities = createCapabilities(caps);
			if (null != capabilities) {
				props2.getProperties().put("capabilities", capabilities);
			}
			defs.put(type, props2);
			return createIfBlock(type, res);
		}).toList();
		pattern.setAllOf(allOf);
	}

	private PropertyBlock createCapabilities(final Map<String, CapabilityDefinition> caps) {
		if (null == caps) {
			return null;
		}
		final PropertyBlock ret = new PropertyBlock();
		final Map<String, PropertyBlock> map = caps.entrySet().stream().map(x -> {
			final CapabilityDefinition capDef = x.getValue();
			final CapabilityTypes cap = root.getCapabilities().get(capDef.getType());
			final ToscaProperties props = cap.getProperties();
			final PropertyBlock res = handleProperties(props);
			if (res == null) {
				return null;
			}
			return Map.entry(x.getKey(), res);
		})
				.filter(x -> x != null)
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		ret.setProperties(map);
		return ret;
	}

	private Map<String, PropertyBlock> handleDerivedFrom(final String derivedFrom) {
		final ToscaClassHolder res = root.getChildOf(derivedFrom);
		if (null == res) {
			return Map.of();
		}
		final ToscaClass node = res.getNode();
		final ToscaProperties props = node.getProperties();
		if (null == props) {
			warnUserOnce(derivedFrom);
			return Map.of();
		}
		final Map<String, PropertyBlock> propBlock = props.getProperties().entrySet().stream()
				.map(x -> Map.entry(x.getKey(), handleProperty(x.getKey(), x.getValue())))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		final ToscaClassHolder parent = res.getParent();
		if (null == parent) {
			return propBlock;
		}
		final Map<String, PropertyBlock> parentRes = handleDerivedFrom(parent.getName());
		final Map<String, PropertyBlock> all = new LinkedHashMap<>(parentRes);
		all.putAll(propBlock);
		return all;
	}

	private void warnUserOnce(final String derivedFrom) {
		if (noAttributesNodes.contains(derivedFrom)) {
			return;
		}
		LOG.warn("🟡 No properties on: {}", derivedFrom);
		noAttributesNodes.add(derivedFrom);
	}

	private static void createTopologyFields(final PropertyBlock grp) {
		grp.setProperties(new LinkedHashMap<>());
		final PropertyBlock inputs = PropertyBlock.ofType(OBJECT);
		grp.getProperties().put("inputs", inputs);
		final PropertyBlock nodeTemplates = PropertyBlock.ofType(OBJECT);
		grp.getProperties().put("node_templates", nodeTemplates);
	}

	private static PropertyBlock createDefaultFields() {
		final PropertyBlock pattern = new PropertyBlock();
		final PropertyBlock sp = new PropertyBlock();
		sp.setType("string");
		final Map<String, PropertyBlock> map = new LinkedHashMap<>();
		map.put("type", sp);
		pattern.setProperties(map);
		pattern.setRequired(List.of("type"));
		return pattern;
	}

}
