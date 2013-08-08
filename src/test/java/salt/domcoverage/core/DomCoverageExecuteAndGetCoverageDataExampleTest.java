package salt.domcoverage.core;

import java.util.List;

import org.junit.Test;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;
import salt.domcoverage.core.dom.DomComparatorUsingSchema;
import salt.domcoverage.core.dom.DomMerger;
import salt.domcoverage.core.dom.clustering.DataClustererWithRelativeSimilarity;
import salt.domcoverage.core.metrics.ElementCoverage;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.TestUtil;

public class DomCoverageExecuteAndGetCoverageDataExampleTest {

	@Test
	public void instrumentRunGetCoverageofOneTestCase() {

		// execute test
		List<String> allTests = TestUtil.getAllTests(TestConstantVars.clarolineTestLocation);
		for (String test : allTests) {
			new TestExecutor().execute(test);
		}

		// merge the doms
		DomMerger dm = new DomMerger(new DomComparatorUsingSchema(), new DataClustererWithRelativeSimilarity());

		List<ElementData> doms = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);

		List<String> merge = dm.merge(doms);

		// getcoverage

		ElementCoverage ec = new ElementCoverage();
		ec.getCoverageOffilesAccordingToCoverageTrue(ConstantVars.MERGEDLOCATION);

	}
}