/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.parser.tosca.sol006;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.ubiqube.parser.tosca.sol006.statement.Statement;

public class KeywordToObject {
	private final Map<String, Class<? extends Statement>> yangStatements;

	public KeywordToObject() {
		yangStatements = new HashMap<>();
		final Reflections refl = new Reflections("com.ubiqube.parser.tosca.sol006.statement");
		final Set<Class<? extends Statement>> allClasses = refl.getSubTypesOf(Statement.class);
		for (final Class<? extends Statement> class1 : allClasses) {
			final Statement clazz = createClass(class1);
			final String yt = clazz.getYangName();
			yangStatements.put(yt, class1);
		}
	}

	public Class<? extends Statement> getImplementation(final String yangType) {
		return yangStatements.get(yangType);
	}

	private static Statement createClass(final Class<? extends Statement> x) {
		try {
			return x.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
}
