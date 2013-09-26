package salt.domcoverage.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import salt.domcoverage.core.metrics.DomStateCoverage;

import com.crawljax.util.DomUtils;
import com.crawljax.util.XPathHelper;

public class TempTest {

	// @Ignore
	@Test
	public void testDomUtilfromCrawljax() throws IOException, XPathExpressionException {

		FirefoxDriver driver = new FirefoxDriver();
		String url = "file://localhost/Users/mehdimir/Desktop/Dropbox/UBC-research/development/git/DomCoverage/MergedState/12.html";
		driver.get(url);

		File scrFile = driver.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		url = "/Users/mehdimir/Desktop/Dropbox/UBC-research/development/git/DomCoverage/MergedState/12.html";
		FileUtils.copyFile(scrFile, new File(url.replace(".html", ".jpg")));

		// driver.getScreenshotAs(target) url.replace(".html", ".jpg"));
		// new DomStateCoverage().addBorder(ConstantVars.CRAWLJAX_IMAGES + "/" + "1" + "_small.jpg");

		// String domStr = FileUtils.readFileToString(new File("1-afterSrtip"));
		// // System.out.println(domStr);
		// String st = DOMUtility.removeTagName(domStr, "body");
		// System.out.println(st);
	}

}
