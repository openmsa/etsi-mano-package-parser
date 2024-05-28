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

import com.ubiqube.parser.tosca.generator.YangUtils;
import com.ubiqube.parser.tosca.sol006.ir.IrStatement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MustStatement extends AbstractStatementImpl {

	private String name;
	private String description;
	private String reference;
	private String errorAppTag;
	private String errorMessage;

	@Override
	public String getYangName() {
		return "must";
	}

	@Override
	public void load(final IrStatement res) {
		name = res.getArgument().toString();
		final List<IrStatement> stmts = res.getStatements();
		stmts.forEach(x -> doSwitch(x));
	}

	private void doSwitch(final IrStatement x) {
		switch (x.getKeyword().identifier()) {
		case "description" -> description = YangUtils.argumentToString(x.getArgument());
		case "reference" -> reference = YangUtils.argumentToString(x.getArgument());
		case "error-app-tag" -> errorAppTag = YangUtils.argumentToString(x.getArgument());
		case "error-message" -> errorMessage = YangUtils.argumentToString(x.getArgument());
		default -> throw new IllegalArgumentException(x.getKeyword() + "");
		}
	}

}
