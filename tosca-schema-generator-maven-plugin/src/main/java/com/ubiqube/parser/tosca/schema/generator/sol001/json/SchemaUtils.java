package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.IOException;
import java.io.InputStream;

import com.ubiqube.parser.tosca.ParseException;

public class SchemaUtils {
	private SchemaUtils() {
		//
	}

	public static String readString(final String classpath) {
		try (InputStream mapping = SchemaUtils.class.getClassLoader().getResourceAsStream(classpath)) {
			return new String(mapping.readAllBytes());
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

}
