/**
 * Copyright (C) 2019-2025 Ubiqube.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.parser.tosca.constraints;

import com.fasterxml.jackson.databind.JsonNode;

public class Pattern extends SimpleValue implements Constraint {
	private java.util.regex.Pattern p;

	public Pattern() {
		//
	}

	public Pattern(final JsonNode value) {
		super(value.asText());
		p = java.util.regex.Pattern.compile(value.asText());
	}

	@Override
	public Object evaluate(final Object value) {
		return p.matcher(value.toString()).find();
	}

}
