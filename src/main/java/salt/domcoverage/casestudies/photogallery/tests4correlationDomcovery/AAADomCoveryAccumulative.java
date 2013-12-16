package salt.domcoverage.casestudies.photogallery.tests4correlationDomcovery;

import java.io.File;
import java.util.List;

import flex.messaging.io.ArrayList;
import salt.domcoverage.core.RoundTripDOMCoverage;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.DOMUtility;
import salt.domcoverage.core.utils.Utils;

public class AAADomCoveryAccumulative {

	public static void main(String argv[]) {

		// ////////////////inputs
		ConstantVars.Clickable_element_collection = false;

		List<String> testClassNames = new ArrayList();
		testClassNames.add("CategoryDeleteTest");// 0
		testClassNames.add("TC2");// 1
		testClassNames.add("add_story_assert");// 2
		testClassNames.add("add_story_assert");// 3
		testClassNames.add("SlideShowTest");// 4
		testClassNames.add("Add_story");// 5
		testClassNames.add("MainViewTest");// 6
		testClassNames.add("Admin_login_and_logout");// 7
		testClassNames.add("CategoryAddTest");// 8
		testClassNames.add("login_log_out");// 9
		testClassNames.add("HomePageTest");// 10
		testClassNames.add("SlideShowPauseTest");// 11
		testClassNames.add("TestUrl");// 12

		for (int i = 1; i <= testClassNames.size(); i++) {
			coverageforTestClasses(testClassNames.subList(0, i));
			Utils.resetProgramVariables();
		}

	}

	private static void coverageforTestClasses(List<String> testClassNames) {
		String outName = "" + (testClassNames.size() - 1);
		// ConstantVars.Clickable_element_collection =true;
		String testLocationFolder = System.getProperty("user.dir") + "/src/main/java/salt/domcoverage/casestudies/photogallery/tests4correlationDomcoveryinstrumented/";
		final long startTime = System.currentTimeMillis();

		String crawljaxReportFolder = "outgallery";
		Utils.updatecrawljaxlocation(crawljaxReportFolder);
		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();

		List<String> tests2run = getFileNamesofTestClassNames(testLocationFolder, testClassNames);
		rtdc.execute(tests2run, "DomcoveryOutput/gallery/" + outName + "/");

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