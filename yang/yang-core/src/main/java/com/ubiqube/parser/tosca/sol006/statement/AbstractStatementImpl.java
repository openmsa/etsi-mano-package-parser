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

import com.ubiqube.parser.tosca.generator.YangException;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud
 *
 */
public abstract class AbstractStatementImpl implements Statement {
	@Setter
    protected String namespace;
	protected List<RevisionStatement> revision = new ArrayList<>();
	protected Statement parent;

	@Override
	public final String getNamespace() {
		return namespace;
	}

    @Override
	public RevisionStatement getLatestRevision() {
		if (revision.isEmpty()) {
			throw new YangException("No revision in " + this.getClass());
		}
		return revision.get(0);
	}

	@Override
	public Statement getParent() {
		return parent;
	}

	@Override
	public void setParent(final Statement parent) {
		this.parent = parent;
	}

}
