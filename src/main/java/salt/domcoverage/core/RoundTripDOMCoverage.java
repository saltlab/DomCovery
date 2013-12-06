package salt.domcoverage.core;

import java.util.List;
import java.util.Map;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;
import salt.domcoverage.core.codeanalysis.AssertionBeforeAfterInstrumentor;
import salt.domcoverage.core.codeanalysis.DriverInstrumentor;
import salt.domcoverage.core.codeanalysis.Instrumentor;
import salt.domcoverage.core.codeanalysis.TestInstrumentor;
import salt.domcoverage.core.dom.DomComparatorUsingSchema;
import salt.domcoverage.core.dom.DomMerger;
import salt.domcoverage.core.dom.clustering.DataClustererWithRelativeSimilarity;
import salt.domcoverage.core.metrics.ClickableElements;
import salt.domcoverage.core.metrics.DomInterStateCoverage;
import salt.domcoverage.core.metrics.DomInterStateCoverageEasy;
import salt.domcoverage.core.metrics.ElementCoverage;
import salt.domcoverage.core.task.TaskUtil;
import salt.domcoverage.core.utils.CompileUtil;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.TestUtil;

public class RoundTripDOMCoverage {

	public void instrument(String testLocationFolder) {
		// instrument drivers of tests
		DriverInstrumentor driverInstrumentor = new DriverInstrumentor();
		driverInstrumentor.instrument(testLocationFolder);

		// instrument calls in tests
		TestInstrumentor tci = new TestInstrumentor();
		tci.instrument(testLocationFolder);

		// instrument assertions
		Instrumentor asser = new AssertionBeforeAfterInstrumentor();
		asser.instrument(testLocationFolder);

		// Utils.sleep(5000);

		// re-compile tests
		// CompileUtil.compileFiles(testLocationFolder);

		// Utils.sleep(5000);
	}

	// execute test
	public void execute(String testLocationFolder, String outputFolder) {
		List<String> allTests = TestUtil.getAllTests(testLocationFolder, ".java");
		execute(allTests, outputFolder);
	}

	public void execute(List<String> allTests, String outputFolder) {

		TaskUtil.setUpDomcoveryExecution(outputFolder);

		TestUtil.executeUnitTests(allTests);

		// merge the doms
		DomMerger dm = new DomMerger(new DomComparatorUsingSchema(), new DataClustererWithRelativeSimilarity());

		List<ElementData> doms = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);

		dm.merge(doms);

		// getclickable elements
		if (ConstantVars.Clickable_element_collection == true) {
			ClickableElements ce = new ClickableElements();
			ce.findClickableElements(allTests);
		}
		// DomInterStateCoverageEasy domStateCoverage = new DomInterStateCoverageEasy();
		DomInterStateCoverage domStateCoverage = new DomInterStateCoverage();
		Map<String, String> coverage = domStateCoverage.getDomStateAndTransitionCoverage();
		// coverage.putAll();

		// /ElementCoverage ec = new ElementCoverage();
		// coverage.putAll(ec.getCoverageOffilesAccordingToCoverageTrue(ConstantVars.MERGEDLOCATION));

		// just print coverge hashmap;
		// printMap(coverage);
		// integrate with crawljax and overviewplugin

		TaskUtil.cleanUpAfterRunningDomcovery(outputFolder);

	}

	private void printMap(Map<String, Double> coverage) {
		for (String type : coverage.keySet()) {
			System.out.println(type + ": " + coverage.get(type));
		}

	}

}
