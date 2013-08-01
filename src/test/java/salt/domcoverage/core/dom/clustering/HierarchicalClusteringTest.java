package salt.domcoverage.core.dom.clustering;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import salt.domcoverage.core.dom.DomComparator;
import salt.domcoverage.core.dom.DomMerger;
import salt.domcoverage.core.dom.clustering.DataHierarchicalClustering;
import salt.domcoverage.core.metrics.ElementCoverage;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.DOMUtility;
import salt.domcoverage.core.utils.Utils;

public class HierarchicalClusteringTest {

	@Test
	public void clusterTest() {

		DomComparator comparator = new DomComparator();
		ArrayList<File> domFiles = DOMUtility.getFilesInDirectoryWithExtension("Coverage", ".html");
		double[][] distances = comparator.extractDistancesByCountringTheDifferences(domFiles);

		DataHierarchicalClustering dataCluster = new DataHierarchicalClustering();
		List<List<String>> clusters = dataCluster.clusterDataReturnInStrings(domFiles, distances);
		DomMerger dm = new DomMerger();

		List<String> doms = dm.mergeDOMsofClusters(clusters);

		Utils.writeArrayToFiles(doms, ConstantVars.MERGEDLOCATION);

		// getcoverage

		ElementCoverage ec = new ElementCoverage();
		ec.getCoverageOffilesAccordingToCoverageTrue(ConstantVars.MERGEDLOCATION);

	}
}