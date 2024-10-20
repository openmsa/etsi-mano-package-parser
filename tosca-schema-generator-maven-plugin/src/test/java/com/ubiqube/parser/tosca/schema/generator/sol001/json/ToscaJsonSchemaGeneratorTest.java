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
package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.networknt.schema.InputFormat;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SchemaValidatorsConfig;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.serialization.JsonNodeReader;

import junit.framework.TestCase;

class ToscaJsonSchemaGeneratorTest extends TestCase {

	void testName2() throws Exception {
		final ToscaJsonSchemaWalker tw = new ToscaJsonSchemaWalker();
		final ToscaJsonSchemaGenerator tl = new ToscaJsonSchemaGenerator(Paths.get("."), "vnfd");
		tw.generate("/home/olivier/git/mano-root/etsi-mano-package-demo/vnf-full-tosca/Definitions/etsi_nfv_sol001_vnfd_types.yaml", tl);
	}

	@Test
	void testYaml() {
		final String schemaData = readString("test2.json");
		final String inputData = readString("vnfd.yaml");
		final JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V202012,
				builder -> builder.jsonNodeReader(JsonNodeReader.builder().locationAware().build()));
		final SchemaValidatorsConfig config = SchemaValidatorsConfig.builder().build();
		final JsonSchema schema = factory.getSchema(schemaData, InputFormat.JSON, config);
		final Set<ValidationMessage> messages = schema.validate(inputData, InputFormat.YAML, executionContext -> {
			executionContext.getExecutionConfig().setFormatAssertionsEnabled(true);
		});
		messages.forEach(x -> System.out.println(x));
	}

	@Test
	void testSmall() {
		final String schemaData = readString("schema-small3.json");
		final String inputData = readString("small.yaml");
		final JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V202012,
				builder -> builder.jsonNodeReader(JsonNodeReader.builder().locationAware().build()));
		final SchemaValidatorsConfig config = SchemaValidatorsConfig.builder().build();
		final JsonSchema schema = factory.getSchema(schemaData, InputFormat.JSON, config);
		final Set<ValidationMessage> messages = schema.validate(inputData, InputFormat.YAML, executionContext -> {
			executionContext.getExecutionConfig().setFormatAssertionsEnabled(true);
			executionContext.getExecutionConfig().setDebugEnabled(true);
		});
		System.out.println("-------------------");
		messages.forEach(x -> System.out.println(x));
	}

	String readString(final String classpath) {
		try (InputStream mapping = this.getClass().getClassLoader().getResourceAsStream(classpath)) {
			return new String(mapping.readAllBytes());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
