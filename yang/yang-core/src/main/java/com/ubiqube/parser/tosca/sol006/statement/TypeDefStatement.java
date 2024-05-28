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
import java.util.Objects;

import com.ubiqube.parser.tosca.generator.ErrorHelper;
import com.ubiqube.parser.tosca.generator.StatusType;
import com.ubiqube.parser.tosca.generator.YangUtils;
import com.ubiqube.parser.tosca.sol006.ir.IrStatement;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *         https://www.rfc-editor.org/rfc/rfc7950#section-7.3
 */
@Getter
@Setter
public class TypeDefStatement extends AbstractStatementImpl implements NamedStatement {

	private String name;
	private String def;
	private String description;
	private String reference;
	private StatusType status;
	private TypeStatement type;
	private String units;
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
		return "typedef";
	}

	@Override
	public void load(final IrStatement res) {
		name = res.getArgument().toString();
		final List<IrStatement> stmts = res.getStatements();
		stmts.forEach(this::doSwitch);
	}

	private void doSwitch(final IrStatement x) {
		switch (x.getKeyword().identifier()) {
		case "default" -> def = YangUtils.argumentToString(x.getArgument());
		case "description" -> description = YangUtils.argumentToString(x.getArgument());
		case "reference" -> reference = YangUtils.argumentToString(x.getArgument());
		case "status" -> status = StatusType.fromValue(YangUtils.argumentToString(x.getArgument()));
		case "type" -> type = YangUtils.genericHandleSingle(this, x, TypeStatement::new);
		case "units" -> units = YangUtils.argumentToString(x.getArgument());
		default -> ErrorHelper.handleError(x);
		}
		Objects.requireNonNull(type, "Type is mandatory in Typedef objects.");
	}

}
