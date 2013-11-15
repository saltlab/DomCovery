package salt.domcoverage.core.utils;

import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;

import salt.domcoverage.core.codeanalysis.TestCaseParser;

public class TestUtil {

	public static List<String> getAllTests(String folder) {
		return getAllTests(folder, ".java");
	}

	public static List<String> getAllTests(String folder, String type) {
		ArrayList<String> ret = new ArrayList<String>();
		ArrayList<File> filesInDirectoryWithExtension = DOMUtility.getFilesInDirectoryWithExtension(folder, type);
		for (File file : filesInDirectoryWithExtension) {
			ret.add(file.getAbsolutePath());
		}
		return ret;

	}

	public static String getFullyQualifiedNameofClass(String absolutePath) {
		CompilationUnit cu = null;
		try {
			cu = TestCaseParser.getCompilationUnitOfFileName(absolutePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = cu.getPackage().getName() + "." + cu.getTypes().get(0).getName();
		return str;
		// String str= absolutePath.substring(System.getProperty("user.dir"))
	}

	public static void executeUnitTest(String test) {
		try {

			test = TestUtil.getFullyQualifiedNameofClass(test);
			System.out.println("Executing test class: " + test);
			Class<?> forName = Class.forName(test);
			JUnitCore.runClasses(forName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void executeUnitTests(List<String> allTests) {
		for (String test : allTests) {
			// new TestExecutor().execute(test);
			Utils.resetProgramVariables();
			executeUnitTest(test);
		}

	}
}
