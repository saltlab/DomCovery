package salt.domcoverage.core.dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.Difference;
import org.w3c.dom.Document;

import salt.domcoverage.core.utils.DOMUtility;

import com.crawljax.oraclecomparator.comparators.PlainStructureComparator;
import com.crawljax.util.DOMComparer;
import com.crawljax.util.DomUtils;

public class DomComparator {

	public int differencesSize(String dom1, String dom2) {
		return differences(dom1, dom2).size();
	}

	public List<Difference> differences(String dom1, String dom2) {
		List<Difference> differences = new ArrayList<Difference>();
		try {
			String dom11 = new PlainStructureComparator(false).normalize(dom1);
			String dom22 = new PlainStructureComparator(false).normalize(dom2);
			// remove coverage=true
			dom11 = dom11.replace("coverage=\"true\"", "");
			dom22 = dom22.replace("coverage=\"true\"", "");
			Document dom1doc = DomUtils.asDocument(dom11);
			// dom1doc.getElementsByTagName("*").getLength();
			Document dom2doc = DomUtils.asDocument(dom22);
			DOMComparer dc = new DOMComparer(dom1doc, dom2doc);
			differences = dc.compare();
			// System.out.println("difference size: "+ differences.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return differences;

	}

	public int differencesConsideringCoveredElement(String dom1, File file) throws IOException {
		String element_dom1 = DOMUtility.getElementAccessedInDOM(dom1);

		boolean contains = false;
		if (!element_dom1.equals("")) {
			contains = DOMUtility.contains(element_dom1, file);
		}

		if (contains) {
			return 1;
		} else
			return 10;
	}

	public double[][] extractDistancesByCountringTheDifferences(ArrayList<File> domFiles) {
		double[][] distances = new double[domFiles.size()][domFiles.size()];
		try {
			File difffile = new File("differences.csv");
			FileUtils.deleteQuietly(difffile);
			for (int i = 0; i < domFiles.size(); i++) {
				for (int j = 0; j < domFiles.size(); j++) {
					// int size =
					// differencesSize(FileUtils.readFileToString(domFiles.get(i)),
					// FileUtils.readFileToString(domFiles.get(j)));
					String filei = FileUtils.readFileToString(domFiles.get(i));
					String filej = FileUtils.readFileToString(domFiles.get(j));
					int diff = differencesSize(filei, filej);
					distances[i][j] = diff;
					String stringtoWrite = domFiles.get(i).getName() + "," + domFiles.get(j).getName() + "," + diff;
					// System.out.println(stringtoWrite);
					FileUtils.writeStringToFile(difffile, stringtoWrite + "\n", true);
				}
				FileUtils.writeStringToFile(difffile, "\n", true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return distances;
	}

	public double[][] extractDistances(ArrayList<File> domFiles) {
		double[][] distances = new double[domFiles.size()][domFiles.size()];
		try {
			File difffile = new File("differences.csv");
			FileUtils.deleteQuietly(difffile);
			for (int i = 0; i < domFiles.size(); i++) {
				for (int j = 0; j < domFiles.size(); j++) {
					// int size =
					// differencesSize(FileUtils.readFileToString(domFiles.get(i)),
					// FileUtils.readFileToString(domFiles.get(j)));
					int diff = differencesConsideringCoveredElement(domFiles.get(i).getName(), domFiles.get(j));
					distances[i][j] = diff;
					String stringtoWrite = domFiles.get(i).getName() + "," + domFiles.get(j).getName() + "," + diff;
					// System.out.println(stringtoWrite);
					FileUtils.writeStringToFile(difffile, stringtoWrite + "\n", true);
				}
				FileUtils.writeStringToFile(difffile, "\n", true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return distances;
	}

}
