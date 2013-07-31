package salt.domcoverage.core;

import org.junit.After;
import org.junit.Test;

import salt.domcoverage.casestudies.photogallery.PhotogalleryTestSourceLocations;
import salt.domcoverage.core.codeanalysis.TestCaseInstrumentor;

public class DomCoverageInstrumentExampleTest {

	@Test
	public void instrumentOneTestCase() {

		String test = PhotogalleryTestSourceLocations.add_story_assert;// TestAddCategory;

		// instrument test add_Category
		TestCaseInstrumentor tci = new TestCaseInstrumentor();
		tci.instrument(test);

	}

	@After
	public void tearDown() {

		// Resources;
	}
}
