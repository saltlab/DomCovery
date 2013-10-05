package salt.domcoverage.core;

import java.util.List;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;
import salt.domcoverage.core.codeanalysis.DriverInstrumentor;
import salt.domcoverage.core.codeanalysis.TestInstrumentor;
import salt.domcoverage.core.dom.DomComparatorUsingSchema;
import salt.domcoverage.core.dom.DomMerger;
import salt.domcoverage.core.dom.clustering.DataClustererWithRelativeSimilarity;
import salt.domcoverage.core.metrics.DomStateCoverage;
import salt.domcoverage.core.metrics.ElementCoverage;
import salt.domcoverage.core.task.TaskUtil;
import salt.domcoverage.core.utils.CompileUtil;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.TestUtil;

public class RoundTripDOMCoverage {

	public void instrument(String testLocationFolder) {
		// instrument drivers of tests
		DriverInstrumentor driverInstrumentor = new DriverInstrumentor();
		driverInstrumentor.instrumentTestSuite(testLocationFolder);

		// instrument tests
		TestInstrumentor tci = new TestInstrumentor();
		tci.instrumentTestSuite(testLocationFolder);
		// Utils.sleep(5000);

		// re-compile tests
		// CompileUtil.compileFiles(testLocationFolder);

		// Utils.sleep(5000);
	}

	// execute test

	public void execute(String testLocationFolder, String outputFolder) {

		TaskUtil.setUpDomcoveryExecution(outputFolder);

		List<String> allTests = TestUtil.getAllTests(testLocationFolder, ".java");

		TestUtil.executeUnitTests(allTests);

		// merge the doms
		DomMerger dm = new DomMerger(new DomComparatorUsingSchema(), new DataClustererWithRelativeSimilarity());

		List<ElementData> doms = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);

		List<String> merge = dm.merge(doms);

		// getcoverage

		DomStateCoverage domStateCoverage = new DomStateCoverage();
		domStateCoverage.getDomStateCoverage();

		ElementCoverage ec = new ElementCoverage();
		ec.getCoverageOffilesAccordingToCoverageTrue(ConstantVars.MERGEDLOCATION);

		TaskUtil.cleanUpAfterRunningDomcovery(outputFolder);

	}

}
