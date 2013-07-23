package salt.domcoverage.core.code2instrument;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.xml.xpath.XPathExpressionException;

import org.apache.xml.serializer.utils.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.crawljax.util.XPathHelper;

public class DomCoverageClass {

	public enum Type {
		XPATH, ID, NAME, CLASS, CSS, OTHERS
	};

	private static String DOM = "";

	public static By collectData(By by, String dom, String testName) {
		// xpathhelper
		// jsoup
		DOM = dom;
		ArrayList<String> elements = new ArrayList<String>();
		switch (byType(by)) {
		case XPATH:
			elements.addAll(getElementsByXPATHInString(dom, getXpath(by)));
		default:
			elements.addAll(getElementsUsingJsoup(dom, by));
		}

		for (String string : elements) {
			System.out.println("element: " + string);
		}
		String time = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());

		new ElementData(time, testName, by.toString(), DOM, elements);

		return by;
	}

	private static ArrayList<String> getElementsUsingJsoup(String dom, By by) {
		ArrayList<String> elementsString = new ArrayList<String>();

		Document doc = Jsoup.parse(dom);
		Elements elements = new Elements();
		switch (byType(by)) {
		case ID:
			elements = doc.select("#" + getString(by));
			break;
		case NAME:
			elements = doc.select("" + getString(by));
			break;
		case CLASS:
			elements = doc.select("." + getString(by));
			break;
		case CSS:
			elements = doc.select("." + getString(by));
			break;
		}
		for (Element element : elements) {
			element.attr("coverage", "true");
			DOM = element.ownerDocument().outerHtml();
			elementsString.add(element.toString());
		}
		return elementsString;
	}

	private static String getString(By by) {
		String s = by.toString().substring(by.toString().indexOf(" "));
		return s.trim();
	}

	private static Type byType(By by) {
		String bystr = by.toString();
		if (bystr.contains("By.xpath:"))
			return Type.XPATH;
		if (bystr.contains("By.id:"))
			return Type.ID;
		if (bystr.contains("By.name:"))
			return Type.NAME;
		if (bystr.contains("By.class:"))
			return Type.CLASS;
		if (bystr.contains("By.css:"))
			return Type.CSS;
		// if (bystr.contains("By.xpath:"))
		return Type.OTHERS;
	}

	private static String getXpath(By by) {
		String xpath = by.toString().substring(by.toString().indexOf(" ") + 1, by.toString().length());
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
			evaluateXpathExpression = XPathHelper.evaluateXpathExpression(dom, by);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("length: " + evaluateXpathExpression.getLength());
		for (int j = 0; j < evaluateXpathExpression.getLength(); j++) {
			Node item = evaluateXpathExpression.item(j);

			Element e = (Element) item;
			e.attr("coverage", "true");
			DOM = e.ownerDocument().outerHtml();
			// System.out.println(XPathHelper.getXPathExpression(item));
		}
		return evaluateXpathExpression;
	}

	public static String capitalizeXpath(String s) {
		return XPathHelper.formatXPath(s);
//		String newXpath = "";
//		int flag = 0;
//
//		for (int i = 0; i < s.length(); i++) {
//			char c = s.charAt(i);
//			if ((c != '@' && flag == 0)) {
//				c = Character.toUpperCase(c);
//			} else if (c == '@' || flag == 1) {
//				flag = 1;
//				if (c == ']')
//					flag = 0;
//
//			}
//			newXpath += Character.toString(c);
//		}
//		return newXpath;
	}

	static void getNameofTest() {

		;

		// System.out.println("name:"+ new
		// Object(){}.getClass().getEnclosingMethod().getName()+this.getClass().getName);
	}

}
