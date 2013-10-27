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

		// String EXAMPLE_TEST = "This is my small example string which I'm going to use for pattern matching.";
		//
		// Pattern pattern = Pattern.compile("\\w+");
		// // In case you would like to ignore case sensitivity you could use this
		// // statement
		// // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
		// Matcher matcher = pattern.matcher(EXAMPLE_TEST);
		// // check all occurance
		// while (matcher.find()) {
		// System.out.print("Start index: " + matcher.start());
		// System.out.print(" End index: " + matcher.end() + " ");
		// System.out.println(matcher.group());
		// }
		// // now create a new pattern and matcher to replace whitespace with tabs
		// Pattern replace = Pattern.compile("\\s+");
		// Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
		// System.out.println(matcher2.replaceAll("\t"));

		String bys = "[[FirefoxDriver: firefox on MAC (da123290-9260-464c-b384-3b4a72f8602a)] -> id: photoBoxes]";
		// System.out.println(bys);
		Pattern pattern1 = Pattern.compile("-> (.*): (.*)");
		Matcher matcher1 = pattern1.matcher(bys);
		boolean find = matcher1.find();
		// System.out.println(find);
		String by0 = matcher1.group(1);
		String by1 = matcher1.group(2);
		String[] matches = bys.split("] -> (.*): (.*)");

		// FirefoxDriver driver = new FirefoxDriver();
		// String url = "file://localhost/Users/mehdimir/Desktop/Dropbox/UBC-research/development/git/DomCoverage/MergedState/12.html";
		// driver.get(url);
		//
		// File scrFile = driver.getScreenshotAs(OutputType.FILE);
		// // Now you can do whatever you need to do with it, for example copy somewhere
		// url = "/Users/mehdimir/Desktop/Dropbox/UBC-research/development/git/DomCoverage/MergedState/12.html";
		// FileUtils.copyFile(scrFile, new File(url.replace(".html", ".jpg")));
		//
		// // driver.getScreenshotAs(target) url.replace(".html", ".jpg"));
		// // new DomStateCoverage().addBorder(ConstantVars.CRAWLJAX_IMAGES + "/" + "1" + "_small.jpg");
		//
		// // String domStr = FileUtils.readFileToString(new File("1-afterSrtip"));
		// // // System.out.println(domStr);
		// // String st = DOMUtility.removeTagName(domStr, "body");
		// // System.out.println(st);
	}

}
