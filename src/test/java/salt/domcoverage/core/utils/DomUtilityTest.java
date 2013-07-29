package salt.domcoverage.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.crawljax.util.DomUtils;
import com.crawljax.util.XPathHelper;

public class DomUtilityTest {

	// @Ignore
	@Test
	public void testDomUtilfromCrawljax() throws IOException, XPathExpressionException {

		String domStr = FileUtils.readFileToString(new File("salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory.html"));
		System.out.println(domStr);
		Document dom = DomUtils.asDocument(domStr);
		assertNotNull(dom);
		NodeList evaluateXpathExpression = XPathHelper.evaluateXpathExpression(dom, "//*[@id=\"loginAdminPass\"]");
		System.out.println(evaluateXpathExpression.getLength());

	}

	@Test
	public void testsimplehtml() throws IOException {

		final String html = "<body><div id='firstdiv'></div><div><span id='thespan'>" + "<a id='thea'>test</a></span></div></body>";

		Document dom = DomUtils.asDocument(html);
		assertNotNull(dom);

		// first div
		String expectedXpath = "/HTML[1]/BODY[1]/DIV[1]";
		String xpathExpr = XPathHelper.getXPathExpression(dom.getElementById("firstdiv"));
		assertEquals(expectedXpath, xpathExpr);
	}
}
