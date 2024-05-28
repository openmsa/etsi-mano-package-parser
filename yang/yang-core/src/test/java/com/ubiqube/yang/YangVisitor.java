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
package com.ubiqube.yang;

import com.ubiqube.etsi.mano.yang.YangStatementParser.ArgumentContext;
import com.ubiqube.etsi.mano.yang.YangStatementParser.FileContext;
import com.ubiqube.etsi.mano.yang.YangStatementParser.KeywordContext;
import com.ubiqube.etsi.mano.yang.YangStatementParser.StatementContext;
import com.ubiqube.etsi.mano.yang.YangStatementParser.UnquotedStringContext;
import com.ubiqube.etsi.mano.yang.YangStatementParserBaseVisitor;

public class YangVisitor extends YangStatementParserBaseVisitor<Object> {

	@Override
	public Object visitFile(final FileContext ctx) {
		System.out.println("visitFile: " + ctx.getChildCount());
		return super.visitFile(ctx);
	}

	@Override
	public Object visitStatement(final StatementContext ctx) {
		System.out.println("statement: ");
		return super.visitStatement(ctx);
	}

	@Override
	public Object visitKeyword(final KeywordContext ctx) {
		System.out.println("keyword: " + ctx.getText());
		return super.visitKeyword(ctx);
	}

	@Override
	public Object visitArgument(final ArgumentContext ctx) {
		System.out.println("argument: " + ctx.getText() + " c= " + ctx.getChildCount());
		return super.visitArgument(ctx);
	}

	@Override
	public Object visitUnquotedString(final UnquotedStringContext ctx) {
		System.out.println("unquoted");
		return super.visitUnquotedString(ctx);
	}

}
