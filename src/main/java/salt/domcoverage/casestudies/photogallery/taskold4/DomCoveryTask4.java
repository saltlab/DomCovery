package salt.domcoverage.casestudies.photogallery.taskold4;

import salt.domcoverage.core.RoundTripDOMCoverage;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.Utils;

public class DomCoveryTask4 {

	public static void main(String argv[]) {

		// ////////////////inputs
		int taskNumber = 4;
		String crawljaxReportFolder = "outtask" + taskNumber;

		String testLocationFolder = System.getProperty("user.dir") + "/src/main/java/salt/domcoverage/casestudies/photogallery/instrumentedtask" + taskNumber;
		final long startTime = System.currentTimeMillis();

		Utils.updatecrawljaxlocation(crawljaxReportFolder);
		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		ConstantVars.Clickable_element_collection = false;
		rtdc.execute(testLocationFolder, "DomcoveryOutput/gallery/Task" + taskNumber + "/");

		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));

	}
}