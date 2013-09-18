package salt.domcoverage.core.codeanalysis;

import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestInstrumentor extends Instrumentor {

	public void updateExpressions(CompilationUnit cu) {

		TestCaseParser tcp = new TestCaseParser();

		try {
			srmce = tcp.getSeleniumDomRelateMethodCallExpressions(cu);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MethodCallExpr instrumentCallExpr(Expression e) {

		// ASTHelper.
		MethodCallExpr mce = (MethodCallExpr) e;
		List<Expression> oldArgs = mce.getArgs();
		// create a methodcall expre
		String codeToInstrument = "salt.domcoverage.core.code2instrument.DomCoverageClass.collectData";
		MethodCallExpr call = new MethodCallExpr(null, codeToInstrument);
		MethodCallExpr calltoPageSource = new MethodCallExpr(null, mce.getScope().toString() + ".getPageSource");
		MethodCallExpr calltoClassName = new MethodCallExpr(null, "this.getClass().getName()+\".\"+new Object(){}.getClass().getEnclosingMethod().getName");
		oldArgs.add(calltoPageSource);
		oldArgs.add(calltoClassName);

		call.setArgs(oldArgs);
		// put oldargs as it's argument

		// setarguments of mce
		List<Expression> newArgs = new ArrayList<Expression>();
		newArgs.add(call);
		mce.setArgs(newArgs);

		return mce;
	}

}
