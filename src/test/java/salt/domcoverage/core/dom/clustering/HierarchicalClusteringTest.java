package salt.domcoverage.core.dom.clustering;

import java.util.List;

import org.junit.Test;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;
import salt.domcoverage.core.dom.DomComparator;
import salt.domcoverage.core.dom.DomComparatorUsingCoveredElements;
import salt.domcoverage.core.metrics.ElementCoverage;
import salt.domcoverage.core.utils.ConstantVars;

public class HierarchicalClusteringTest {

	@Test
	public void clusterTest() {

		DomComparator comparator = new DomComparatorUsingCoveredElements();
		List<ElementData> doms = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);
		double[][] distances = comparator.extractDistances(doms);

		DataHierarchicalClustering dataCluster = new DataHierarchicalClustering();
		// List<List<String>> clusters =
		// dataCluster.clusterDataReturnInStrings(doms, distances);
		// DomMerger dm = new DomMerger();

		// List<String> doms = dm.mergeDOMsofClusters(clusters);

		// Utils.writeArrayToFiles(doms, ConstantVars.MERGEDLOCATION);

		// getcoverage

		ElementCoverage ec = new ElementCoverage();
		ec.getCoverageOffilesAccordingToCoverageTrue(ConstantVars.MERGEDLOCATION);

	}
}