package salt.domcoverage.core.utils;

import japa.parser.ast.expr.MethodCallExpr;

import java.util.ArrayList;

public class Utils {

	public static void printArrayList(ArrayList e){
		for (Object o : e) {
			System.out.println( e.size()+": " + o);
		}	
	}
}
