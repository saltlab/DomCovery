package salt.domcoverage.casestudies.photogallery.task2;

import salt.domcoverage.core.RoundTripDOMCoverage;

public class DomCoveryTask2 {

	public static void main(String argv[]) {

		final long startTime = System.currentTimeMillis();

		int taskNumber = 2;

		String testLocationFolder = System.getProperty("user.dir") + "/src/main/java/salt/domcoverage/casestudies/photogallery/instrumentedtask" + taskNumber;

		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		rtdc.execute(testLocationFolder, "DomcoveryOutput/Task" + taskNumber + "/");

		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));

	}
}