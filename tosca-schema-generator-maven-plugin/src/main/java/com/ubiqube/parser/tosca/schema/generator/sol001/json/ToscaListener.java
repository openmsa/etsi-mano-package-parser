package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.schema.generator.sol001.json.stmt.PropertyBlock;

public class ToscaListener {
	private static final String OBJECT = "object";
	private final ObjectMapper om = new ObjectMapper();
	private final Map<String, Object> root = new LinkedHashMap<>();
	private final Map<String, Object> rootProperties = new LinkedHashMap<>();
	private LinkedHashMap<String, PropertyBlock> defs;

	public ToscaListener() {
		om.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public void startDocument(final String title) {
		root.put("$schema", "https://json-schema.org/draft/2020-12/schema");
		root.put("type", OBJECT);
		root.put("title", title);
		root.put("description", title);
		root.put("properties", rootProperties);
		defs = new LinkedHashMap<>();
		root.put("$defs", defs);
		pushSimpleField(rootProperties, "tosca_definitions_version", "string");
		pushSimpleField(rootProperties, "description", "string");
		pushSimpleField(rootProperties, "repositories", OBJECT);
		pushSimpleField(rootProperties, "interface_types", OBJECT);
		pushSimpleField(rootProperties, "node_types", OBJECT);
		createImports();
	}

	private void createImports() {
		final String str = SchemaUtils.readString("imports-fragment.json");
		try {
			final PropertyBlock res = om.readValue(str.getBytes(), PropertyBlock.class);
			rootProperties.put("imports", res);
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static void pushSimpleField(final Map<String, Object> base, final String name, final String type) {
		final Field f = new Field();
		f.setType(type);
		base.put(name, f);
	}

	public void terminateDocument() {
		//
	}

	public PropertyBlock createObject(final String name) {
		final PropertyBlock f = new PropertyBlock();
		f.setType(OBJECT);
		rootProperties.put(name, f);
		return f;
	}

	public Map<String, Object> getRoot() {
		return root;
	}

	public String serialize() {
		try {
			return om.writeValueAsString(root);
		} catch (final JsonProcessingException e) {
			throw new ParseException(e);
		}
	}

	public Map<String, PropertyBlock> getDefs() {
		return defs;
	}

}
