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

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.parser.tosca.generator.ErrorHelper;
import com.ubiqube.parser.tosca.generator.YangUtils;
import com.ubiqube.parser.tosca.sol006.ir.IrStatement;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *         https://www.rfc-editor.org/rfc/rfc7950#section-7.4
 */
@Getter
@Setter
public class TypeStatement extends AbstractStatementImpl implements NamedStatement {

	private List<String> base = new ArrayList<>();
	private List<BitStatement> bit = new ArrayList<>();
	private List<EnumStatement> enu = new ArrayList<>();
	private String fractionDigit;
	private String length;
	private String path;
	private List<String> pattern = new ArrayList<>();
	private RangeStatement range;
	// Boolean
	private String requireInstance;
	private List<TypeStatement> type = new ArrayList<>();
	private String name;
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
		return "type";
	}

	@Override
	public void load(final IrStatement res) {
		name = res.getArgument().toString();
		final List<IrStatement> stmts = res.getStatements();
		stmts.forEach(this::doSwitch);
	}

	private void doSwitch(final IrStatement x) {
		switch (x.getKeyword().identifier()) {
		case "base" -> YangUtils.handleListable(x.getArgument(), base);
		case "bit" -> YangUtils.genericHandle(this, x, BitStatement::new, bit);
		case "enum" -> YangUtils.genericHandle(this, x, EnumStatement::new, enu);
		case "fraction-digits" -> fractionDigit = YangUtils.argumentToString(x.getArgument());
		case "length" -> length = YangUtils.argumentToString(x.getArgument());
		case "path" -> path = YangUtils.argumentToString(x.getArgument());
		case "pattern" -> YangUtils.argumentToString(x.getArgument());
		case "range" -> range = YangUtils.genericHandleSingle(this, x, RangeStatement::new);
		case "require-instance" -> requireInstance = YangUtils.argumentToString(x.getArgument());
		case "type" -> YangUtils.genericHandle(this, x, TypeStatement::new, type);
		default -> ErrorHelper.handleError(x);
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
