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

import java.util.List;

import com.ubiqube.parser.tosca.ParseException;

public class LessOrEqual extends SimpleValue implements Constraint {

	public LessOrEqual() {
		//
	}

	public LessOrEqual(final Object value) {
		super(value);
	}

	@Override
	public Object evaluate(final Object value) {
		if (value instanceof final Integer i) {
			return (Integer.valueOf(getValue().toString()) <= i);
		}
		if (value instanceof final Double d) {
			return (Double.valueOf(getValue().toString()) <= d);
		}
		if (value instanceof final List<?> l) {
			return l.size() <= Integer.valueOf(getValue().toString());
		}
		throw new ParseException("Could not evaluate inRange for type: " + value.getClass().getSimpleName());
	}

}
