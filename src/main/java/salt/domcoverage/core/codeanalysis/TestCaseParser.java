package salt.domcoverage.core.codeanalysis;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.Expression;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import salt.domcoverage.core.utils.CompilationUnitUtils;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.MethodCallVisitor;
import salt.domcoverage.core.utils.ObjectCreationExprVisitor;

public class TestCaseParser {

	public HashMap<MethodDeclaration, ArrayList<Expression>> getDriverCallExpr(CompilationUnit cu) {
		ArrayList<MethodDeclaration> methodsofCompilationUnit = CompilationUnitUtils.getMethodsofCompilationUnit(cu);
		return getObjectCreationExprs(methodsofCompilationUnit, ConstantVars.SELENIUMDRIVER_METHODCALLS);

	}

	private HashMap<MethodDeclaration, ArrayList<Expression>> getObjectCreationExprs(ArrayList<MethodDeclaration> methodsofCompilationUnit, String[] methodCalls) {
		HashMap<MethodDeclaration, ArrayList<Expression>> driverMethodCallExps = new HashMap<MethodDeclaration, ArrayList<Expression>>();

		for (MethodDeclaration methodDeclaration : methodsofCompilationUnit) {
			// find location of all findelement methods
			ObjectCreationExprVisitor mcv = new ObjectCreationExprVisitor();
			ArrayList<String> elementsToCover = new ArrayList<String>();
			elementsToCover.addAll(Arrays.asList(methodCalls));
			mcv.applyFilter(elementsToCover);

			mcv.visit(methodDeclaration, null);

			driverMethodCallExps.put(methodDeclaration, mcv.getObjectCreationExprs());
			// instrument them
			// Utils.printArrayList( mcv.getMethodCallExpressions());
		}
		return driverMethodCallExps;
	}

	public HashMap<MethodDeclaration, ArrayList<Expression>> getSeleniumDomRelateMethodCallExpressions(CompilationUnit cu) throws FileNotFoundException, ParseException, IOException {
		ArrayList<MethodDeclaration> testMethodsofCompilationUnit = CompilationUnitUtils.methodsofCompilationUnit(cu);
		return getMethodCallExpressions(testMethodsofCompilationUnit, ConstantVars.seleniumDomRelatedMethodCallList);

	}

	private HashMap<MethodDeclaration, ArrayList<Expression>> getMethodCallExpressions(ArrayList<MethodDeclaration> methodsofCompilationUnit, String[] methodCalls) {
		HashMap<MethodDeclaration, ArrayList<Expression>> driverMethodCallExps = new HashMap<MethodDeclaration, ArrayList<Expression>>();

		for (MethodDeclaration methodDeclaration : methodsofCompilationUnit) {
			// find location of all findelement methods
			MethodCallVisitor mcv = new MethodCallVisitor();
			ArrayList<String> elementsToCover = new ArrayList<String>();
			elementsToCover.addAll(Arrays.asList(methodCalls));
			mcv.applyFilter(elementsToCover);

			mcv.visit(methodDeclaration, null);

			driverMethodCallExps.put(methodDeclaration, mcv.getMethodCallExpressions());
			// instrument them
			// Utils.printArrayList( mcv.getMethodCallExpressions());
		}
		return driverMethodCallExps;
	}

	public HashMap<MethodDeclaration, ArrayList<Expression>> getSeleniumDomRelateMethodCallExpressions(String fileName) throws FileNotFoundException, ParseException, IOException {

		CompilationUnit cu = getCompilationUnitOfFileName(fileName);
		return getSeleniumDomRelateMethodCallExpressions(cu);
	}

	public static CompilationUnit getCompilationUnitOfFileName(String fileName) throws FileNotFoundException, ParseException, IOException {
		FileInputStream in = new FileInputStream(fileName);

		CompilationUnit cu;
		try {
			// parse the file
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		return cu;
	}

	public HashMap<MethodDeclaration, ArrayList<Expression>> getAssertionCallExpr(CompilationUnit cu) {
		ArrayList<MethodDeclaration> methodsofCompilationUnit = CompilationUnitUtils.getMethodsofCompilationUnit(cu);
		return getMethodCallExpressions(methodsofCompilationUnit, ConstantVars.assertion_methods_list);
	}

}
