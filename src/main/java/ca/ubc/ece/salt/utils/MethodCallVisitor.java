package ca.ubc.ece.salt.utils;

import java.nio.file.WatchEvent.Modifier;

import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.ModifierVisitorAdapter;
import japa.parser.ast.visitor.VoidVisitorAdapter;


public class MethodCallVisitor extends VoidVisitorAdapter {
	
	public void visit(MethodCallExpr mce,Object arg){
		
		System.out.println("mce: "+mce);
		if (mce.getArgs() != null)
		for (Expression ie : mce.getArgs()) {
			//System.out.println("          ie: "+ie);
		}
		
	}
	

}
