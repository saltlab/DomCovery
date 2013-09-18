package salt.domcoverage.core;

import org.junit.Test;

public class DomCoverageRoundTripTest {

	@Test
	public void instrumentRunGetCoverageofOneTestCase() {
		String testLocationFolder = TestConstantVars.clarolineTestLocation4test;

		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		rtdc.performRoundtripActions(testLocationFolder);

	}

}