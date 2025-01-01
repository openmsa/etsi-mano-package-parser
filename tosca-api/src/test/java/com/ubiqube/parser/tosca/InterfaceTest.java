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

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.api.ContextResolver;
import com.ubiqube.parser.tosca.api.ToscaApi;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF;

class InterfaceTest {

	private final ToscaApi toscaApi;

	public InterfaceTest() throws MalformedURLException {
		toscaApi = ToscaApiFactory.createToscaApi();
	}

	@Test
	void testInterfaces() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/interfaces.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final List<VNF> obj = toscaApi.getObjects(root, new HashMap<>(), VNF.class);
		final ContextResolver ctx = new ContextResolver(root, new HashMap<>());
		ctx.resolvValue("");
		assertEquals(1, obj.size());
	}

	@Test
	void testInputs() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/input-test.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final List<VNF> obj = toscaApi.getObjects(root, new HashMap<>(), VNF.class);
		final Map<String, String> params = Map.of("descriptor_id", "FF39B25D-855D-8D3F-1FF6-03A23BDE63CB");
		final ContextResolver ctx = new ContextResolver(root, params);
		ctx.resolvValue("");
		assertEquals(1, obj.size());
	}
}
