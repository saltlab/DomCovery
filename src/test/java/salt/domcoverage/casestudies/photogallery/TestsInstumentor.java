package salt.domcoverage.casestudies.photogallery;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.MethodCallExpr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import salt.domcoverage.core.codeanalysis.TestCaseInstrumentor;
import salt.domcoverage.core.codeanalysis.TestCaseParser;
import salt.domcoverage.core.utils.CompilationUnitUtils;
import salt.domcoverage.core.utils.Utils;


public class TestsInstumentor {
		TestCaseInstrumentor tci;
		private boolean writeBack=true; 
		
		@Before
		public void setUp(){
			 tci = new TestCaseInstrumentor();

		}
		@Test
		public void testInstrumentMethodCall() throws FileNotFoundException, ParseException, IOException  {
			//testthis(TestSourceLocations.TestUrl);
		}	
		@Test
		public void testInstrumentMethodCallforStopPlay() throws FileNotFoundException, ParseException, IOException  {
			//testthis(TestSourceLocations.TestSlideShow);
		}
		
		@Test
		public void testInstrumentMethodCallforTestEnteringName() throws FileNotFoundException, ParseException, IOException  {
			testthis(PhotogalleryTestSourceLocations.TestAddCategory);
		}

		private void testthis( String fileName) throws FileNotFoundException, ParseException, IOException {
			TestCaseParser tcp= new TestCaseParser();
			CompilationUnit cu = TestCaseParser.getCompilationUnitOfFileName(fileName);

			HashMap<MethodDeclaration,ArrayList<MethodCallExpr>> srmce= tcp.getSeleniumDomRelateMethodCallExpressions(cu);

			for ( MethodDeclaration e : srmce.keySet()) {
				System.out.println("testcase: "+ e.getName());
				for (MethodCallExpr mce : srmce.get(e)) {
					tci.instrumentMethodCall(mce);
					System.out.println(mce);
				}
			}
			if (writeBack == true)
				CompilationUnitUtils.writeCompilationUnitToFile(cu,fileName);
			System.out.println("done writing");
			
		}

	}


