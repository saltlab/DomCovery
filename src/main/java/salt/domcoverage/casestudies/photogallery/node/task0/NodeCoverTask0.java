package salt.domcoverage.casestudies.photogallery.node.task0;

//import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import salt.domcoverage.core.utils.Utils;

//import org.openqa.selenium.support.ui.Select;

public class NodeCoverTask0 {
	private WebDriver driver;
	// private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void openBrowser() {
		// // testCases++;
		// Proxy proxy = new Proxy().setHttpProxy("localhost:8000");
		// DesiredCapabilities cap = new DesiredCapabilities();
		// cap.setCapability(CapabilityType.PROXY, proxy);
		FirefoxProfile cap = new FirefoxProfile();

		cap.setPreference("network.proxy.http", "localhost");
		cap.setPreference("network.proxy.http_port", 8000);
		cap.setPreference("network.proxy.type", 1);
		/* use proxy for everything, including localhost */
		cap.setPreference("network.proxy.no_proxies_on", "");

		driver = new FirefoxDriver(cap);
		// driver.get("http://salt.ece.ubc.ca");

	}

	@After
	public void closeBrowser() {
		JavascriptExecutor js = null;
		if (driver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) driver;
		}
		js.executeScript("$$_l.submit();");
		driver.close();
	}

	@Test
	public void test1() throws Exception {
		driver.get("http://localhost/phormer-photoGallery331/?feat=slideshow");
		assertTrue(driver.getTitle().matches("^SlideShow[\\s\\S]*$"));
		driver.findElement(By.id("ss_playpause_link")).click();
		assertTrue(isElementPresent(By.cssSelector("img#ss_photo")));
		assertEquals("1", driver.findElement(By.cssSelector("span#ss_n")).getText());
		driver.findElement(By.linkText("Next")).click();
		assertEquals("2", driver.findElement(By.cssSelector("span#ss_n")).getText());
		driver.findElement(By.linkText("Previous")).click();
		assertEquals("1", driver.findElement(By.cssSelector("span#ss_n")).getText());
		driver.findElement(By.linkText("Smaller Size")).click();
		assertEquals("SlideShow :: My PhotoGallery", driver.getTitle());
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
