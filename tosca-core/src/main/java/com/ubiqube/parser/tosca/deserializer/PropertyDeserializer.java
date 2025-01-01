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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubiqube.parser.tosca.ToscaProperties;
import com.ubiqube.parser.tosca.ValueObject;

public class PropertyDeserializer extends StdDeserializer<ToscaProperties> {

	public PropertyDeserializer() {
		this(null);
	}

	public PropertyDeserializer(final Class<?> object) {
		super(object);
	}

	/** serial. */
	private static final long serialVersionUID = 1L;

	@Override
	public ToscaProperties deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final HashMap<String, ValueObject> props = new HashMap<>();
		final ObjectNode value = p.getCodec().readTree(p);
		final Iterator<Entry<String, JsonNode>> fields = value.fields();
		while (fields.hasNext()) {
			final Map.Entry<String, JsonNode> entry = fields.next();
			final ValueObject vo = p.getCodec().treeToValue(entry.getValue(), ValueObject.class);
			props.put(entry.getKey(), vo);
		}
		final ToscaProperties toscaProperties = new ToscaProperties();
		toscaProperties.setProperties(props);
		return toscaProperties;
	}

}
