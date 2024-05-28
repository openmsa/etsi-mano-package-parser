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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.ubiqube.etsi.mano.yang.YangStatementLexer;
import com.ubiqube.etsi.mano.yang.YangStatementParser;
import com.ubiqube.etsi.mano.yang.YangStatementParser.ArgumentContext;
import com.ubiqube.etsi.mano.yang.YangStatementParser.FileContext;
import com.ubiqube.etsi.mano.yang.YangStatementParser.StatementContext;
import com.ubiqube.parser.tosca.sol006.ir.IrArgument;
import com.ubiqube.parser.tosca.sol006.ir.IrKeyword;
import com.ubiqube.parser.tosca.sol006.ir.IrKeyword.Unqualified;
import com.ubiqube.parser.tosca.sol006.ir.IrStatement;
import com.ubiqube.parser.tosca.sol006.ir.YangRoot;

public class YangFileLoader {

	public YangFileLoader() {
		// Nothing.
	}

	public YangRoot loadFile(final File file) {
		try (final InputStream stream = new FileInputStream(file)) {
			final YangStatementLexer lexer = new YangStatementLexer(CharStreams.fromStream(stream));
			final YangStatementParser parser = new YangStatementParser(new CommonTokenStream(lexer));
			final FileContext result = parser.file();
			final StatementContext stmt = result.statement();
			final IrStatement res = buildStatements(stmt);
			return createYangRoot(res);
		} catch (final IOException e) {
			throw new YangException(e);
		}
	}

	private static YangRoot createYangRoot(final IrStatement res) {
		final YangRoot yr = new YangRoot();
		yr.load(res);
		return yr;
	}

	private IrStatement buildStatements(final StatementContext stmt) {
		final ParseTree firstChild = stmt.getChild(0);
		final ParseTree keywordStart = firstChild.getChild(0);
		final Token keywordToken = ((TerminalNode) keywordStart).getSymbol();
		final IrKeyword keyword = new Unqualified(keywordToken.getText());
		final IrArgument argument = createArgument(stmt);
		if (1 != firstChild.getChildCount()) {
			throw new ParseCancellationException();
		}
		final List<IrStatement> statements = createStatements(stmt);
		final int line = keywordToken.getLine();
		final int column = keywordToken.getCharPositionInLine();
		return new IrStatement(keyword, argument, statements, line, column);
	}

	private static IrArgument createArgument(final StatementContext stmt) {
		final ArgumentContext argument = stmt.argument();
		if (argument == null) {
			return null;
		}
		return switch (argument.getChildCount()) {
		case 0 -> throw new YangException("Unexpected shape of " + argument);
		case 1 -> YangStrings.createSimple(argument);
		case 2 -> YangStrings.createQuoted(argument);
		default -> YangStrings.createConcatenation(argument);
		};
	}

	private List<IrStatement> createStatements(final StatementContext stmt) {
		final List<StatementContext> statements = stmt.statement();
		return statements.stream().map(this::buildStatements).toList();
	}

}
