package salt.domcoverage.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;

import salt.domcoverage.core.code2instrument.DomCoverageClass;
import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;

import com.crawljax.oraclecomparator.comparators.PlainStructureComparator;

public class DOMUtility {

	public static ArrayList<File> getFilesInDirectoryWithExtension(String folder, String extension) {
		Collection<File> files = FileUtils.listFiles(new File(folder), null, false);
		ArrayList<File> domFiles = new ArrayList<File>();
		for (File file : files) {
			if (file.getName().contains(extension))
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

	public static String normalizeDOM(String dom, boolean stripattributes) {
		// System.out.println(readFileToString);
		dom = new PlainStructureComparator(stripattributes).normalize(dom);
		// dom = dom.toLowerCase();
		dom = dom.replace(" coverage=\"true\"", "");
		dom = dom.replace("coverage=\"true\"", "");
		dom = dom.replace("  ", " ");
		dom = dom.replace("\"=\"\"", "");
		dom = dom.replace("'", "");
		// remove all comments
		dom = removeAllComments(dom);
		return dom;
	}

	private static String removeAllComments(String dom) {
		// dom = dom.replaceAll(">(.*?)<", "><");
		dom = dom.replaceAll("<!--(.*?)-->", "");

		return dom;
	}

	public static boolean contains(String element_dom1, File file) {
		String readFileToString = "";
		try {
			readFileToString = FileUtils.readFileToString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		readFileToString = DOMUtility.normalizeDOM(readFileToString, false);
		// System.out.println(readFileToString);
		element_dom1 = DOMUtility.normalizeDOM(element_dom1, false);
		return readFileToString.contains(element_dom1);

	}

	public static String replace(String by, String mergedDom) {
		// get element accesess method
		// String by = getByFromDOM(accessedDom);
		// add attribute to it

		// DomCoverageClass dc = new DomCoverageClass();
		DomCoverageClass.setDom(null);
		String ret = DomCoverageClass.getModifiedElementInDOM(by, mergedDom);
		if (ret == null) {
			System.out.println("ret is null");
			return mergedDom;
		}
		return ret;
	}

	private static String getByFromDOM(String dom) {

		List<ElementData> elementsFromFile = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);
		for (ElementData elementData : elementsFromFile) {
			if (elementData.getDomFileName().equals(dom)) {
				String by = elementData.getBy();// .toLowerCase();
				// by = by.replace("by.", "By.");
				return by;
			}
		}
		return null;
	}

	public static boolean isInvalidDom(Document DOM) {
		if (DOM == null)
			return false;
		String dom = DOM.getTextContent();
		if (dom == null)
			return false;
		if (dom.length() < ConstantVars.MINIMUM_LENGTH_OF_DOM)
			return false;
		return true;
	}

}
