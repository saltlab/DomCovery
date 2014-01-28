package salt.domcoverage.core;

import org.junit.Test;

public class DomCoverageInstrumentExampleTest {

	@Test
	public void instrumentTestSuite() {

		final long startTime = System.currentTimeMillis();

		// String testLocationFolder = TestConstantVars.add_story_assert;
		// String testLocationFolder = System.getProperty("user.dir") +
		// "/src/main/java/salt/domcoverage/casestudies/photogallery/instrumentedtask0";
		// String testLocationFolder = System.getProperty("user.dir") +
		// "/src/main/java/salt/domcoverage/casestudies/photogallery/tests4correlationDomcoveryinstrumented";
		// String testLocationFolder = System.getProperty("user.dir") + "/src/main/java/salt/domcoverage/casestudies/saltlabinstrumented";
		// String testLocationFolder = System.getProperty("user.dir") +
		// "/src/main/java/salt/domcoverage/casestudies/claroline/instrumentedtask4";

		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		String testLocationFolder = TestConstantVars.saltlabTestFuncLocation;
		rtdc.instrument(testLocationFolder);

		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));

	}

}
