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
