/**
 * Copyright (C) 2019-2025 Ubiqube.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
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
