package salt.domcoverage.casestudies.photogallery.task2;

//import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import salt.domcoverage.core.utils.Utils;

//import org.openqa.selenium.support.ui.Select;

public class NodeTEN {
	private WebDriver driver;
	// private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String baseUrl;

	@Before
	public void openBrowser() {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
	public void testMain() throws Exception {
		driver.get(baseUrl + "/phormer-photoGallery331/");
		driver.findElement(By.linkText("Admin Page")).click();
		driver.findElement(By.id("loginAdminPass")).clear();
		driver.findElement(By.id("loginAdminPass")).sendKeys("editor");
		driver.findElement(By.cssSelector("input.submit")).click();
		driver.findElement(By.linkText("Manage Drafts")).click();
		driver.findElement(By.cssSelector("input.submit")).click();
		assertEquals("At least one file should be selected!", closeAlertAndGetItsText());
		// driver.findElement(By.linkText("Upload Mass of Items")).click();
		// Utils.sleep(2000);
		// // driver.findElement(By.id("theAddress")).clear();
		// driver.findElement(By.xpath("//*[@id=\"theAddress\"]")).sendKeys("/Users/mehdimir/Desktop/myphoto.jpg");
		// assertEquals("The photo \"myphoto.jpg\" is uploaded successfully and is saved in Drafts part!", driver.findElement(By.id("upload_note_lmecc")).getText());

	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
