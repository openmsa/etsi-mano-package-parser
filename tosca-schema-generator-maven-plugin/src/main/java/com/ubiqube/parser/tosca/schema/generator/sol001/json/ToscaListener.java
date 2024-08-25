package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.parser.tosca.schema.generator.sol001.json.stmt.PropertyBlock;

public class ToscaListener {
	private final ObjectMapper om = new ObjectMapper();
	private final Map<String, Object> root = new LinkedHashMap<>();
	private final Map<String, Object> rootProperties = new LinkedHashMap<>();

	public void startDocument() {
		root.put("$schema", "https://json-schema.org/draft/2020-12/schema");
		root.put("type", "object");
		root.put("title", "ETSI MANO VNFD schema");
		root.put("description", "ETSI MANO VNFD schema SOL001 4.5.1");
		root.put("properties", rootProperties);
		pushSimpleField(rootProperties, "tosca_definitions_version", "string");
		pushSimpleField(rootProperties, "description", "string");
		// "unevaluatedProperties": false,
	}

	private static void pushSimpleField(final Map<String, Object> base, final String name, final String type) {
		final Field f = new Field();
		f.setType(type);
		base.put(name, f);
	}

	public void terminateDocument() {
		// TODO Auto-generated method stub

	}

	public Field createArrayField(final String string, final String string2) {
		return null;
	}

	public PropertyBlock createObject(final String name) {
		final PropertyBlock f = new PropertyBlock();
		f.setType("object");
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
			throw new RuntimeException(e);
		}
	}
}
