package salt.domcoverage.core;

import org.junit.Test;

public class DomCoverageExecuteAndGetCoverageDataExampleTest {

	@Test
	public void execute() {

		final long startTime = System.currentTimeMillis();

		// String testLocationFolder = TestConstantVars.clarolineTestLocation4test;
		// String testLocationFolder = TestConstantVars.theorgnizerlocation;
		// String testLocationFolder = TestConstantVars.photogalleryTestLocation;
		String testLocationFolder = TestConstantVars.add_story_assert;
		// String testLocationFolder = System.getProperty("user.dir") + "/src/main/java/salt/domcoverage/casestudies/photogallery/instrumentedtask1";

		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		rtdc.execute(testLocationFolder, "DomcoveryOutput/");

		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));

	}
}