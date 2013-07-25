package salt.domcoverage.core;

import org.junit.Test;

import salt.domcoverage.casestudies.photogallery.PhotogalleryTestSourceLocations;
import salt.domcoverage.core.metrics.ElementCoverage;

public class DomCoverageExecuteAndGetCoverageDataExampleTest {

	@Test
	public void instrumentRunGetCoverageofOneTestCase() {

		String test = PhotogalleryTestSourceLocations.TestAddCategory;

		// instrument test add_Category
		// TestCaseInstrumentor tci = new TestCaseInstrumentor();
		// tci.instrument(test);

		// execute test

		new TestExecutor().execute(test);

		// getcoverage

		ElementCoverage ec = new ElementCoverage();
		ec.getCoverage("Coverage");

	}
}
