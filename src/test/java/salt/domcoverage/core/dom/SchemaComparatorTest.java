package salt.domcoverage.core.dom;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;
import salt.domcoverage.core.dom.clustering.DataClustererWithRelativeSimilarity;
import salt.domcoverage.core.utils.ConstantVars;

public class SchemaComparatorTest {
	@Ignore
	@Test
	public void test() throws IOException {

		// get two files
		// s1, s2 :geenrate schemas of file1 and file2 separately
		DomComparator sc = new DomComparatorUsingSchema();

		// String f1 =
		// "Coverage/salt.domcoverage.casestudies.claroline.add_class.testAddClass_DOM_2013-08-03_11-11-33.975.html";//
		// "src/test/resources/test-data.xml";
		String f2 = "Coverage/salt.domcoverage.casestudies.claroline.add_class.testAddClass_DOM_2013-08-03_11-11-35.278.html";//
		// "src/test/resources/test-data2.xml";
		// String f2 =
		// "Coverage/salt.domcoverage.casestudies.claroline.add_class.testAddClass_DOM_2013-08-03_11-11-51.479.html";//
		// "src/test/resources/test-data2.xml";
		String f1 = "Coverage/salt.domcoverage.casestudies.claroline.add_class.testAddClass_DOM_2013-08-03_11-12-28.042.html";
		String s1 = FileUtils.readFileToString(new File(f1));
		String s2 = FileUtils.readFileToString(new File(f2));

		// double size = sc.differences(s1, s2);
		// comapre schemas of s1 and s2
		// System.out.println(size);
		// if deviation is above treshold,

	}

	// @Ignore
	@Test
	public void testallfiles() {

		DomComparatorUsingSchema sc = new DomComparatorUsingSchema();
		double difference = 0;
		DomMerger dm = new DomMerger(new DomComparatorUsingSchema(), new DataClustererWithRelativeSimilarity());

		List<ElementData> doms = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);

		double[][] extractDistances = sc.extractDistances(doms);

		// for (int i = 0; i < files.size() - 1; i++) {
		// File f1 = files.get(i);
		// File f2 = files.get(i + 1);
		// System.out.format("differences: %s and %s", f1, f2);
		// difference = sc.differencesFiles(f1, f2);
		// System.out.println(difference);
		//
		// }

	}
}
