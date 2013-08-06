package salt.domcoverage.core.dom;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.Utils;

public class DomComparatorTest {

	@Ignore
	@Test
	public void test() throws IOException {
		DomComparatorUsingCoveredElements dc = new DomComparatorUsingCoveredElements();
		String dom1 = FileUtils.readFileToString(new File("Coverage/salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory_DOM_2013-07-23_02-33-56"));
		String dom2 = FileUtils.readFileToString(new File("Coverage/salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory_DOM_2013-07-23_02-33-57"));

		String dom3 = FileUtils.readFileToString(new File("Coverage/salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory_DOM_2013-07-23_02-34-26"));
		// dc.compare(dom1, dom2);

	}

	@Test
	public void testalldomsincoveragefolder() throws IOException {

		DomComparator dc = new DomComparatorUsingSchema();
		List<ElementData> doms = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);
		double[][] distances = dc.extractDistances(doms);
		System.out.println("distance: \n");
		Utils.print2DArray(distances);
		// DataHierarchicalClustering dataClustering = new
		// DataHierarchicalClustering();
		// HashSet<ArrayList<Cluster>> clusterData =
		// dataClustering.clusterData(domFiles, distances);

		// DataClusterer dataC = new DataClusterer();
		// dataC.clusterData(domFiles, distances);

		System.out.println("");

	}

}
