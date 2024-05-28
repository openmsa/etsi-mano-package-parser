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
 *
 */
@Getter
@Setter
public class ContainerStatement extends AbstractStatementImpl implements NamedStatement {
	private List<ListStatement> list = new ArrayList<>();
	private List<LeafStatement> leaf = new ArrayList<>();
	private List<LeafListStatement> leafList = new ArrayList<>();
	private List<ContainerStatement> container = new ArrayList<>();
	private List<ChoiceStatement> choice = new ArrayList<>();
	private List<UsesStatement> uses = new ArrayList<>();
	private List<GroupingStatement> grouping = new ArrayList<>();
	private String name;
	private String description;
	private String reference;
	private String presence;
	private String when;
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
		return "container";
	}

	@Override
	public void load(final IrStatement res) {
		name = res.getArgument().toString();
		final List<IrStatement> stmts = res.getStatements();
		stmts.forEach(this::doSwitch);
	}

	private void doSwitch(final IrStatement x) {
		switch (x.getKeyword().identifier()) {
		case "description" -> description = YangUtils.argumentToString(x.getArgument());
		case "reference" -> reference = YangUtils.argumentToString(x.getArgument());
		case "action" -> ErrorHelper.handleError(x);
		case "choice" -> YangUtils.genericHandle(this, x, ChoiceStatement::new, choice);
		case "config" -> ErrorHelper.handleError(x);
		case "container" -> YangUtils.genericHandle(this, x, ContainerStatement::new, container);
		case "grouping" -> YangUtils.genericHandle(this, x, GroupingStatement::new, grouping);
		case "if-feature" -> ErrorHelper.handleError(x);
		case "leaf" -> YangUtils.genericHandle(this, x, LeafStatement::new, leaf);
		case "leaf-list" -> YangUtils.genericHandle(this, x, LeafListStatement::new, leafList);
		case "list" -> YangUtils.genericHandle(this, x, ListStatement::new, list);
		case "typedef" -> ErrorHelper.handleError(x);
		case "uses" -> YangUtils.genericHandle(this, x, UsesStatement::new, uses);
		case "presence" -> presence = YangUtils.argumentToString(x.getArgument());
		case "when" -> when = YangUtils.argumentToString(x.getArgument());
		default -> throw new IllegalArgumentException(x.getKeyword() + "");
		}
	}

}
