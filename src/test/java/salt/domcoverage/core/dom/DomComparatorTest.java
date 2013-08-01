package salt.domcoverage.core.dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import salt.domcoverage.core.dom.DomComparator;
import salt.domcoverage.core.dom.clustering.DataClusterer;
import salt.domcoverage.core.utils.DOMUtility;
import salt.domcoverage.core.utils.Utils;

public class DomComparatorTest {

	@Ignore
	@Test
	public void test() throws IOException {
		DomComparator dc = new DomComparator();
		String dom1 = FileUtils.readFileToString(new File("Coverage/salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory_DOM_2013-07-23_02-33-56"));
		String dom2 = FileUtils.readFileToString(new File("Coverage/salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory_DOM_2013-07-23_02-33-57"));

		String dom3 = FileUtils.readFileToString(new File("Coverage/salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory_DOM_2013-07-23_02-34-26"));
		// dc.compare(dom1, dom2);

	}

	@Test
	public void testalldomsincoveragefolder() throws IOException {

		DomComparator dc = new DomComparator();
		ArrayList<File> domFiles = DOMUtility.getFilesInDirectoryWithExtension("Coverage", ".html");
		double[][] distances = dc.extractDistances(domFiles);
		System.out.println("distance: \n");
		Utils.print2DArray(distances);
		// DataHierarchicalClustering dataClustering = new
		// DataHierarchicalClustering();
		// HashSet<ArrayList<Cluster>> clusterData =
		// dataClustering.clusterData(domFiles, distances);
		DataClusterer dataC = new DataClusterer();
		dataC.clusterData(domFiles, distances);

		System.out.println("");

	}

}
