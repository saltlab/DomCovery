package salt.domcoverage.core;

import org.junit.Before;
import org.junit.Test;

public class DomCoverageRoundTripTest {

	RoundTripDOMCoverage rtdc;
	String testLocationFolder;

	@Before
	public void setUp() throws Exception {
		// testLocationFolder = TestConstantVars.clarolineTestLocation4test;
		testLocationFolder = TestConstantVars.theorgnizerlocation;
		rtdc = new RoundTripDOMCoverage();
	}

	@Test
	public void Zexecute() {
		rtdc.instrument(testLocationFolder);
		rtdc.execute(testLocationFolder, "DomcoveryOutput");
	}

}