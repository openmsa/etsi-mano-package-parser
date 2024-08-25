package com.ubiqube.parser.tosca.schema.generator.sol001.json.stmt;

import java.util.Map;

import com.ubiqube.parser.tosca.schema.generator.sol001.json.Field;

import lombok.Data;

@Data
public class ThenStmt implements Statement {
	Map<String, Field> properties;

	@Override
	public Map<String, Object> generate() {
		return Map.of("properties", properties);
	}

}
