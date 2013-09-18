package salt.domcoverage.core.utils;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AnnotationExpr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class CompilationUnitUtils {
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

	public static ArrayList<MethodDeclaration> testMethodsofCompilationUnit(CompilationUnit cu) {
		ArrayList<MethodDeclaration> testMethodsOfCompilationUnit = new ArrayList<MethodDeclaration>();
		for (BodyDeclaration i : cu.getTypes().get(0).getMembers()) {
			MethodDeclaration j = null;
			if (i instanceof MethodDeclaration) {
				j = (MethodDeclaration) i;
				boolean thisisatest = false;
				List<AnnotationExpr> annotations = j.getAnnotations();
				if (annotations != null && !annotations.isEmpty()) {
					for (AnnotationExpr ann : annotations) {
						if (ann.getName().getName().contains("Test"))
							thisisatest = true;
						break;
					}
				}
				if (thisisatest) {
					testMethodsOfCompilationUnit.add(j);
					// System.out.println("name: " + j.getNameExpr());
				}
			} // prints the resulting compilation unit to default system output
				// System.out.println(cu.toString());
		}
		return testMethodsOfCompilationUnit;
	}

	public static void writeCompilationUnitToFile(CompilationUnit cu, String fileName) {
		// String str = cu.getPackage().getName() + "." + cu.getTypes().get(0).getName();
		// str = str.replace(".", "/");
		// String rootDirectory = fileName.substring(0, fileName.indexOf(str));
		// fileName = rootDirectory + ConstantVars.INSTRUMENTED_CODE_LOCATION + str;

		// fileName = getFileNameInOutputFolder(fileName);
		try {
			FileUtils.writeStringToFile(new File(fileName), cu.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
