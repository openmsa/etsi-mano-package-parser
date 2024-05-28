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

import com.ubiqube.parser.tosca.generator.ErrorHelper;
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
public class UsesStatement extends AbstractStatementImpl implements NamedStatement {

	private String name;
	private String description;
	private String reference;
	private String className;

	@Override
	public String getClassName() {
		if (null != className) {
			return className;
		}
		return name;
	}

	@Override
	public String getYangName() {
		return "uses";
	}

	@Override
	public void load(final IrStatement res) {
		name = res.getArgument().toString();
		final List<IrStatement> stmts = res.getStatements();
		stmts.forEach(x -> doSwitch(x));
	}

	private void doSwitch(final IrStatement x) {
		switch (x.getKeyword().identifier()) {
		case "augment" -> ErrorHelper.handleError(x);
		case "description" -> description = x.getArgument().toString();
		case "reference" -> reference = x.getArgument().toString();
		case "if-feature" -> ErrorHelper.handleError(x);
		case "refine" -> ErrorHelper.handleError(x);
		case "status" -> ErrorHelper.handleError(x);
		case "when" -> ErrorHelper.handleError(x);
		default -> throw new IllegalArgumentException(x.getKeyword() + "");
		}
	}

}
