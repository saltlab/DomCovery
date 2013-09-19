package salt.domcoverage.core;

import org.junit.Test;

public class DomCoverageInstrumentExampleTest {

	@Test
	public void instrumentTestSuite() {

		final long startTime = System.currentTimeMillis();

		String testLocationFolder = TestConstantVars.clarolineTestLocation4test;
		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		rtdc.instrument(testLocationFolder);

		
		
		final long endTime = System.currentTimeMillis();

			System.out.println("Total execution time: " + (endTime - startTime) );

	}

}
