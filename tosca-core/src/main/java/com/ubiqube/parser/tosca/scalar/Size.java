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

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.ParseException;

public class Size implements Scalar {
	private static final Logger LOG = LoggerFactory.getLogger(Size.class);

	private static final Pattern SIZE_PATTERN = Pattern.compile("(?<size>\\d+)\\s*(?<unit>[a-zA-Z]+)");

	private final long lsize;

	private final String unit;

	public Size(final String value) {
		LOG.debug("Size value: {}", value);

		final Matcher m = SIZE_PATTERN.matcher(value);
		if (!m.find()) {
			throw new ParseException("Size scalr: Unable to find a match for: " + value);
		}
		lsize = Long.parseLong(m.group("size"));
		unit = m.group("unit");
	}

	public Size(final Long value) {
		lsize = value.longValue();
		unit = "b";
	}

	public String getToscaForm() {
		return lsize + " " + unit;
	}

	public BigDecimal getValue() {
		final BigDecimal mul = getMultiplier(unit);
		final BigDecimal sizeBd = new BigDecimal(lsize);
		return mul.multiply(sizeBd);
	}

	public static BigDecimal getMultiplier(final String unit2) {
		return switch (unit2.toLowerCase()) {
		case "b" -> BigDecimal.ONE;
		case "kb" -> new BigDecimal("1000");
		case "kib" -> new BigDecimal("1024");
		case "mb" -> new BigDecimal("1000000");
		case "mib" -> new BigDecimal("1048576");
		case "gb" -> new BigDecimal("1000000000");
		case "gib" -> new BigDecimal("1073741824");
		case "tb" -> new BigDecimal("1000000000000");
		case "tib" -> new BigDecimal("1099511627776");
		default -> throw new ParseException("Unknown scalar unit " + unit2);
		};
	}
}
