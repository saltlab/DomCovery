package salt.domcoverage.core.task;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import salt.domcoverage.core.utils.ConstantVars;

public class TaskUtil {
	public static void setUpDomcoveryExecution(String taskLocation) {
		// remove Coverage folder
		try {
			FileUtils.deleteQuietly(new File(ConstantVars.COVERAGE_LOCATION));

			FileUtils.copyDirectory(new File(ConstantVars.CRAWLOVERVIEW), new File(ConstantVars.CRAWLOVERVIEW + "temp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void cleanUpAfterRunningDomcovery(String taskLocation) {
		// move mergeDom, coverageReport, and out to TASK's folder
		try {
			FileUtils.deleteQuietly(new File(taskLocation + "Domcovery-states"));

			FileUtils.copyDirectory(new File(ConstantVars.MERGEDLOCATION), new File(taskLocation + "Domcovery-states"));
			FileUtils.copyFile(new File(ConstantVars.DomCoverageCriteria), new File(taskLocation + ConstantVars.DomCoverageCriteria));

			// copy css files of photogallery to the mergedDom folder
			FileUtils.copyDirectory(new File("extrafilesToCopy/photogallery"), new File(taskLocation + "Domcovery-states"));

			// move out directory to task1
			FileUtils.deleteQuietly(new File(taskLocation + "Domcovery-DomStates-report"));
			FileUtils.copyDirectory(new File(ConstantVars.CRAWLOVERVIEW), new File(taskLocation + "Domcovery-DomStates-report"));
			// copy back the temp to out directory
			FileUtils.copyDirectory(new File(ConstantVars.CRAWLOVERVIEW + "temp"), new File(ConstantVars.CRAWLOVERVIEW));
			FileUtils.deleteQuietly(new File(ConstantVars.CRAWLOVERVIEW + "temp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
