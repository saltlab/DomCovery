package salt.domcoverage.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;

import javax.tools.*;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompileUtil {
	private static final Logger LOG = LoggerFactory.getLogger(CompileUtil.class);

	public static void compileFiles(String folderLoc) {

		// try {
		// String folderLoc = System.getProperty("user.dir");
		// On Linux/Mac
		// folderLoc += "/src/main/java/com/crawljax/plugins/testsuiteextension/casestudies/originaltests/";
		// On Windows
		// folderLoc += "\\src\\main\\java\\casestudies\\originaltests\\";

		File folder = new File(folderLoc);

		// System.out.println(folderLoc);

		// Compiling the instrumented unit test files
		// LOG.info("Compiling the instrumented unit test files located in {}", folder.getAbsolutePath());

		// FileUtils.listFiles(new File(folder), {"java"}, false);

		File[] listOfFiles = folder.listFiles(new FilenameFilter() {
			public boolean accept(File file, String name) {
				return name.endsWith(".java");
			}
		});

		for (File file : listOfFiles) {
			if (file.isFile()) {
				System.out.println(file.getName());
				LOG.info(file.getName());
			}
		}

		System.out.println(System.getProperty("java.home"));

		// System.setProperty("java.home", "C:\\Program Files\\Java\\jdk1.7.0_05");

		out(listOfFiles[0]);

		// JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		// StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		// Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(listOfFiles));
		// JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
		// boolean success = task.call();

		// System.out.println(success);
		//
		// for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
		// LOG.info("Error on line {} in {}", diagnostic.getLineNumber(), diagnostic.getSource().toString());
		// System.out.println("Error on line " + diagnostic.getLineNumber() + " in " + diagnostic.getSource().toString());
		// }
		//
		// fileManager.close();

		// if (!success) {
		// // Executing the instrumented unit test files. This will produce a log of the execution trace
		// LOG.info("Executing the instrumented unit test files and logging the execution trace...");
		// for (File file : listOfFiles) {
		// if (file.isFile()) {
		//
		// // SI.instrument(file);
		//
		// System.out.println("Executing unit test: " + file.getName());
		// // System.out.println("Executing unit test in " + file.getAbsolutePath());
		// // LOG.info("Executing unit test in {}", file.getName());
		//
		// executeUnitTest(file.getAbsolutePath());
		// }
		//
		// break; // just to test one case...
		//
		// }
		// }

		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

	public static void out(File outFile) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		if (compiler.run(null, null, null, outFile.getAbsolutePath()) != 0) {
			System.err.println("Could not compile.");
			System.exit(0);
		}

		try {
			System.out.println("copyingggggggg files");
			String className = outFile.getAbsolutePath().replace(".java", ".class");
			File compiledFile = new File(className);

			String destName = className.replace("src/main/java", "target/classes");
			File destFile = new File(destName);
			FileUtils.copyFile(compiledFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// Runtime rt = Runtime.getRuntime();
		// Process pr = rt.exec("java " + outFile);
		// BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		// String line = null;
		// while ((line = input.readLine()) != null) {
		// System.out.println(line);
		// }
		// } catch (Exception e) {
		// System.out.println(e.toString());
		// e.printStackTrace();
		// }
	}// end out.
}
