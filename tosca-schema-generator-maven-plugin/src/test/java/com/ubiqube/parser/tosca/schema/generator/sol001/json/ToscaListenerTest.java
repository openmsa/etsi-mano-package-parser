package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.IOException;
import java.io.InputStream;
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

class ToscaListenerTest extends TestCase {

	@Test
	void testName2() throws Exception {
		final ToscaWalker tw = new ToscaWalker();
		final ToscaListener tl = new ToscaListener();
		tw.generate("/home/olivier/git/mano-root/etsi-mano-package-demo/vnf-full-tosca/Definitions/tosca_ubi.yaml", tl);
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
