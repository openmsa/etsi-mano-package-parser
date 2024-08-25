package com.ubiqube.parser.tosca.schema.generator.sol001.json.stmt;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConditionStmt implements Statement {

	private final Map<String, Object> properties = new LinkedHashMap<>();

	public ConditionStmt(final String key, final String value) {
		final Map<String, Object> type = new LinkedHashMap<>();
		type.put("const", value);
		final Map<String, Object> props = new LinkedHashMap<>();
		props.put(key, type);
		properties.put("properties", props);
	}

	@Override
	public Map<String, Object> generate() {
		return properties;
	}
}
