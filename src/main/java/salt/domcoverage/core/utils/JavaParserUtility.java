package salt.domcoverage.core.utils;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;

import java.util.ArrayList;

public class JavaParserUtility {

	public static ArrayList<MethodDeclaration> getMethodsofCompilationUnit(CompilationUnit cu) {
		ArrayList<MethodDeclaration> methodsOfCompilationUnit = new ArrayList<MethodDeclaration>();
		for (BodyDeclaration i : cu.getTypes().get(0).getMembers()) {
			MethodDeclaration j = null;
			if (i instanceof MethodDeclaration) {
				j = (MethodDeclaration) i;
				methodsOfCompilationUnit.add(j);
				// System.out.println("name: " + j.getNameExpr());

			} // prints the resulting compilation unit to default system output
				// System.out.println(cu.toString());
		}
		return methodsOfCompilationUnit;
	}

}
