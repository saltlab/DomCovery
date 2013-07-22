package ca.ubc.ece.salt.domcoverage.core.codeanalysis;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.visitor.VoidVisitor;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.bcel.generic.Visitor;

import ca.ubc.ece.salt.domcoverage.core.utils.CompilationUnitUtils;
import ca.ubc.ece.salt.domcoverage.core.utils.MethodCallVisitor;
import ca.ubc.ece.salt.domcoverage.core.utils.Utils;

public class TestCaseParser {

	public HashMap<MethodDeclaration, ArrayList<MethodCallExpr>> getSeleniumDomRelateMethodCallExpressions(CompilationUnit cu)
			throws FileNotFoundException, ParseException, IOException {
		ArrayList<MethodDeclaration> testMethodsofCompilationUnit = CompilationUnitUtils.testMethodsofCompilationUnit(cu);
		return getSeleniumDomRelateMethodCallExpressions(testMethodsofCompilationUnit);

	}

	private HashMap<MethodDeclaration, ArrayList<MethodCallExpr>> getSeleniumDomRelateMethodCallExpressions(
			ArrayList<MethodDeclaration> testMethodsofCompilationUnit) {
		HashMap<MethodDeclaration, ArrayList<MethodCallExpr>> domRelateMethodCallExps = new HashMap<MethodDeclaration, ArrayList<MethodCallExpr>>();

		for (MethodDeclaration methodDeclaration : testMethodsofCompilationUnit) {
			// find location of all findelement methods
			MethodCallVisitor mcv = new MethodCallVisitor();
			ArrayList<String> elementsToCover = new ArrayList<String>();
			elementsToCover.add("findElement");
			mcv.applyFilter(elementsToCover);

			mcv.visit(methodDeclaration, null);

			domRelateMethodCallExps.put(methodDeclaration, mcv.getMethodCallExpressions());
			// instrument them
			// Utils.printArrayList( mcv.getMethodCallExpressions());
		}
		return domRelateMethodCallExps;
	}

	public HashMap<MethodDeclaration, ArrayList<MethodCallExpr>> getSeleniumDomRelateMethodCallExpressions(String fileName)
			throws FileNotFoundException, ParseException, IOException {

		CompilationUnit cu = getCompilationUnitOfFileName(fileName);
		return getSeleniumDomRelateMethodCallExpressions(cu);
	}

	static CompilationUnit getCompilationUnitOfFileName(String fileName) throws FileNotFoundException, ParseException,
			IOException {
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

}
