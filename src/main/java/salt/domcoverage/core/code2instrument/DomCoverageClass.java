package salt.domcoverage.core.code2instrument;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import salt.domcoverage.core.utils.ConstantVars;

import com.crawljax.util.DomUtils;
import com.crawljax.util.XPathHelper;

public class DomCoverageClass {

	public enum Type {
		XPATH, ID, NAME, CLASS, CSS, OTHERS, LINKTEXT, TAGNAME
	};

	private static String DOM;

	public String getDOM() {
		return DOM;
	}

	public static By collectData(By by, String dom, String testName) {
		// xpathhelper
		// jsoup
		DOM = dom;
		System.out.println(DOM);
		ArrayList<String> elements = getElementsofDOM(by.toString(), dom);

		for (String string : elements) {
			System.out.println("element: " + string);
		}
		// try {
		// FileUtils.write(new
		// File("salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory.html"),
		// DOM);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		String time = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss.SSS").format(new Date());
		// String recordedDomFileName = byalreadyExistInRecordedDoms(by);
		// if (recordedDomFileName != null) {
		// new ElementDataPersist(time, testName, by.toString(), DOM,
		// recordedDomFileName, elements);
		// } else
		new ElementDataPersist(time, testName, by.toString(), DOM, "", elements);

		// else
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		return by;
	}

	public static String getModifiedElementInDOM(String string, String dom) {
		getElementsofDOM(string, dom);
		return DOM;
	}

	public static ArrayList<String> getElementsofDOM(String string, String dom) {
		ArrayList<String> elements = new ArrayList<String>();

		switch (byType(string)) {
		case XPATH:
			elements.addAll(getElementsByXPATHInString(dom, getXpath(string)));
			break;
		default:
			elements.addAll(getElementsUsingJsoup(dom, string));
		}
		return elements;
	}

	private static ArrayList<String> getElementsUsingJsoup(String dom, String by) {
		ArrayList<String> elementsString = new ArrayList<String>();
		// dom = dom.toLowerCase();
		Document doc = Jsoup.parse(dom);
		Elements elements = new Elements();
		String byString = getString(by);
		// byString = byString.toLowerCase();
		switch (byType(by)) {
		case ID:
			elements = doc.select("#" + byString);
			break;
		case NAME:
			elements = doc.select("[name=" + byString + "]");
			break;
		case CLASS:
			elements = doc.select("." + byString);
			break;
		case TAGNAME:
			elements = doc.select(byString);
			break;
		case CSS:
			String str = byString.replace("\"", "");
			elements = doc.select(str);
			// System.out.println("css selector : " + byString);
			// System.out.println("element found: " + elements);
			break;
		case LINKTEXT:
			elements = doc.select("a:contains(" + byString + ")");
			break;
		}
		for (org.jsoup.nodes.Element element : elements) {
			if (!element.hasAttr("coverage")) {
				element.attr("coverage", "true");
				DOM = element.ownerDocument().outerHtml();
			}
			elementsString.add(element.toString());
		}
		return elementsString;
	}

	public static String getString(By by) {
		String s = by.toString();
		return getString(s);
	}

	public static String getString(String by) {
		String s = by.substring(by.indexOf(" "));
		return s.trim();
	}

	private static Type byType(String bystr) {
		System.out.println("bystr1: " + bystr);
		// bystr = bystr.toLowerCase().replace("by.", "By.");
		System.out.println("bystr2: " + bystr);
		if (bystr.contains("By.xpath:"))
			return Type.XPATH;
		if (bystr.contains("By.id:"))
			return Type.ID;
		if (bystr.contains("By.name:"))
			return Type.NAME;
		if (bystr.contains("By.tag:"))
			return Type.NAME;
		if (bystr.contains("By.class:"))
			return Type.CLASS;
		if (bystr.contains("By.selector:"))
			return Type.CSS;
		if (bystr.contains("By.linkText:") || bystr.contains("By.linktext:") || bystr.contains("By.partiallinktext:"))
			return Type.LINKTEXT;
		// if (bystr.contains("By.xpath:"))
		return Type.OTHERS;
	}

	private static String getXpath(String by) {
		String xpath = by.substring(by.toString().indexOf(" ") + 1, by.toString().length());
		return capitalizeXpath(xpath.trim());
	}

	private static ArrayList<String> getElementsByXPATHInString(String dom, String by) {
		ArrayList<String> elements = new ArrayList<String>();
		NodeList evaluateXpathExpression = getElementsByXPATH(dom, by);
		for (int j = 0; j < evaluateXpathExpression.getLength(); j++) {
			elements.add(XPathHelper.getXPathExpression(evaluateXpathExpression.item(j)));
		}
		return elements;
	}

	private static NodeList getElementsByXPATH(String dom, String by) {
		// System.out.println("page source: "+driver.getPageSource());
		NodeList evaluateXpathExpression = null;
		try {
			org.w3c.dom.Document documentDom;
			documentDom = DomUtils.asDocument(dom);
			try {
				evaluateXpathExpression = XPathHelper.evaluateXpathExpression(documentDom, by);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// FileUtils.write(new
			// File("salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory.html"),
			// dom);
			// System.out.println("length: " +
			// evaluateXpathExpression.getLength());
			for (int j = 0; j < evaluateXpathExpression.getLength(); j++) {
				Node item = evaluateXpathExpression.item(j);
				Element e = (Element) item;
				if (e.getAttribute("coverage") == "") {
					e.setAttribute("coverage", "true");
					DOM = DomUtils.getDocumentToString(e.getOwnerDocument());
				}
				// System.out.println(DOM);
				// DOM = e.getOwnerDocument().getTextContent();
				// System.out.println(XPathHelper.getXPathExpression(item));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return evaluateXpathExpression;
	}

	public static String capitalizeXpath(String s) {
		return XPathHelper.formatXPath(s);
		// String newXpath = "";
		// int flag = 0;
		//
		// for (int i = 0; i < s.length(); i++) {
		// char c = s.charAt(i);
		// if ((c != '@' && flag == 0)) {
		// c = Character.toUpperCase(c);
		// } else if (c == '@' || flag == 1) {
		// flag = 1;
		// if (c == ']')
		// flag = 0;
		//
		// }
		// newXpath += Character.toString(c);
		// }
		// return newXpath;
	}

	public static String byalreadyExistInRecordedDoms(By by) {
		// get all doms in coverage folder
		File directory = new File(ConstantVars.COVERAGE_LOCATION);
		if (!directory.exists())
			return null;
		String[] m = { "html" };
		Iterator<File> iterateFiles = FileUtils.iterateFiles(directory, m, false);
		for (Iterator<File> iterator = iterateFiles; iterator.hasNext();) {
			File file = (File) iterator.next();
			if (!file.getName().contains(".html"))
				continue;
			String dom = "";
			try {
				dom = FileUtils.readFileToString(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<String> elements = getElementsofDOM(by.toString(), dom);
			if (elements.size() != 0) {
				// DOM = dom;
				return file.getName();
			}

		}
		// return the dom that has current element in it
		// othervise retrun null
		return null;
	}

	static void getNameofTest() {

		;

		// System.out.println("name:"+ new
		// Object(){}.getClass().getEnclosingMethod().getName()+this.getClass().getName);
	}

	public static void setDom(String dom) {
		DOM = dom;
	}

}
