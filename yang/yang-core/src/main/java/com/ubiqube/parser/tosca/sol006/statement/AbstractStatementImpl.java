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

import com.ubiqube.parser.tosca.generator.YangException;

/**
 *
 * @author Olivier Vignaud
 *
 */
public abstract class AbstractStatementImpl implements Statement {
	protected String namespace;
	protected List<RevisionStatement> revision = new ArrayList<>();
	protected Statement parent;

	@Override
	public final String getNamespace() {
		return namespace;
	}

	public void setNamespace(final String namespace) {
		this.namespace = namespace;
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
