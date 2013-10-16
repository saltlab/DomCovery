package salt.domcoverage.core.codeanalysis;

import japa.parser.ASTHelper;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.Node;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.LiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.ObjectCreationExpr;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import salt.domcoverage.core.utils.ConstantVars;

public class AssertionInstrumentor extends Instrumentor {

	public AssertionInstrumentor() {
		super();

	}

	public void updateExpressions(CompilationUnit cu) {

		TestCaseParser tcp = new TestCaseParser();

		srmce = tcp.getAssertionCallExpr(cu);
	}

	public Expression instrumentCallExpr(Expression e) {

		// ASTHelper.

		MethodCallExpr mce = (MethodCallExpr) e;
		List<Expression> oldArgs = mce.getArgs();
		// create a methodcall expre
		List<Expression> expressions2instrument = new ArrayList<Expression>();
		if (oldArgs.size() == 1) {// it is assertTrue for example
			Expression arg0 = oldArgs.get(0);
			expressions2instrument.addAll(getSeleniumDomRelateMethodCallExpressionsORVariableInstances(arg0));
			// if (arg0 instanceof )
		}

		if (oldArgs.size() == 2) {
			// Expression arg0 = oldArgs.get(0);
			Expression arg1 = oldArgs.get(1);
			// expressions2instrument.addAll(getSeleniumDomRelateMethodCallExpressionsORVariableInstances(arg0));
			expressions2instrument.addAll(getSeleniumDomRelateMethodCallExpressionsORVariableInstances(arg1));
		}
		if (expressions2instrument.size() > 0)
			e = instrumentAll(expressions2instrument);

		// put oldargs as it's argument

		// setarguments of mce

		return e;
	}

	private Expression instrumentAll(List<Expression> expressions2instrument) {

		for (Expression expression : expressions2instrument) {

			expression = instrumentOneExpression(expression);
		}
		return expressions2instrument.get(0);

	}

	private Expression instrumentOneExpression(Expression expression) {
		String codeToInstrument = "salt.domcoverage.core.code2instrument.DomCoverageClass.collectAssertedElements";
		// MethodCallExpr calltoClassName = new MethodCallExpr(null, "this.getClass().getName()+\".\"+new Object(){}.getClass().getEnclosingMethod().getName");

		MethodCallExpr call = new MethodCallExpr(null, codeToInstrument);
		// MethodCallExpr calltoPageSource = new MethodCallExpr(null, mce.getScope().toString() + ".getPageSource");
		// MethodCallExpr calltoClassName = new MethodCallExpr(null, "this.getClass().getName()+\".\"+new Object(){}.getClass().getEnclosingMethod().getName");
		// oldArgs.add(calltoPageSource);
		// call.setParentNode(expression.getParentNode());
		List<Expression> args = new ArrayList<Expression>();
		args.add(expression);
		call.setArgs(args);
		// ASTHelper.addArgument((MethodCallExpr) expression.getParentNode(), call);
		// ASTHelper.add(expression.,call);
		expression = call;
		System.out.println("created call: " + call.toString());
		return expression;
	}

	private List<Expression> getSeleniumDomRelateMethodCallExpressionsORVariableInstances(Expression exp) {
		List<Expression> outputExps = new ArrayList<Expression>();
		if (exp instanceof MethodCallExpr) {// isTrue(elem)
			MethodCallExpr arg0mce = (MethodCallExpr) exp;
			if (isdomrelatedMethodCall(arg0mce)) {
				instrumentOneExpression(arg0mce);
				outputExps.add(arg0mce);
				return outputExps;
			}
			if (arg0mce.getArgs() != null) {
				for (Expression exparg : arg0mce.getArgs()) {
					outputExps.addAll(getSeleniumDomRelateMethodCallExpressionsORVariableInstances(exparg));
					return outputExps;
				}
			}
			Expression scope = arg0mce.getScope();
			if (scope != null) {
				outputExps.addAll(getSeleniumDomRelateMethodCallExpressionsORVariableInstances(scope));
				return outputExps;
			}
		}
		if (exp instanceof BinaryExpr) {
			BinaryExpr be = (BinaryExpr) exp;
			outputExps.addAll(getSeleniumDomRelateMethodCallExpressionsORVariableInstances(be.getLeft()));
			outputExps.addAll(getSeleniumDomRelateMethodCallExpressionsORVariableInstances(be.getRight()));
			return outputExps;
		}
		if (exp instanceof NameExpr) {
			NameExpr le = (NameExpr) exp;
			outputExps.add(le);
			return outputExps;
		}
		return outputExps;
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
