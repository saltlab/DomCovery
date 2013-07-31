package salt.domcoverage.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import salt.domcoverage.core.code2instrument.DomCoverageClass;
import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;

import com.crawljax.oraclecomparator.comparators.PlainStructureComparator;

public class DOMUtility {

	public static int getNumberoofAllClickables(Document document) {

		NodeList elemA = document.getElementsByTagName("a");
		NodeList elemI = document.getElementsByTagName("input");
		NodeList elemB = document.getElementsByTagName("button");

		return elemA.getLength() + elemB.getLength() + elemI.getLength();
	}

	public static ArrayList<File> getDomFiles(String folder) {
		Collection<File> files = FileUtils.listFiles(new File(folder), null, false);
		ArrayList<File> domFiles = new ArrayList<File>();
		for (File file : files) {
			if (file.getName().contains(".html"))
				domFiles.add(file);
		}
		return domFiles;
	}

	public static String getDomFile(String domFileName) {
		String element = "";
		try {
			element = FileUtils.readFileToString(new File(ConstantVars.COVERAGE_LOCATION + domFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}

	public static File getDomFileoffileName(String domFileName) {
		return FileUtils.getFile(ConstantVars.COVERAGE_LOCATION + domFileName);
	}

	public static String getElementAccessedInDOM(String domFileName) {
		String elementFileName = domFileName.replace("DOM", "ELEMENT");
		elementFileName = elementFileName.replace(".html", ".txt");
		String element = "";
		try {
			element = FileUtils.readFileToString(new File(ConstantVars.COVERAGE_LOCATION + elementFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}

	public static int getElementAccessedInDOMThroughCoverageTrueAttribute(String dom) {
		org.jsoup.nodes.Document doc = Jsoup.parse(dom);
		Elements elements = doc.select("[coverage=true]");
		return elements.size();

	}

	public static String normalizeDOM(String dom) {
		// System.out.println(readFileToString);
		dom = new PlainStructureComparator(false).normalize(dom);
		dom = dom.toLowerCase();
		dom = dom.replace("coverage=\"true\"", "");
		dom = dom.replace("  ", " ");

		return dom;
	}

	public static boolean contains(String element_dom1, File file) {
		String readFileToString = "";
		try {
			readFileToString = FileUtils.readFileToString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		readFileToString = DOMUtility.normalizeDOM(readFileToString);
		// System.out.println(readFileToString);
		element_dom1 = DOMUtility.normalizeDOM(element_dom1);
		return readFileToString.contains(element_dom1);

	}

	public static String replace(String elementAccessedInDOM, String accessedDom, String mergedDom) {
		// get element accesess method
		String by = getByFromDOM(accessedDom);
		// add attribute to it

		// DomCoverageClass dc = new DomCoverageClass();
		DomCoverageClass.setDom(null);
		String ret = DomCoverageClass.getModifiedElementInDOM(by, mergedDom);
		if (ret == null)
			return mergedDom;
		return ret;
	}

	private static String getByFromDOM(String dom) {

		List<ElementData> elementsFromFile = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);
		for (ElementData elementData : elementsFromFile) {
			if (elementData.getDomFileName().equals(dom)) {
				String by = elementData.getBy().toLowerCase();
				by = by.replace("by.", "By.");
				return by;
			}
		}
		return null;
	}
}
