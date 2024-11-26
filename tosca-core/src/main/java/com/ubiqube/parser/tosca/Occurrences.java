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
package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.OccurrencesDeserializer;

import lombok.Getter;
import lombok.Setter;

/**
 * implied default of [1,UNBOUNDED]
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@Setter
@Getter
@JsonDeserialize(using = OccurrencesDeserializer.class)
public class Occurrences {

	private int max;
	private int min;

	public Occurrences(final int _min, final int _max) {
		min = _min;
		max = _max;
	}

	@Override
	public String toString() {
		final Object m = (max == Integer.MAX_VALUE) ? "UNBOUNDED" : max;
		return "[" + min + ", " + m + "]";
	}
}
