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
package com.ubiqube.parser.tosca.sol006.ir;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@Getter
@Setter
public class IrStatement implements IrNode {
	private final IrKeyword keyword;
	private final IrArgument argument;
	private int line;
	private int column;
	private List<IrStatement> statements;

	public IrStatement(final IrKeyword keyword, final IrArgument argument, final List<IrStatement> statements, final int line, final int column) {
		this.keyword = keyword;
		this.argument = argument;
		this.line = line;
		this.column = column;
		this.statements = statements;
	}

}
