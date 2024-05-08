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
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.parser.tosca;

import java.beans.IntrospectionException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.sol001.v441.mapper.ArtifactMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.CapabilityMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.GroupMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.InterfaceMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.NodeMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.PolicyMapper;

class ReflectionTest {

	@Test
	void testName() throws Exception {
		inspectClass(ArtifactMapper.class, "artifactMapper");
		inspectClass(CapabilityMapper.class, "capabilityMApper");
		inspectClass(GroupMapper.class, "groupMapper");
		inspectClass(InterfaceMapper.class, "interfaceMapper");
		inspectClass(NodeMapper.class, "nodeMapper");
		inspectClass(PolicyMapper.class, "policyMapper");
	}

	void inspectClass(final Class<?> clazz, final String object) throws IntrospectionException {
		final Method[] meths = clazz.getMethods();
		for (final Method method : meths) {
			System.out.println("case \"" + method.getReturnType().getCanonicalName() + "\" -> " + object + "." + method.getName() + "((" + method.getParameters()[0].getType().getCanonicalName() + ")arg);");
		}
	}

	void a(final String c) {
		switch (c) {
		case "ee": {

			//
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + c);
		}
	}
}
