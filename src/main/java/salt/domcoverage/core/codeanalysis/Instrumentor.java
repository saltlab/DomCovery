package salt.domcoverage.core.codeanalysis;

import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.Expression;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import salt.domcoverage.core.utils.CompilationUnitUtils;
import salt.domcoverage.core.utils.TestUtil;

public abstract class Instrumentor {

	HashMap<MethodDeclaration, ArrayList<Expression>> srmce;

	public void instrument(String fileName) {
		File f = new File(fileName);
		if (f.isDirectory()) {
			List<String> allTests = TestUtil.getAllTests(fileName);
			for (String test : allTests) {
				instrument(test, true);
			}
			return;
		}
		instrument(fileName, true);
	}

	public void instrument(String fileName, boolean writeBack) {
		try {
			TestCaseParser tcp = new TestCaseParser();
			CompilationUnit cu;
			cu = TestCaseParser.getCompilationUnitOfFileName(fileName);

			updateExpressions(cu);

			for (MethodDeclaration e : srmce.keySet()) {
				System.out.println("method: " + e.getName());
				for (Expression mce : srmce.get(e)) {
					this.instrumentCallExpr(mce);
					System.out.println("instrumented mce: " + mce);
				}
			}
			if (writeBack == true)
				CompilationUnitUtils.writeCompilationUnitToFile(cu, fileName);
			// System.out.println(cu);
			System.out.println("done writing");

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	protected abstract void updateExpressions(CompilationUnit cu);

	public abstract Expression instrumentCallExpr(Expression mce);
}
