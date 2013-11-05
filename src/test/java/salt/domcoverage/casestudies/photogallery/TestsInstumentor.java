package salt.domcoverage.casestudies.photogallery;

import org.junit.Test;

import salt.domcoverage.core.TestConstantVars;
import salt.domcoverage.core.codeanalysis.TestInstrumentor;

public class TestsInstumentor {

	@Test
	public void instrumentTestSuite() {

		TestInstrumentor tci = new TestInstrumentor();
		tci.instrument(TestConstantVars.photogalleryTestLocation);

	}

}
