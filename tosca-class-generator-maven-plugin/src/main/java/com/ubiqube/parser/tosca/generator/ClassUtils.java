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
package com.ubiqube.parser.tosca.generator;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.ParseException;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
public final class ClassUtils {
	private static final Pattern PACKAGE_PATTERN = Pattern.compile("(?<package>.*)(?=\\.)\\.(?<clazz>.*)");

	private static final Logger LOG = LoggerFactory.getLogger(ClassUtils.class);

	private ClassUtils() {
		// Nothing.
	}

	public static String getClassName(final String key) {
		if (key.lastIndexOf('.') == -1) {
			return key;
		}
		final int pi = key.lastIndexOf('.');
		return key.substring(pi + 1);
	}

	public static String getPackage(final String key) {
		if (key.lastIndexOf('.') == -1) {
			return null;
		}
		final int pi = key.lastIndexOf('.');
		final String p = key.substring(0, pi).toLowerCase();
		return p.replaceFirst("\\.abstract", ".Abstract");
	}

	public static String toscaToJava(final String derivedFrom) {
		final Matcher m = PACKAGE_PATTERN.matcher(derivedFrom);
		if (m.matches()) {
			return m.group("package").toLowerCase(Locale.ROOT) + "." + m.group("clazz");
		}
		return derivedFrom;
	}

	public static Class<?> getClassOf(final String subType) {
		try {
			return Class.forName(subType);
		} catch (final ClassNotFoundException e) {
			throw new ParseException("Unknown subtype: " + subType, e);
		}
	}

	public static Class<?> getExistingClass(final String type) {
		try {
			return Class.forName(type);
		} catch (final ClassNotFoundException e) {
			LOG.trace("", e);
		}
		return null;
	}

}
