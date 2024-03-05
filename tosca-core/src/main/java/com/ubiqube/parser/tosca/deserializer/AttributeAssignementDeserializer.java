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
package com.ubiqube.parser.tosca.deserializer;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubiqube.parser.tosca.AttributeAssignement;
import com.ubiqube.parser.tosca.ParseException;

public class AttributeAssignementDeserializer extends StdDeserializer<AttributeAssignement> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public AttributeAssignementDeserializer() {
		this(null);
	}

	protected AttributeAssignementDeserializer(final Class<?> vc) {
		super(vc);
	}

	@Override
	public AttributeAssignement deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final ObjectNode value = p.getCodec().readTree(p);
		final JsonNode val = value.get("value");
		if (null != val) {
			final AttributeAssignement aa = new AttributeAssignement();
			aa.setValue(paseValue(val));
			aa.setDescription(value.get("description").asText());
			return aa;
		}
		final AttributeAssignement aa = new AttributeAssignement();
		aa.setValue(paseValue(value));
		return aa;
	}

	private static List<String> paseValue(final JsonNode val) {
		final JsonNode attr = val.get("get_attribute");
		if ((null != attr) && (attr instanceof final ArrayNode an)) {
			return StreamSupport.stream(an.spliterator(), false)
					.map(x -> x.asText())
					.toList();
		}
		throw new ParseException("Unknown attributes function " + val);
	}

}
