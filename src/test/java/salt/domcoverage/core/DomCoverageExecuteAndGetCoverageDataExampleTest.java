package salt.domcoverage.core;

import java.util.List;

import org.junit.Test;

import salt.domcoverage.core.dom.DomMerger;
import salt.domcoverage.core.dom.clustering.DataClusterer;
import salt.domcoverage.core.metrics.ElementCoverage;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.Utils;

public class DomCoverageExecuteAndGetCoverageDataExampleTest {

	@Test
	public void instrumentRunGetCoverageofOneTestCase() {

		// execute test
		// List<String> allTests =
		// TestUtil.getAllTests(TestConstantVars.clarolineTestLocation);
		// for (String test : allTests) {
		// new TestExecutor().execute(test);
		// }

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