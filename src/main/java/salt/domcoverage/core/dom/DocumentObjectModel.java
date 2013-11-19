package salt.domcoverage.core.dom;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import salt.domcoverage.core.utils.ConstantVars;

import com.crawljax.util.DomUtils;
import com.google.common.collect.Lists;

import flex.messaging.io.ArrayList;

public class DocumentObjectModel {

	private String DOM;

	public DocumentObjectModel(String dom) {
		DOM = dom;
	}

	public DocumentObjectModel(File file) {
		try {
			DOM = FileUtils.readFileToString(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getNumberofAllClickables() {

		Document document = getW3cDocument();
		NodeList elemA = document.getElementsByTagName("a");
		NodeList elemI = document.getElementsByTagName("input");
		NodeList elemB = document.getElementsByTagName("button");
		NodeList elemIm = document.getElementsByTagName("img");

		return elemA.getLength() + elemB.getLength() + elemI.getLength() + elemIm.getLength();
	}

	public int getAllElementsSize() {
		List<Node> allElements = getAllElements();
		return allElements.size();
	}

	public List<Node> getAllElements() {
		int all = 0;
		Document w3cdoc = getW3cDocument();
		// NodeList body = w3cdoc.getElementsByTagName("body");
		// if (body == null)
		// body = w3cdoc.getElementsByTagName("BODY");

		all = w3cdoc.getElementsByTagName("*").getLength();

		NodeList elements = w3cdoc.getElementsByTagName("*");
		List<Node> listofnodes = new ArrayList();
		int bodyelementorder = 0;
		for (int i = 0; i < elements.getLength(); i++) {
			Node item = elements.item(i);
			if (item.getNodeName().toLowerCase().contains("body")) {
				bodyelementorder = i + 1;
				break;
			}
			// System.out.println("elem: " + item);

		}
		// System.out.println(" body element order numbercounting: " +
		// bodyelementorder);
		int numberofCountingItems = 0;
		for (int i = bodyelementorder - 1; i < elements.getLength(); i++) {
			Node item = elements.item(i);
			if (item.getFirstChild() != null && !containsText(item.getFirstChild().getNodeValue()))
				continue;
			if (countingItem(item.getNodeName())) {
				listofnodes.add(item);
				numberofCountingItems++;
			}
		}
		return listofnodes;
	}

	private boolean containsText(String nodeValue) {
		if (nodeValue == null)
			return false;
		String s = new String(nodeValue);
		s = s.replace(" ", "");
		s = s.replaceAll("\\r\\n|\\r|\\n", " ");
		return s.equals("");
	}

	private boolean countingItem(String nodeName) {
		for (String s : ConstantVars.ELEMENTS_TO_COUNT) {
			if (nodeName.trim().toLowerCase().equals(s))
				return true;
		}
		return false;
	}

	private Document getW3cDocument() {
		Document asDocument = null;
		try {
			asDocument = DomUtils.asDocument(DOM);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return asDocument;
	}

	public int getElementAccessedSizeInDOMThroughAttribute(String attribute) {
		Elements elements = getElementAccessedInDOMThroughAttribute(attribute);
		return elements.size();

	}

	public Elements getElementAccessedInDOMThroughAttribute(String attribute) {
		org.jsoup.nodes.Document doc = Jsoup.parse(DOM);
		Elements elements = doc.select("[" + attribute + "]");

		return elements;
	}

	public Elements getAllElementsUsingJsoup() {
		org.jsoup.nodes.Document doc = Jsoup.parse(DOM);
		Elements elements = doc.getAllElements();
		Elements out = new Elements();
		for (Element element : elements) {
			if (countingItem(element.nodeName()))
				out.add(element);
		}
		return out;
	}

	public int getAllElementsAndInterserctionwithClickableElements() {
		Elements clickableElements = getElementAccessedInDOMThroughAttribute(ConstantVars.clickableCoverageAttribute + "=true");
		Elements allElements = getAllElementsUsingJsoup();
		int sizeall = allElements.size();
		// for (Element element : allElements) {
		// if(element.hasAttr(ConstantVars.clickableCoverageAttribute))
		// }
		boolean addAll = allElements.addAll(clickableElements);
		// List intersection = ListUtils.intersection(allElements, clickableElements);

		return allElements.size() - sizeall + clickableElements.size();
	}
}
