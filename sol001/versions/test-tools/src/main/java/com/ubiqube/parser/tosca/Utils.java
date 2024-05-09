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
/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.parser.tosca;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.ubiqube.parser.tosca.api.ToscaMapper;

public class Utils {

	private Utils() {
		// Nothing.
	}

	public static ToscaMapper createMapperFactory() {
		return registerMapper();
	}

	private static ToscaMapper registerMapper() {
		final ClassLoader urlLoader = Utils.class.getClassLoader();
		try (InputStream is = urlLoader.getResourceAsStream("META-INF/tosca-resources.properties")) {
			final Properties props = new Properties();
			props.load(is);
			final Class<?> clz = urlLoader.loadClass(props.getProperty("mapper"));
			return (ToscaMapper) clz.getDeclaredConstructor().newInstance();
		} catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | IOException e) {
			throw new ParseException(e);
		}
	}

}
