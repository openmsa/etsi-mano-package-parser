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
package com.ubiqube.parser.tosca.sol006.statement;

import java.util.List;

import com.ubiqube.parser.tosca.sol006.ir.IrStatement;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@Getter
@Setter
public class BelongsToStatement extends AbstractStatementImpl {

	private String name;
	private String prefix;

	@Override
	public String getYangName() {
		return "belongs-to";
	}

	@Override
	public void load(final IrStatement res) {
		name = res.getArgument().toString();
		final List<IrStatement> stmts = res.getStatements();
		stmts.forEach(x -> doSwitch(x));
	}

	private void doSwitch(final IrStatement x) {
		switch (x.getKeyword().identifier()) {
		case "prefix" -> prefix = x.getArgument().toString();
		default -> throw new IllegalArgumentException(x.getKeyword() + "");
		}
	}

}
