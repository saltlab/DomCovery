package ca.ubc.ece.salt.domcoverage.core.codeanalysis;

import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.MethodCallExpr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Ignore;
import org.junit.Test;

import ca.ubc.ece.salt.domcoverage.core.codeanalysis.TestCaseParser;
import ca.ubc.ece.salt.domcoverage.core.utils.Utils;

public class TestCaseParserTest {

	@Test
	public void testTestUrl() throws FileNotFoundException, ParseException, IOException {
		String fileName = "/Users/mehdimir/Desktop/Dropbox/UBC-research/development/eclipse-workspace/CaseStudySubjectsTests/src/main/java/org/photogallery/TestUrl.java";
		TestCaseParser tcp= new TestCaseParser();
		HashMap<MethodDeclaration,ArrayList<MethodCallExpr>> srmce= tcp.getSeleniumDomRelateMethodCallExpressions(fileName);
		printResults(srmce);	
	}


	@Test
	public void testSlideShowPause() throws FileNotFoundException, ParseException, IOException {
		String fileName = "/Users/mehdimir/Desktop/Dropbox/UBC-research/development/eclipse-workspace/CaseStudySubjectsTests/src/main/java/org/photogallery/SlideShowPauseTest.java";
		TestCaseParser tcp= new TestCaseParser();
		HashMap<MethodDeclaration,ArrayList<MethodCallExpr>> srmce= tcp.getSeleniumDomRelateMethodCallExpressions(fileName);
		printResults(srmce);	
	}
private void printResults(HashMap<MethodDeclaration, ArrayList<MethodCallExpr>> srmce) {
		for ( MethodDeclaration e : srmce.keySet()) {
			System.out.println("testcase: "+ e.getName());
			Utils.printArrayList(srmce.get(e));
		}
	}

}
