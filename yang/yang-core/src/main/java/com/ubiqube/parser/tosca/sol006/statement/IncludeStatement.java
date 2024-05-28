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

import com.ubiqube.parser.tosca.sol006.ir.IrStatement;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *         https://www.rfc-editor.org/rfc/rfc7950#section-7.1.6
 *
 */
@Getter
@Setter
public class IncludeStatement extends AbstractStatementImpl {

	private String name;

	// description reference revision-date
	@Override
	public String getYangName() {
		return "include";
	}

	@Override
	public void load(final IrStatement res) {
		name = res.getArgument().toString();
		final List<IrStatement> lst = res.getStatements();
		if (!lst.isEmpty()) {
			throw new IllegalArgumentException("TODO");
		}
	}

}
