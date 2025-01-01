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
package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.schema.generator.sol001.json.stmt.PropertyBlock;

public class ToscaJsonSchemaGenerator {
	private static final String OBJECT = "object";
	private final ObjectMapper om = new ObjectMapper();
	private final Map<String, Object> root = new LinkedHashMap<>();
	private final Map<String, Object> rootProperties = new LinkedHashMap<>();
	private LinkedHashMap<String, PropertyBlock> defs;
	private String version;
	private String type;
	private final Path target;

	public ToscaJsonSchemaGenerator(final Path target, final String type) {
		this.target = target;
		this.type = type;
		om.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public void startDocument(final String title) {
		root.put("$schema", "https://json-schema.org/draft/2020-12/schema");
		root.put("type", OBJECT);
		root.put("title", title);
		root.put("description", title);
		root.put("properties", rootProperties);
		defs = new LinkedHashMap<>();
		root.put("$defs", defs);
		pushSimpleField(rootProperties, "tosca_definitions_version", "string");
		pushSimpleField(rootProperties, "description", "string");
		pushSimpleField(rootProperties, "repositories", OBJECT);
		pushSimpleField(rootProperties, "interface_types", OBJECT);
		pushSimpleField(rootProperties, "node_types", OBJECT);
		createImports();
	}

	private void createImports() {
		final String str = SchemaUtils.readString("imports-fragment.json");
		try {
			final PropertyBlock res = om.readValue(str.getBytes(), PropertyBlock.class);
			rootProperties.put("imports", res);
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static void pushSimpleField(final Map<String, Object> base, final String name, final String type) {
		final Field f = new Field();
		f.setType(type);
		base.put(name, f);
	}

	public void terminateDocument() {
		//
	}

	public PropertyBlock createObject(final String name) {
		final PropertyBlock f = new PropertyBlock();
		f.setType(OBJECT);
		rootProperties.put(name, f);
		return f;
	}

	public Map<String, Object> getRoot() {
		return root;
	}

	public void serialize() {
		final Path finalDst = Paths.get(target.toString(), version);
		final File dir = finalDst.toFile();
		dir.mkdirs();
		final File df = new File(dir, "schema-%s.json".formatted(type));
		try (FileOutputStream fos = new FileOutputStream(df)) {
			final String str = om.writeValueAsString(root);
			fos.write(str.getBytes());
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	public Map<String, PropertyBlock> getDefs() {
		return defs;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
