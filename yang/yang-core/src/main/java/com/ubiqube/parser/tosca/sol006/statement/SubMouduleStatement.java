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
 *         https://www.rfc-editor.org/rfc/rfc7950#section-7.2
 */
@Getter
@Setter
public class SubMouduleStatement extends AbstractStatementImpl implements NamedStatement {

	private YangVersionStatement yangVersion;

	private List<IncludeStatement> include = new ArrayList<>();

	private List<ImportStatement> imp = new ArrayList<>();

	private String description;

	private List<GroupingStatement> grouping = new ArrayList<>();

	private List<IdentityStatement> identity = new ArrayList<>();

	private List<TypeDefStatement> typedef = new ArrayList<>();

	private String name;

	private Revision version;

	private BelongsToStatement belongsTo;

	private String prefix;

	private String organization;
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
		return "submodule";
	}

	@Override
	public void load(final IrStatement res) {
		final IrArgument arg = res.getArgument();
		name = arg.toString();
		final List<IrStatement> stmt = res.getStatements();
		stmt.forEach(this::parseStatement);
	}

	private void parseStatement(final IrStatement x) {
		switch (x.getKeyword().identifier()) {
		case "belongs-to" -> belongsTo = YangUtils.genericHandleSingle(this, x, BelongsToStatement::new);
		case "description" -> description = YangUtils.argumentToString(x.getArgument());
		case "identity" -> YangUtils.genericHandle(this, x, IdentityStatement::new, identity);
		case "include" -> YangUtils.genericHandle(this, x, IncludeStatement::new, include);
		case "import" -> YangUtils.genericHandle(this, x, ImportStatement::new, imp);
		case "grouping" -> YangUtils.genericHandle(this, x, GroupingStatement::new, grouping);
		case "organization" -> organization = YangUtils.argumentToString(x.getArgument());
		case "prefix" -> prefix = YangUtils.argumentToString(x.getArgument());
		case "revision" -> YangUtils.genericHandle(this, x, RevisionStatement::new, revision);
		case "typedef" -> YangUtils.genericHandle(this, x, TypeDefStatement::new, typedef);
		case "yang-version" -> version = new Revision(YangUtils.argumentToString(x.getArgument()));
		default -> ErrorHelper.handleError(x);
		}
	}

}
