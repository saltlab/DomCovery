package salt.domcoverage.core.codeanalysis;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.ObjectCreationExpr;

import java.util.ArrayList;
import java.util.List;

public class DriverInstrumentor extends Instrumentor {

	public DriverInstrumentor() {
		super();

	}

	public void updateExpressions(CompilationUnit cu) {

		TestCaseParser tcp = new TestCaseParser();

		srmce = tcp.getDriverCallExpr(cu);
	}

	public Expression instrumentCallExpr(Expression e) {

		ObjectCreationExpr oce = (ObjectCreationExpr) e;

		String codeToInstrument = "salt.domcoverage.core.dom.proxy.BrowserProfile.getConfigureProxyandgetProfile";
		MethodCallExpr call = new MethodCallExpr(null, codeToInstrument);

		List<Expression> newArgs = new ArrayList<Expression>();
		newArgs.add(call);
		oce.setArgs(newArgs);

		return oce;
	}

}
