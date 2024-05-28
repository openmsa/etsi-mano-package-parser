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
package com.ubiqube.parser.tosca.generator;

import java.util.List;
import java.util.function.Supplier;

import com.ubiqube.parser.tosca.sol006.ir.IrArgument;
import com.ubiqube.parser.tosca.sol006.ir.IrArgument.Concatenation;
import com.ubiqube.parser.tosca.sol006.ir.IrArgument.Single;
import com.ubiqube.parser.tosca.sol006.ir.IrKeyword;
import com.ubiqube.parser.tosca.sol006.ir.IrKeyword.Unqualified;
import com.ubiqube.parser.tosca.sol006.ir.IrStatement;
import com.ubiqube.parser.tosca.sol006.statement.Statement;

/**
 *
 * @author Olivier Vignaud
 *
 */
public class YangUtils {

	private YangUtils() {
		//
	}

	public static String getRevisionDateString(final IrStatement importStatement) {
		String revisionDateStr = null;
		for (final IrStatement substatement : importStatement.getStatements()) {
			if (isBuiltin(substatement, "revision-date")) {
				revisionDateStr = argumentToString(substatement.getArgument());
			}
		}
		return revisionDateStr;
	}

	public static String argumentToString(final IrArgument arg) {
		if (arg instanceof final Single s) {
			// Need to unescape.
			return s.string();
		}
		final Concatenation c = (Concatenation) arg;
		return concatStrings(c.parts());
	}

	public static String concatStrings(final List<? extends Single> parts) {
		final StringBuilder sb = new StringBuilder();
		for (final Single part : parts) {
			final String str = part.string();
			sb.append(str);
		}
		return sb.toString();
	}

	public static boolean isBuiltin(final IrStatement stmt, final String localName) {
		final IrKeyword keyword = stmt.getKeyword();
		return (keyword instanceof Unqualified) && localName.equals(keyword.identifier());
	}

	public static <U extends Statement> void genericHandle(final Statement parent, final IrStatement x, final Supplier<U> supp, final List<U> lst) {
		final U n = supp.get();
		n.load(x);
		n.setParent(parent);
		lst.add(n);
	}

	public static void handleListable(final IrArgument arg, final List<String> lst) {
		lst.add(argumentToString(arg));
	}

	public static <U extends Statement> U genericHandleSingle(final Statement parent, final IrStatement x, final Supplier<U> supp) {
		final U n = supp.get();
		n.load(x);
		n.setParent(parent);
		return n;
	}

}
