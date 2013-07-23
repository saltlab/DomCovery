package salt.domcoverage.core.codeanalysis;

import java.util.ArrayList;
import java.util.List;

import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;

public class TestCaseInstrumentor {
	
	public MethodCallExpr instrumentMethodCall(MethodCallExpr mce){
		
		//ASTHelper.
		
		List<Expression> oldArgs= mce.getArgs();
		//create a methodcall expre
		String codeToInstrument = "salt.domcoverage.core.code2instrument.DomCoverageClass.collectData";
		MethodCallExpr call = new MethodCallExpr(null, codeToInstrument);
		MethodCallExpr calltoPageSource = new MethodCallExpr(null, mce.getScope().toString()+".getPageSource");
		MethodCallExpr calltoClassName = new MethodCallExpr(null, "this.getClass().getName()+\".\"+new Object(){}.getClass().getEnclosingMethod().getName");
		oldArgs.add(calltoPageSource);
		oldArgs.add(calltoClassName);
		
		call.setArgs(oldArgs);
		//put oldargs as it's argument
		
		
		//setarguments of mce
		List<Expression> newArgs= new ArrayList<Expression>() ;
		newArgs.add(call);
		mce.setArgs(newArgs);

		return mce;
	}

}
