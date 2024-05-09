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

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import com.ubiqube.parser.test.ArtifactDownloader;
import com.ubiqube.parser.tosca.api.ToscaApi;
import com.ubiqube.parser.tosca.api.ToscaMapper;
import com.ubiqube.parser.tosca.scalar.Version;

public class ToscaApiFactory {
	private static final String JAR_PATH_JDK = "/tosca-class-%s-2.0.0-SNAPSHOT.jar";

	private ToscaApiFactory() {
		//
	}

	static ToscaApi createToscaApi() throws MalformedURLException {
		final Version v = new Version("3.6.1");
		ArtifactDownloader.prepareArtifact(toJarVersions(v));
		final URL cls = ToscaApiFactory.class.getResource(String.format(JAR_PATH_JDK, toJarVersions(v)));
		final URLClassLoader inst = URLClassLoader.newInstance(new URL[] { cls }, ToscaApiFactory.class.getClassLoader());
		final ToscaMapper tm = getVersionedMapperMethod(inst);
		return new ToscaApi(inst, tm);
	}

	private static String toJarVersions(final Version v) {
		return v.toString().replace(".", "");
	}

	private static ToscaMapper configureMapper(final URLClassLoader inst) throws SecurityException {
		Thread.currentThread().setContextClassLoader(inst);
		return getVersionedMapperMethod(inst);
	}

	private static ToscaMapper getVersionedMapperMethod(final URLClassLoader urlLoader) {
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
