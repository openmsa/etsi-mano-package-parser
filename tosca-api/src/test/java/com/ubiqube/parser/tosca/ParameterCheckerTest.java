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
package com.ubiqube.parser.tosca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.api.ToscaApi;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF;

class ParameterCheckerTest {

	private final ToscaApi toscaApi;

	public ParameterCheckerTest() throws MalformedURLException {
		toscaApi = ToscaApiFactory.createToscaApi();
	}

	@Test
	void testEmptyParams() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/input-test.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final Map<String, String> params = Map.of();
		final List<VNF> obj = toscaApi.getObjects(root, params, VNF.class);
		assertEquals(1, obj.size());
	}

	@Test
	void testBadParams() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/input-test.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final Map<String, String> params = Map.of("aaaaaa", "dddddd");
		assertThrows(NullPointerException.class, () -> {
			toscaApi.getObjects(root, params, VNF.class);
		});
	}

	@Test
	void testOneGoodNullBad() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/input-test.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final Map<String, String> params = new HashMap<>();
		params.put("descriptor_id", null);
		final List<VNF> obj = toscaApi.getObjects(root, params, VNF.class);
		assertEquals(1, obj.size());
		final VNF o = obj.get(0);
		assertEquals("FF39B25D-855D-8D3F-1FF6-03A23BDE63CB", o.getDescriptorId());
	}

	@Test
	void testOneBadParams() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/input-test.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final Map<String, String> params = new HashMap<>();
		params.put("flavour_id", "complex");
		assertThrows(ParseException.class, () -> toscaApi.getObjects(root, params, VNF.class));
	}

	@Test
	void testDoubleCorrectTest() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/input-test.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final Map<String, String> params = new HashMap<>();
		params.put("software_version", "1.0");
		final List<VNF> obj = toscaApi.getObjects(root, params, VNF.class);
		assertEquals(1, obj.size());
		final VNF o = obj.get(0);
		assertEquals("1.0", o.getSoftwareVersion());
	}

	@Test
	void testDoubleCorrectAsTextTest() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/input-test.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final Map<String, String> params = new HashMap<>();
		params.put("descriptor_version", "a");
		final List<VNF> obj = toscaApi.getObjects(root, params, VNF.class);
		assertEquals(1, obj.size());
		final VNF o = obj.get(0);
		assertEquals("1.0", o.getSoftwareVersion());
	}

	@Test
	void testDoubleInCorrectAsTextTest() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/input-test.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final Map<String, String> params = new HashMap<>();
		params.put("descriptor_version", "aaa");
		assertThrows(ParseException.class, () -> toscaApi.getObjects(root, params, VNF.class));
	}

}
