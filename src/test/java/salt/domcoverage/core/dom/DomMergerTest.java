package salt.domcoverage.core.dom;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import salt.domcoverage.core.domcomparison.clustering.DataClusterer;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.Utils;

public class DomMergerTest {

	@Test
	public void testMergigDoms() {

		DataClusterer dataClusterer = new DataClusterer();
		List<List<String>> clusters = dataClusterer.clusterDataInFolder(ConstantVars.COVERAGE_LOCATION);

		DomMerger dm = new DomMerger();

		List<String> doms = dm.mergeDOMsofClusters(clusters);

		Utils.writeArrayToFiles(doms, ConstantVars.MERGEDLOCATION);

		assertNotNull(doms);

	}

}
