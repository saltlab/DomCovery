package salt.domcoverage.core.dom;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;
import salt.domcoverage.core.dom.clustering.DataClustererWithRelativeSimilarity;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.Utils;

public class DomMergerTest {

	// @Ignore
	@Test
	public void testMergingDoms() {

		// List<List<String>> clusters =
		// dataClusterer.clusterDataInFolder(ConstantVars.COVERAGE_LOCATION, new
		// DomComparatorUsingCoveredElements());

		DomMerger dm = new DomMerger(new DomComparatorUsingSchema(), new DataClustererWithRelativeSimilarity());

		List<ElementData> doms = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);

		List<String> merge = dm.merge(doms);
		// List<String> doms = dm.mergeDOMsofClusters(clusters);

		Utils.writeArrayToFiles(merge, ConstantVars.MERGEDLOCATION);

		assertNotNull(doms);

	}

}
