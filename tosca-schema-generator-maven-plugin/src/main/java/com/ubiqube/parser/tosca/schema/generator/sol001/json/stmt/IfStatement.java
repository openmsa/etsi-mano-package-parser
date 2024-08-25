package com.ubiqube.parser.tosca.schema.generator.sol001.json.stmt;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class IfStatement implements Statement {
	private ConditionStmt condition;
	private ThenStmt then;
	private Statement elseStmt;

	@Override
	public Map<String, Object> generate() {
		final Map<String, Object> ret = new LinkedHashMap<>();
		ret.put("if", condition.generate());
		ret.put("then", then.generate());
		return ret;
	}
}
