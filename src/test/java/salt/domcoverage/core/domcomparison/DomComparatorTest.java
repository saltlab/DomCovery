package salt.domcoverage.core.domcomparison;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.Difference;
import org.junit.*;

import com.apporiented.algorithm.clustering.Cluster;

import salt.domcoverage.core.domcomparison.clustering.DataClustering;

public class DomComparatorTest {

	@Ignore
	@Test
	public void test() throws IOException {
		DomComparator dc = new DomComparator();
		String dom1 = FileUtils.readFileToString(new File("Coverage/salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory_DOM_2013-07-23_02-33-56"));
		String dom2 = FileUtils.readFileToString(new File("Coverage/salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory_DOM_2013-07-23_02-33-57"));
		
		String dom3 = FileUtils.readFileToString(new File("Coverage/salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory_DOM_2013-07-23_02-34-26"));
		//dc.compare(dom1, dom2);
	
	}
	@Test
	public void testalldomsincoveragefolder() throws IOException {
		
		DomComparator dc= new DomComparator();
		ArrayList<File> domFiles = dc.getDomFiles("Coverage");
		double[][] distances = dc.extractDistances(domFiles);

		DataClustering dataClustering = new DataClustering();
		HashSet<ArrayList<Cluster>> clusterData = dataClustering.clusterData(domFiles,distances);
		
		System.out.println("");
		
	
	}
	
}
