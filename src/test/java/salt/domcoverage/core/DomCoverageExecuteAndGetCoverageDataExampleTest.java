package salt.domcoverage.core;

import java.util.List;

import org.junit.Test;

import salt.domcoverage.casestudies.photogallery.PhotogalleryTestSourceLocations;
import salt.domcoverage.core.dom.DomMerger;
import salt.domcoverage.core.domcomparison.clustering.DataClusterer;
import salt.domcoverage.core.metrics.ElementCoverage;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.Utils;

public class DomCoverageExecuteAndGetCoverageDataExampleTest {

	@Test
	public void instrumentRunGetCoverageofOneTestCase() {

		String test = PhotogalleryTestSourceLocations.add_story_assert;// PhotogalleryTestSourceLocations.TestAddCategory;

		// instrument test add_Category
		// TestCaseInstrumentor tci = new TestCaseInstrumentor();
		// tci.instrument(test);

		// execute test

		new TestExecutor().execute(test);

		// merge doms
		DataClusterer dataClusterer = new DataClusterer();
		List<List<String>> clusters = dataClusterer.clusterDataInFolder(ConstantVars.COVERAGE_LOCATION);

		DomMerger dm = new DomMerger();

		List<String> doms = dm.mergeDOMsofClusters(clusters);

		Utils.writeArrayToFiles(doms, ConstantVars.MERGEDLOCATION);

		// getcoverage

		ElementCoverage ec = new ElementCoverage();
		ec.getCoverageOffilesAccordingToCoverageTrue(ConstantVars.MERGEDLOCATION);

	}
}
