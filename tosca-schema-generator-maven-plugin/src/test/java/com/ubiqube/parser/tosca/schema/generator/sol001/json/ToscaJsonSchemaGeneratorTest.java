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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.networknt.schema.InputFormat;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.SchemaRegistryConfig;
import com.networknt.schema.SpecificationVersion;
import com.networknt.schema.serialization.NodeReader;

import junit.framework.TestCase;

class ToscaJsonSchemaGeneratorTest extends TestCase {

	void testName2() {
		final ToscaJsonSchemaWalker tw = new ToscaJsonSchemaWalker();
		final ToscaJsonSchemaGenerator tl = new ToscaJsonSchemaGenerator(Paths.get("."), "vnfd");
		tw.generate("/home/olivier/git/mano-root/etsi-mano-package-demo/vnf-full-tosca/Definitions/etsi_nfv_sol001_vnfd_types.yaml", tl);
	}

	@Test
	void testYaml() {
		final String schemaData = readString("test2.json");
		final String inputData = readString("vnfd.yaml");
		final SchemaRegistry factory = SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_2020_12,
				builder -> builder.nodeReader(NodeReader.builder().locationAware().build()));
		final SchemaRegistryConfig config = SchemaRegistryConfig.builder().build();
		final Schema schema = factory.getSchema(schemaData, InputFormat.JSON);
		final List<com.networknt.schema.Error> messages = schema.validate(inputData, InputFormat.YAML, executionContext -> {
			// executionContext.getExecutionConfig().setFormatAssertionsEnabled(true);
		});
		messages.forEach(x -> System.out.println(x));
	}

	@Test
	void testSmall() {
		final String schemaData = readString("schema-small3.json");
		final String inputData = readString("small.yaml");
		final SchemaRegistry factory = SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_2020_12,
				builder -> builder.nodeReader(NodeReader.builder().locationAware().build()));
		final SchemaRegistryConfig config = SchemaRegistryConfig.builder().build();
		final Schema schema = factory.getSchema(schemaData, InputFormat.JSON);
		final List<com.networknt.schema.Error> messages = schema.validate(inputData, InputFormat.YAML, executionContext -> {
//			executionContext.getExecutionConfig().setFormatAssertionsEnabled(true);
//			executionContext.getExecutionConfig().setDebugEnabled(true);
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
