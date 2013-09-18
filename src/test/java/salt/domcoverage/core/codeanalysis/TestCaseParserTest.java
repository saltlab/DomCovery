package salt.domcoverage.core.codeanalysis;

import japa.parser.ParseException;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.Expression;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import salt.domcoverage.core.utils.Utils;

public class TestCaseParserTest {

	@Test
	public void testTestUrl() throws FileNotFoundException, ParseException, IOException {
		String fileName = "/Users/mehdimir/Desktop/Dropbox/UBC-research/development/eclipse-workspace/CaseStudySubjectsTests/src/main/java/org/photogallery/TestUrl.java";
		TestCaseParser tcp = new TestCaseParser();
		HashMap<MethodDeclaration, ArrayList<Expression>> srmce = tcp.getSeleniumDomRelateMethodCallExpressions(fileName);
		printResults(srmce);
	}

	@Test
	public void testSlideShowPause() throws FileNotFoundException, ParseException, IOException {
		String fileName = "/Users/mehdimir/Desktop/Dropbox/UBC-research/development/eclipse-workspace/CaseStudySubjectsTests/src/main/java/org/photogallery/SlideShowPauseTest.java";
		TestCaseParser tcp = new TestCaseParser();
		HashMap<MethodDeclaration, ArrayList<Expression>> srmce = tcp.getSeleniumDomRelateMethodCallExpressions(fileName);
		printResults(srmce);
	}

	private void printResults(HashMap<MethodDeclaration, ArrayList<Expression>> srmce) {
		for (MethodDeclaration e : srmce.keySet()) {
			System.out.println("testcase: " + e.getName());
			Utils.printList(srmce.get(e));
		}
	}

}
