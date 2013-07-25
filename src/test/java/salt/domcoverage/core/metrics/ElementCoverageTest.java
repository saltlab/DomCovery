package salt.domcoverage.core.metrics;

import org.junit.Test;

public class ElementCoverageTest {

	@Test
	public void testElements() {
		ElementCoverage ec = new ElementCoverage();
		ec.getCoverage("Coverage");

	}

}
