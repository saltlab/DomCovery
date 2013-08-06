package salt.domcoverage.core;

import org.junit.Test;

import salt.domcoverage.core.metrics.ElementCoverage;
import salt.domcoverage.core.utils.ConstantVars;

public class DomCoverageExecuteAndGetCoverageDataExampleTest {

	@Test
	public void instrumentRunGetCoverageofOneTestCase() {

		// execute test
		// List<String> allTests =
		// TestUtil.getAllTests(TestConstantVars.clarolineTestLocation);
		// for (String test : allTests) {
		// new TestExecutor().execute(test);
		// }

		// / Utils.writeArrayToFiles(doms, ConstantVars.MERGEDLOCATION);

		// getcoverage

		ElementCoverage ec = new ElementCoverage();
		ec.getCoverageOffilesAccordingToCoverageTrue(ConstantVars.MERGEDLOCATION);

	}
}