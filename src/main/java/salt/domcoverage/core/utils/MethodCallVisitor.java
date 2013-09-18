package salt.domcoverage.core.utils;

import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;

public class MethodCallVisitor extends VoidVisitorAdapter {

	private ArrayList<String> elementsToCover = new ArrayList<String>();

	ArrayList<Expression> methodCallExpressions = new ArrayList<Expression>();

	public ArrayList<Expression> getMethodCallExpressions() {
		return methodCallExpressions;
	}

	public void visit(MethodCallExpr mce, Object arg) {

		// System.out.println("mce: " + mce);
		// for (String elementThatAccessDOM : elementsToCover) {
		// if (mce.toString().contains(elementThatAccessDOM)) {
		getAllMethdocalls(mce);
		// methodCallExpressions.add(mce);
		// addAllScopes(mce);
		// while (mce.getArgs() != null) {
		// // this is a method call
		// for (Object arg1 : mce.getArgs()) {
		// if (arg1 instanceof MethodCallExpr)
		// addAllScopes((MethodCallExpr)arg1);
		// }
		// if (mce instanceof Expression)
		// System.out.println("");
		// }
		// }
		// }

	}

	private void getAllMethdocalls(MethodCallExpr mce) {

		addToMethodCallExpressions(mce);

		Expression scope = mce.getScope();
		if (scope instanceof MethodCallExpr) {
			MethodCallExpr scope2 = (MethodCallExpr) scope;
			// addToMethodCallExpressions(scope2);
			getAllMethdocalls(scope2);
		}
		if (mce.getArgs() != null) {
			for (Object arg1 : mce.getArgs()) {
				if (arg1 instanceof MethodCallExpr)
					getAllMethdocalls((MethodCallExpr) arg1);
			}

		}

	}

	private void addToMethodCallExpressions(MethodCallExpr mce) {
		for (String elementThatAccessDOM : elementsToCover) {
			if (mce.getName().contains(elementThatAccessDOM)) {
				methodCallExpressions.add(mce);
			}
		}

	}

	// private void addAllScopes(MethodCallExpr mce) {
	// Expression scope = mce.getScope();
	// while (scope instanceof MethodCallExpr) {
	// methodCallExpressions.add((MethodCallExpr) scope);
	// scope = ((MethodCallExpr) scope).getScope();
	// }
	// }

	public void applyFilter(ArrayList<String> etc) {

		elementsToCover = etc;
	}

}
