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

import com.ubiqube.parser.tosca.sol006.ir.IrStatement;
import com.ubiqube.parser.tosca.sol006.statement.Statement;

public class IrVisitor {

	private final KeywordToObject kto = new KeywordToObject();

	public void visit(final IrStatement stmt) {
		final Class<? extends Statement> cls = kto.getImplementation(stmt.getKeyword().identifier());
		if (null == cls) {
			System.out.println(" =>" + stmt.getKeyword().identifier());
		}
		stmt.getStatements().forEach(this::visit);
	}
}
