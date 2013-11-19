package salt.domcoverage.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import salt.domcoverage.core.code2instrument.DomCoverageClass;
import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;
import bsh.commands.dir;

import com.crawljax.oraclecomparator.comparators.PlainStructureComparator;

public class DOMUtility {

	public static ArrayList<File> getFilesInDirectoryWithExtension(String folder, String extension) {
		ArrayList<File> domFiles = new ArrayList<File>();

		File directory = new File(folder);
		if (!directory.isDirectory()) {
			domFiles.add(directory);
			return domFiles;
		}
		Collection<File> files = FileUtils.listFiles(directory, null, false);
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

	public static void main(String argv[]) {
		try {
			String readFileToString = FileUtils.readFileToString(new File("/Users/mehdimir/Desktop/Dropbox/UBC-research/development/git/DomCoverage/DomcoveryOutput/gallery/Task1/Domcovery-states/salt.domcoverage.casestudies.photogallery.instrumentedtask1.EIGHT.testLoginLogOut_DOM_2013-11-15_12-51-07.707.html"));
			System.out.println(normalizeDOM(readFileToString, false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String normalizeDOM(String dom, boolean stripattributes) {
		// System.out.println(readFileToString);
		dom = new PlainStructureComparator(stripattributes).normalize(dom);
		// dom = dom.toLowerCase();
		dom = removeCoverageTrue(dom, ConstantVars.clickableCoverageAttribute);
		dom = removeCoverageTrue(dom, ConstantVars.assertedCoverageAttribute);
		dom = removeCoverageTrue(dom, ConstantVars.indirectCoverageAttribute);
		dom = removeCoverageTrue(dom, ConstantVars.directCoverageAttribute);
		dom = dom.replace("  ", " ");
		dom = dom.replace("\"=\"\"", "");
		dom = dom.replace("'", "");
		// remove all comments
		dom = removeAllComments(dom);
		return dom;
	}

	private static String removeCoverageTrue(String dom, String cov) {
		String tro1 = "=true";
		String tro2 = "=\"true\"";
		String tro3 = "= true";
		dom = dom.replace(cov + tro1, "");
		dom = dom.replace(cov + tro2, "");
		dom = dom.replace(cov + tro3, "");

		return dom;
	}

	public static String removeTagName(String data, String tagname) {
		StringBuilder regex = new StringBuilder("<" + tagname + "[^>]*>(.*?)</" + tagname + ">");
		int flags = Pattern.MULTILINE | Pattern.DOTALL | Pattern.CASE_INSENSITIVE;
		Pattern pattern = Pattern.compile(regex.toString(), flags);
		Matcher matcher = pattern.matcher(data);
		return matcher.replaceAll("");
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
			System.out.println("ret is null for by: " + by);
			return mergedDom;
		}
		System.out.println("ret is not null for by: " + by);
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

	public static String replaceIndirectCoverageElements(String mergedDom, String dom) {
		org.jsoup.nodes.Document docDom = Jsoup.parse(dom);
		org.jsoup.nodes.Document docMergedDom = Jsoup.parse(mergedDom);
		Elements elements = new Elements();
		elements = docDom.select("[" + ConstantVars.indirectCoverageAttribute + "=true]");
		for (org.jsoup.nodes.Element element : elements) {
			element.removeAttr(ConstantVars.indirectCoverageAttribute);
			element.removeAttr(ConstantVars.directCoverageAttribute);
			org.jsoup.nodes.Element elementInMergedDom = DOMUtility.elementExistsInDom(docMergedDom, element);
			if (elementInMergedDom != null) {
				System.out.println("containts!");
				elementInMergedDom.attr(ConstantVars.indirectCoverageAttribute, "true");
			}
			// DOM = element.ownerDocument().outerHtml();
		}
		String returnDom = docMergedDom.ownerDocument().outerHtml();
		return returnDom;
	}

	private static Element elementExistsInDom(org.jsoup.nodes.Document docMergedDom, Element find) {
		for (org.jsoup.nodes.Element element : docMergedDom.getAllElements()) {
			if (prepare(element).equals(prepare(find)))
				return element;
		}
		return null;
	}

	private static String prepare(Element element) {
		String s = element.toString().replace(" ", "").toLowerCase();
		int ind = (s.length() > 40) ? 40 : s.length();
		System.out.println("element to comapre: " + s.substring(0, ind) + "  ........");
		return s;
	}

}
