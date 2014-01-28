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

public class CrawljaxExecutionSaltLab {

	public static void main(String[] args) throws IOException, URISyntaxException {
		// String URL = "http://watersmc.ece.ubc.ca:8888/claroline-1.11.7/";
		crwalCall();
		// photogalleryCall();
	}

	private static void crwalCall() {
		int time = 10;
		int numberOfBrowsers = 5;
		String serializedFile = ConstantVars.ESTIMATIONFILE;

		String URL = "http://salt.ece.ubc.ca/";

		CrawljaxConfigurationBuilder builder = CrawljaxConfiguration.builderFor(URL);

		builder.crawlRules().clickDefaultElements();
		builder.crawlRules().click("span", "div", "p", "a", "input", "button", "li", "img");
		builder.crawlRules().clickOnce(false);
		builder.crawlRules().insertRandomDataInInputForms(true);
		builder.crawlRules().crawlFrames(true);

		CrawlOverview plugin = new CrawlOverview();
		deleteOutDirectory();
		builder.addPlugin(plugin);

		builder.addPlugin(new DomComparatorPlugin());
		builder.addPlugin(new SerializeSFGPlugin(new File(serializedFile)));

		builder.setUnlimitedCrawlDepth();
		builder.setUnlimitedStates();
		long WAIT_TIME_AFTER_EVENT = 3000;
		long WAIT_TIME_AFTER_RELOAD = 3000;

		builder.setMaximumRunTime(time, TimeUnit.MINUTES);
		builder.crawlRules().insertRandomDataInInputForms(false);
		builder.crawlRules().waitAfterReloadUrl(WAIT_TIME_AFTER_RELOAD, TimeUnit.MILLISECONDS);
		builder.crawlRules().waitAfterEvent(WAIT_TIME_AFTER_EVENT, TimeUnit.MILLISECONDS);
		builder.setBrowserConfig(new BrowserConfiguration(BrowserType.FIREFOX, numberOfBrowsers));
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

}
