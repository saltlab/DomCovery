package salt.domcoverage.core.crawljax;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import com.crawljax.browser.EmbeddedBrowser.BrowserType;
import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.BrowserConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration.CrawljaxConfigurationBuilder;
import com.crawljax.core.configuration.Form;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.plugins.crawloverview.CrawlOverview;

public class DomEstimation {

	public static void main(String[] args) throws IOException, URISyntaxException {
		String URL = "http://watersmc.ece.ubc.ca:8888/claroline-1.11.7/";
		long WAIT_TIME_AFTER_EVENT = 400;
		long WAIT_TIME_AFTER_RELOAD = 400;

		CrawljaxConfigurationBuilder builder = CrawljaxConfiguration.builderFor(URL);

		builder.crawlRules().setInputSpec(getInputSpecification());

		CrawlOverview plugin = new CrawlOverview();
		deleteOutDirectory();
		builder.addPlugin(plugin);

		builder.setMaximumRunTime(1, TimeUnit.MINUTES);
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

	private static InputSpecification getInputSpecification() {
		InputSpecification input = new InputSpecification();
		Form loginForm = new Form();
		loginForm.field("login").setValues("nainy");
		loginForm.field("password").setValues("nainy");
		// contactForm.field("female").setValues(false, true);
		// contactForm.field("name").setValues("Bob", "Alice", "John");
		// contactForm.field("phone").setValues("1234567890", "1234888888", "");
		// contactForm.field("mobile").setValues("123", "3214321421");
		// contactForm.field("type").setValues("Student", "Teacher");
		// contactForm.field("active").setValues(true);
		input.setValuesInForm(loginForm).beforeClickElement("button").withText("Enter");
		return input;
	}

}
