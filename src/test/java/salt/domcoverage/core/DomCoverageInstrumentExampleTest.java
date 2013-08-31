package salt.domcoverage.core;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import salt.domcoverage.casestudies.photogallery.PhotogalleryTestSourceLocations;
import salt.domcoverage.core.codeanalysis.TestInstrumentor;

public class DomCoverageInstrumentExampleTest {

	@Ignore
	@Test
	public void instrumentOneTestCase() {

		String test = PhotogalleryTestSourceLocations.add_story_assert;// TestAddCategory;

		// instrument test add_Category
		TestInstrumentor tci = new TestInstrumentor();
		tci.instrument(test);

	}

	@Ignore
	@Test
	public void instrumentTestSuite() {

		TestInstrumentor tci = new TestInstrumentor();
		tci.instrumentTestSuite(TestConstantVars.clarolineTestLocation);

	}

	@Test
	public void instrumentTestClassofJavascriptCoveredElements() {

		TestInstrumentor tci = new TestInstrumentor();
		tci.instrument(TestConstantVars.jsCoveredElement);

	}

	@After
	public void tearDown() {

		// Resources;
	}
}
