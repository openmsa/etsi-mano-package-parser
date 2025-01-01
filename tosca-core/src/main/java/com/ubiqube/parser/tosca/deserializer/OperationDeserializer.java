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
package com.ubiqube.parser.tosca.deserializer;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.ubiqube.parser.tosca.OperationDefinition;
import com.ubiqube.parser.tosca.OperationImplementationDefinition;
import com.ubiqube.parser.tosca.ToscaProperties;

public class OperationDeserializer extends StdDeserializer<OperationDefinition> {
	private static final Logger LOG = LoggerFactory.getLogger(OperationDeserializer.class);

	protected OperationDeserializer() {
		this(null);
	}

	public OperationDeserializer(final Class<?> object) {
		super(object);
	}

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Override
	public OperationDefinition deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final TreeNode value = p.getCodec().readTree(p);
		LOG.debug("value {}<=>{}", value.getClass(), value);
		if (value instanceof TextNode) {
			final OperationDefinition od = new OperationDefinition();
			final OperationImplementationDefinition oid = new OperationImplementationDefinition();
			oid.setPrimary(value.toString());
			od.setImplementation(oid);
			return od;
		}
		final OperationDefinition od = new OperationDefinition();
		final ObjectNode tn = (ObjectNode) value;
		Optional.ofNullable(tn.findValue("description")).ifPresent(x -> od.setDescription(x.asText()));
		DeserializerHelper.fillIfNeeded(p, tn, "implementation", OperationImplementationDefinition.class, od::setImplementation);
		DeserializerHelper.fillIfNeeded(p, tn, "inputs", ToscaProperties.class, od::setInputs);
		DeserializerHelper.fillIfNeeded(p, tn, "outputs", Map.class, od::setOutputs);
		return od;
	}
}
