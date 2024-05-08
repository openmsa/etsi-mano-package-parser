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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.test.ArtifactDownloader;
import com.ubiqube.parser.tosca.api.ContextResolver;
import com.ubiqube.parser.tosca.api.ToscaApi;
import com.ubiqube.parser.tosca.api.ToscaMapper;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF;
import com.ubiqube.parser.tosca.scalar.Version;

import ma.glasnost.orika.OrikaSystemProperties;

/**
 *
 * @author olivier
 *
 */
class MappingClassTest {
	private static final String JAR_PATH_JDK = "/tosca-class-%s-2.0.0-SNAPSHOT.jar";

	@Test
	void testName() throws Exception {
		System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES, "true");
		System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES_TO_PATH, "/tmp/orika-tosca");
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/ubi-tosca/Definitions/tosca_ubi.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		Version v = getVersion(root.getMetadata());
		v = new Version("3.6.1");
		ArtifactDownloader.prepareArtifact(toJarVersions(v));
		final URL cls = this.getClass().getResource(String.format(JAR_PATH_JDK, toJarVersions(v)));
		System.out.println("" + cls);
		final URLClassLoader inst = URLClassLoader.newInstance(new URL[] { cls }, this.getClass().getClassLoader());
		final ContextResolver ctx = new ContextResolver(root, new HashMap<>());
		final ToscaMapper mapperFactory = configureMapper(inst);
		final ToscaApi toscaApi = new ToscaApi(inst, mapperFactory);
		final Class<?> clz = inst.loadClass("tosca.nodes.nfv.VNF");
		assertNotNull(clz);
		final List<VNF> objs = toscaApi.getObjects(root, Map.of(), VNF.class);
		final List<?> fin = objs.stream().map(x -> mapperFactory.map(x, Object.class)).toList();
		assertNotNull(fin);
		assertEquals(1, fin.size());
		final Object vnf = fin.get(0);
		assertNotNull(vnf);
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

	private static String toJarVersions(final Version v) {
		return v.toString().replace(".", "");
	}

	private static Version getVersion(final Map<String, String> metadata) {
		final String author = metadata.get("template_author");
		if (null == author) {
			return new Version("2.5.1");
		}
		return Optional.ofNullable(metadata.get("template_version"))
				.map(Version::new)
				.orElseGet(() -> new Version("2.5.1"));
	}
}
