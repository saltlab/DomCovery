package salt.domcoverage.core;

import org.junit.Test;

public class DomCoverageExecuteAndGetCoverageDataExampleTest {

	@Test
	public void execute() {

		final long startTime = System.currentTimeMillis();

		String testLocationFolder = TestConstantVars.clarolineTestLocation4test;
		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		rtdc.execute(testLocationFolder);

		
		final long endTime = System.currentTimeMillis();

			System.out.println("Total execution time: " + (endTime - startTime) );

	}
}