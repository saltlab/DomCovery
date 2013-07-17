package ca.ubc.ece.salt.codeanalysis;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.visitor.VoidVisitor;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.Visitor;

import ca.ubc.ece.salt.utils.CompilationUnitUtils;
import ca.ubc.ece.salt.utils.MethodCallVisitor;

public class TestCaseParser {

	public static void main(String[] args) throws ParseException, IOException {

		//instrument java file 
		//get types of accessing to the elements
		//
		
		FileInputStream in = new FileInputStream("/Users/mehdimir/Desktop/Dropbox/UBC-research/development/eclipse-workspace/CaseStudySubjectsTests/src/main/java/org/photogallery/SlideShowPauseTest.java");

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
		ArrayList<MethodDeclaration> testMethodsofCompilationUnit = CompilationUnitUtils.testMethodsofCompilationUnit(cu);
		
		for (MethodDeclaration methodDeclaration : testMethodsofCompilationUnit) {
			//find location of all findelement methods 
			MethodCallVisitor mcv= new MethodCallVisitor();
			ArrayList<String> elementsToCover = new ArrayList<String>();
			elementsToCover.add("findElement");
			mcv.applyFilter(elementsToCover);
			mcv.visit(methodDeclaration,null);
			
//instrument them
		}
	}


}
