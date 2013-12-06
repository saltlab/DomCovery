package salt.domcoverage.casestudies.claroline.originaltests4DomcoveryInstrumented;

import java.io.File;
import java.util.List;

import flex.messaging.io.ArrayList;
import salt.domcoverage.core.RoundTripDOMCoverage;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.DOMUtility;
import salt.domcoverage.core.utils.Utils;

public class AAADomCoveryTask0 {

	public static void main(String argv[]) {

		// ////////////////inputs
		ConstantVars.Clickable_element_collection = false;

		List<String> testClassNames = new ArrayList();
		testClassNames.add("Login_logout");
		testClassNames.add("Exercise");
		testClassNames.add("N");
		testClassNames.add("AddCategory_pass");
		testClassNames.add("add_class");
		testClassNames.add("view_profile");
		testClassNames.add("assign");
		testClassNames.add("Tc3");// 7
		// testClassNames.add("Manage");// 8
		// testClassNames.add("course_category_edit");//10
		// testClassNames.add("EditTextZone");//11
		// testClassNames.add("send_message");//12
		// testClassNames.add("s");//13
		// testClassNames.add("Support");//14
		// testClassNames.add("wiki");//15
		// testClassNames.add("AddPhone");//16
		// testClassNames.add("AddNewCategory");//17
		// testClassNames.add("course_desc");//18
		// testClassNames.add("DeletingCategory");//19
		// testClassNames.add("announcement");//20
		String outName = "01234567-";
		// ConstantVars.Clickable_element_collection =true;
		String testLocationFolder = System.getProperty("user.dir") + "/src/main/java/salt/domcoverage/casestudies/claroline/originaltests4DomcoveryInstrumented/";
		final long startTime = System.currentTimeMillis();

		String crawljaxReportFolder = "outclaroline";
		Utils.updatecrawljaxlocation(crawljaxReportFolder);
		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();

		List<String> tests2run = getFileNamesofTestClassNames(testLocationFolder, testClassNames);
		rtdc.execute(tests2run, "DomcoveryOutput/claroline/" + outName + "/");

		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));

	}

	private static List<String> getFileNamesofTestClassNames(String testLocationFolder, List<String> testClassNames) {
		List<String> ret = new ArrayList();
		List<File> filesInDirectoryWithExtension = DOMUtility.getFilesInDirectoryWithExtension(testLocationFolder, ".java");
		for (File file : filesInDirectoryWithExtension) {
			for (String testClass : testClassNames) {
				if (file.getName().equals(testClass + ".java")) {
					ret.add(file.getAbsolutePath());
					continue;
				}
			}
		}
		return ret;

	}
}