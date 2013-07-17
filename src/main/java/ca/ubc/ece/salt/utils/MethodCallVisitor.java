package ca.ubc.ece.salt.utils;

import java.nio.file.WatchEvent.Modifier;
import java.util.ArrayList;

import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.ModifierVisitorAdapter;
import japa.parser.ast.visitor.VoidVisitorAdapter;


public class MethodCallVisitor extends VoidVisitorAdapter {
	
	private ArrayList<String> elementsToCover= new ArrayList<String>();

	public void visit(MethodCallExpr mce,Object arg){
		
		System.out.println("mce: "+mce);
		if (mce.getArgs() != null)
		for (Expression ie : mce.getArgs()) {
			for (String elementThatAccessDOM : elementsToCover) {
				if (ie.toString().contains(elementThatAccessDOM))
					System.out.println("          ie: "+ie);
			}
		}
		
	}

	public void applyFilter(ArrayList<String> etc) {

		elementsToCover=etc;
	}
	

}
