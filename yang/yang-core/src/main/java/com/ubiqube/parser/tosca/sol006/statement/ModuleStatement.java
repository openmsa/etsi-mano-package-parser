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
import com.ubiqube.parser.tosca.sol006.Revision;
import com.ubiqube.parser.tosca.sol006.ir.IrArgument;
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
public class ModuleStatement extends AbstractStatementImpl implements NamedStatement {

	private YangVersionStatement yangVersion;

	private List<IncludeStatement> include = new ArrayList<>();

	private List<ImportStatement> imp = new ArrayList<>();

	private List<GroupingStatement> grouping = new ArrayList<>();

	private List<ContainerStatement> container = new ArrayList<>();

	private List<TypeDefStatement> typeDef = new ArrayList<>();
	private List<UsesStatement> uses = new ArrayList<>();

	private String description;

	private String name;

	private Revision version;

	private BelongsToStatement belongsTo;

	private String prefix;

	private String organization;

	private String contact;
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
		return "module";
	}

	@Override
	public void load(final IrStatement res) {
		final IrArgument arg = res.getArgument();
		name = arg.toString();
		final List<IrStatement> stmt = res.getStatements();
		stmt.forEach(this::parseStatement);
	}

	private Object parseStatement(final IrStatement x) {
		switch (x.getKeyword().identifier()) {
		case "contact" -> contact = YangUtils.argumentToString(x.getArgument());
		case "container" -> YangUtils.genericHandle(this, x, ContainerStatement::new, container);
		case "description" -> description = YangUtils.argumentToString(x.getArgument());
		case "grouping" -> YangUtils.genericHandle(this, x, GroupingStatement::new, grouping);
		case "include" -> YangUtils.genericHandle(this, x, IncludeStatement::new, include);
		case "import" -> YangUtils.genericHandle(this, x, ImportStatement::new, imp);
		case "namespace" -> namespace = YangUtils.argumentToString(x.getArgument());
		case "organization" -> organization = YangUtils.argumentToString(x.getArgument());
		case "prefix" -> prefix = YangUtils.argumentToString(x.getArgument());
		case "revision" -> YangUtils.genericHandle(this, x, RevisionStatement::new, revision);
		case "typedef" -> YangUtils.genericHandle(this, x, TypeDefStatement::new, typeDef);
		case "uses" -> YangUtils.genericHandle(this, x, UsesStatement::new, uses);
		case "yang-version" -> version = new Revision(x.getArgument().toString());
		default -> ErrorHelper.handleError(x);
		}
		return null;
	}

}
