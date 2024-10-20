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
package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.IOException;
import java.io.InputStream;

import com.ubiqube.parser.tosca.ParseException;

public class SchemaUtils {
	private SchemaUtils() {
		//
	}

	public static String readString(final String classpath) {
		try (InputStream mapping = SchemaUtils.class.getClassLoader().getResourceAsStream(classpath)) {
			return new String(mapping.readAllBytes());
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

}
