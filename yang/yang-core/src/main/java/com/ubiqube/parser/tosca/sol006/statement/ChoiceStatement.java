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
public class ChoiceStatement extends AbstractStatementImpl {
	private List<ListStatement> list = new ArrayList<>();
	private List<LeafStatement> leaf = new ArrayList<>();
	private List<LeafListStatement> leafList = new ArrayList<>();
	private List<ChoiceStatement> choice = new ArrayList<>();
	private List<ContainerStatement> container = new ArrayList<>();

	private String description;
	private String reference;
	private String name;
	private String mandatory;

	@Override
	public String getYangName() {
		return "choice";
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
		case "leaf" -> YangUtils.genericHandle(this, x, LeafStatement::new, leaf);
		case "leaf-list" -> YangUtils.genericHandle(this, x, LeafListStatement::new, leafList);
		case "list" -> YangUtils.genericHandle(this, x, ListStatement::new, list);
		case "choice" -> YangUtils.genericHandle(this, x, ChoiceStatement::new, choice);
		case "container" -> YangUtils.genericHandle(this, x, ContainerStatement::new, container);
		case "mandatory" -> mandatory = YangUtils.argumentToString(x.getArgument());
		default -> throw new IllegalArgumentException(x.getKeyword() + "");
		}
	}

	private void handleContainer(final IrStatement x) {
		final ContainerStatement l = new ContainerStatement();
		l.load(x);
		container.add(l);
	}

	private void handleChoice(final IrStatement x) {
		final ChoiceStatement l = new ChoiceStatement();
		l.load(x);
		choice.add(l);
	}

	private void handleList(final IrStatement x) {
		final ListStatement l = new ListStatement();
		l.load(x);
		list.add(l);
	}

	private void handleLeafList(final IrStatement x) {
		final LeafListStatement ls = new LeafListStatement();
		ls.load(x);
		leafList.add(ls);
	}

	private void handleLeaf(final IrStatement x) {
		final LeafStatement ls = new LeafStatement();
		ls.load(x);
		leaf.add(ls);
	}

}
