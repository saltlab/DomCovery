package salt.domcoverage.core.metrics;

import org.junit.Test;

import salt.domcoverage.core.utils.ConstantVars;

public class ElementCoverageTest {

	@Test
	public void testElements() {
		ElementCoverage ec = new ElementCoverage();
		ec.getCoverageOffilesAccordingToCoverageTrue(ConstantVars.MERGEDLOCATION);

	}

}
