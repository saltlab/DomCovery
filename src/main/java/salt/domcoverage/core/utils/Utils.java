package salt.domcoverage.core.utils;

import japa.parser.ast.expr.MethodCallExpr;

import java.io.File;
import java.util.ArrayList;

public class Utils {

	
	public static void printArrayList(ArrayList e){
		for (Object o : e) {
			System.out.println( e.size()+": " + o);
		}	
	}

	public static String[] convertToArrayofString(ArrayList<File> domFiles) {

		String[] array= new String[domFiles.size()];
		for (int i = 0; i < domFiles.size(); i++) {
			array[i]=domFiles.get(i).getName();
		}
		return array;
	}
}
