package salt.domcoverage.core.codeanalysis;

import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.Node;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.LiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.Statement;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import salt.domcoverage.core.utils.ConstantVars;

public class AssertionBeforeAfterInstrumentor extends Instrumentor {

	public AssertionBeforeAfterInstrumentor() {
		super();

	}

	public void updateExpressions(CompilationUnit cu) {

		TestCaseParser tcp = new TestCaseParser();

		srmce = tcp.getAssertionCallExpr(cu);
	}

	public Expression instrumentCallExpr(Expression e) {

		MethodCallExpr mce = (MethodCallExpr) e;

		String codeToInstrumentOn = "salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn";
		String codeToInstrumentOff = "salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff";
		MethodCallExpr callOn = new MethodCallExpr(null, codeToInstrumentOn);
		MethodCallExpr callOff = new MethodCallExpr(null, codeToInstrumentOff);

		BlockStmt block = (BlockStmt) mce.getParentNode().getParentNode();
		List<Statement> stmts = block.getStmts();
		int indexOf = stmts.indexOf(mce.getParentNode());
		stmts.add(indexOf + 1, new ExpressionStmt(callOff));
		stmts.add(indexOf, new ExpressionStmt(callOn));
		block.setStmts(stmts);
		return e;
	}

	private boolean isdomrelatedMethodCall(MethodCallExpr mce) {
		for (String elementThatAccessDOM : ConstantVars.allSeleniumMethodCalls) {
			if (mce.getName().equals(elementThatAccessDOM)) {
				return true;
			}
		}
		return false;

	}
}
