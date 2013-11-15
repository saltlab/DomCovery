package salt.domcoverage.casestudies.claroline.task1;

import salt.domcoverage.core.RoundTripDOMCoverage;

public class DomCoveryTask1 {

	public static void main(String argv[]) {

		final long startTime = System.currentTimeMillis();

		int taskNumber = 1;

		String testLocationFolder = System.getProperty("user.dir") + "/src/main/java/salt/domcoverage/casestudies/claroline/instrumentedtask" + taskNumber;

		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		rtdc.execute(testLocationFolder, "DomcoveryOutput/Claroline/Task" + taskNumber + "/");

		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));

	}
}