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

import com.ubiqube.parser.tosca.generator.StatusType;
import com.ubiqube.parser.tosca.generator.YangUtils;
import com.ubiqube.parser.tosca.sol006.ir.IrStatement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListStatement extends AbstractStatementImpl implements NamedStatement {
	private List<ListStatement> list = new ArrayList<>();
	private List<LeafStatement> leaf = new ArrayList<>();
	private List<LeafListStatement> leafList = new ArrayList<>();
	private List<ChoiceStatement> choice = new ArrayList<>();
	private List<UsesStatement> uses = new ArrayList<>();
	private List<ContainerStatement> container = new ArrayList<>();
	private String description;
	private String reference;
	private String typedef;
	private String name;
	private String key;
	private String minElements;
	private String maxElements;
	private String must;
	private String orderBy;
	private String when;
	private StatusType status;
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
		return "list";
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
		case "config" -> handleError(x);
		case "typedef" -> typedef = YangUtils.argumentToString(x.getArgument());
		case "key" -> key = YangUtils.argumentToString(x.getArgument());
		case "leaf" -> YangUtils.genericHandle(this, x, LeafStatement::new, leaf);
		case "leaf-list" -> YangUtils.genericHandle(this, x, LeafListStatement::new, leafList);
		case "list" -> YangUtils.genericHandle(this, x, ListStatement::new, list);
		case "choice" -> YangUtils.genericHandle(this, x, ChoiceStatement::new, choice);
		case "uses" -> YangUtils.genericHandle(this, x, UsesStatement::new, uses);
		case "container" -> YangUtils.genericHandle(this, x, ContainerStatement::new, container);
		case "min-elements" -> minElements = YangUtils.argumentToString(x.getArgument());
		case "max-elements" -> maxElements = YangUtils.argumentToString(x.getArgument());
		case "must" -> must = YangUtils.argumentToString(x.getArgument());
		case "ordered-by" -> orderBy = YangUtils.argumentToString(x.getArgument());
		case "when" -> when = YangUtils.argumentToString(x.getArgument());
		case "status" -> status = StatusType.fromValue(YangUtils.argumentToString(x.getArgument()));
		default -> throw new IllegalArgumentException(x.getKeyword() + "");
		}
	}

	private static void handleError(final IrStatement x) {
		throw new IllegalArgumentException(x.getKeyword() + "");
	}

}
