package salt.domcoverage.core;

import org.junit.Test;

import salt.domcoverage.core.utils.ConstantVars;

public class DomCoverageExecuteAndGetCoverageDataExampleTest {

	@Test
	public void execute() {

		final long startTime = System.currentTimeMillis();

		// String testLocationFolder = TestConstantVars.clarolineTestLocation4test;
		// String testLocationFolder = TestConstantVars.theorgnizerlocation;
		// String testLocationFolder = TestConstantVars.photogalleryTestLocation;
		String testLocationFolder = TestConstantVars.saltlabTestFuncLocation;

		// String testLocationFolder = System.getProperty("user.dir") +
		// "/src/main/java/salt/domcoverage/casestudies/photogallery/instrumentedtask1";
		// ConstantVars.Clickable_element_collection = false;
		ConstantVars.Clickable_element_collection = true;

		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		rtdc.execute(testLocationFolder, "DomcoveryOutput-saltwclickable-5mincrawl/");

		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));

	}
}