/**
 *     Copyright (C) 2019-2024 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see https://www.gnu.org/licenses/.
 */
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
