package salt.domcoverage.core;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import salt.domcoverage.core.metrics.ElementCoverageTest;

public class DomCoverageExampleTest {

	@Test
	public void instrumentRunGetCoverageofOneTestCase() {
		//instrument test
		
		
		//execute test
		JUnitCore.runClasses(ElementCoverageTest.class);
		
		
		//getcoverage
		

	}

}
