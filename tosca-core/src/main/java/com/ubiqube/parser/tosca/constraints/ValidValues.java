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
package com.ubiqube.parser.tosca.constraints;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ValidValues implements Constraint {
	private final List<String> values = new ArrayList<>();

	public ValidValues() {
		//
	}

	public ValidValues(final ArrayNode value) {
		for (JsonNode node : value) {
			values.add(node.asText());
		}
	}

	@Override
	public String toString() {
		return "" + values;
	}

	@Override
	public Object evaluate(final Object value) {
		return values.stream().anyMatch(x -> x.equals(value));
	}

	public List<String> getValues() {
		return values;
	}

}
