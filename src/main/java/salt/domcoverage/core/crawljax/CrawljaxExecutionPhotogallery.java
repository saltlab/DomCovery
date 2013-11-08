package salt.domcoverage.core.crawljax;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import salt.domcoverage.core.utils.ConstantVars;

import com.crawljax.browser.EmbeddedBrowser.BrowserType;
import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.BrowserConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration.CrawljaxConfigurationBuilder;
import com.crawljax.core.configuration.Form;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.plugins.crawloverview.CrawlOverview;

public class CrawljaxExecutionPhotogallery {

	public static void main(String[] args) throws IOException, URISyntaxException {
		photogalleryCall();
	}

	private static void photogalleryCall() {
		String URL = "http://localhost:8888/phormer-photoGallery331";
		InputSpecification inputSpecification = getGalleryInputSpecification();
		int time = 5;

		callCrawljax(URL, inputSpecification, time, ConstantVars.ESTIMATIONFILE);
	}

	private static void callCrawljax(String URL, InputSpecification inputSpecification, int time, String sfgLocation) {
		long WAIT_TIME_AFTER_EVENT = 3000;
		long WAIT_TIME_AFTER_RELOAD = 3000;

		CrawljaxConfigurationBuilder builder = CrawljaxConfiguration.builderFor(URL);
		builder.crawlRules().clickDefaultElements();
		// builder.crawlRules().click("span", "div", "p", "a", "input", "button", "li", "img");
		// builder.crawlRules().clickOnce(false);
		// builder.crawlRules().insertRandomDataInInputForms(true);
		builder.crawlRules().crawlFrames(true);
		// builder.crawlRules().followExternalLinks(false);

		// gallery:
		builder.crawlRules().dontClick("a").withText("Log Out");
		builder.crawlRules().dontClick("a").withAttribute("href", "?page=logout");
		builder.crawlRules().dontClick("a").withAttribute("target", "_blank");
		builder.crawlRules().dontClick("a").withAttribute("title", "RSS Feed");
		builder.crawlRules().dontClick("div").withAttribute("class", "back2mainR");

		builder.crawlRules().setInputSpec(inputSpecification);

		CrawlOverview plugin = new CrawlOverview();
		deleteOutDirectory();
		builder.addPlugin(plugin);

		builder.addPlugin(new DomComparatorPlugin());
		// builder.addPlugin(new SerializeSFGPlugin(new File(sfgLocation)));

		builder.setUnlimitedCrawlDepth();
		builder.setUnlimitedStates();

		builder.setMaximumRunTime(time, TimeUnit.SECONDS);
		builder.crawlRules().insertRandomDataInInputForms(false);
		builder.crawlRules().waitAfterReloadUrl(WAIT_TIME_AFTER_RELOAD, TimeUnit.MILLISECONDS);
		builder.crawlRules().waitAfterEvent(WAIT_TIME_AFTER_EVENT, TimeUnit.MILLISECONDS);
		builder.setBrowserConfig(new BrowserConfiguration(BrowserType.FIREFOX, 1));
		CrawljaxRunner crawljax = new CrawljaxRunner(builder.build());
		crawljax.call();
	}

	private static void deleteOutDirectory() {
		try {
			FileUtils.deleteDirectory(new File("out"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static InputSpecification getGalleryInputSpecification() {
		InputSpecification input = new InputSpecification();
		Form loginForm = new Form();
		loginForm.field("loginAdminPass").setValues("editor");
		input.setValuesInForm(loginForm).beforeClickElement("input").withAttribute("class", "submit");
		return input;
	}
}
