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
package com.ubiqube.parser.tosca.scalar;

import com.ubiqube.parser.tosca.ParseException;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@Setter
@Getter
public class Range implements Scalar {
	long min;
	long max;

	public Range(final String value) {
		final String[] arr = value.split("-");
		if (arr.length != 2) {
			throw new ParseException("Range must have two element " + value);
		}
		min = Long.parseLong(arr[0].trim());
		max = Long.parseLong(arr[1].trim());
	}

	@Override
	public String toString() {
		return min + " - " + max;
	}

}
