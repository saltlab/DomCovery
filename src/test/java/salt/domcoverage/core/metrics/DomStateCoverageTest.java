package salt.domcoverage.core.metrics;

import static org.junit.Assert.*;

import org.junit.Test;

public class DomStateCoverageTest {

	@Test
	public void testCoverage() {
		DomInterStateCoverage domStateCoverage = new DomInterStateCoverage();
		domStateCoverage.getDomStateAndTransitionCoverage();
	}
}
