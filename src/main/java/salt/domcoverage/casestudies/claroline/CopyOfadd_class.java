package salt.domcoverage.casestudies.claroline;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import salt.domcoverage.core.dom.proxy.BrowserProfile;
import salt.domcoverage.core.dom.proxy.WebScarabProxy;

public class CopyOfadd_class {

	private WebDriver driver;

	private String baseUrl;

	private boolean acceptNextAlert = true;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		new WebScarabProxy().configureRunProxy();
		baseUrl = "http://localhost:8888/test2.html";

		// System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
		// ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File("lib/chromedriver")).usingPort(8884).build();

		// service.start();
		// CommandExecutor URL = new HttpCommandExecutor(new URL(System.getProperty("webdriver.remote.server", "http://localhost:8884/")));
		// WebDriver driver = new ChromeDriver(service);
		// WebDriver driver = new RemoteWebDriver(URL, DesiredCapabilities.chrome());
		// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// capabilities.setCapability("chrome.switches", Arrays.asList("--proxy-server=http://127.0.0.1:8884"));
		// WebDriver driver = new ChromeDriver(capabilities);
		driver = new FirefoxDriver(BrowserProfile.getProfile());

		// driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAddClass() {
		driver.get(baseUrl);
		// WebElement findElement = driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.xpath("//*[@id=\"bu\"]"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		// }.getClass().getEnclosingMethod().getName()));
		WebElement findElement = driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.xpath("/html/body/button"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName()));
		findElement.click();

		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// // e.pr
		// e.printStackTrace();
		// }

		assertFalse(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).getText().contains("W3Schools"));
		// Warning: verifyTextPresent may require manual changes

		// driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Logout"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		// }.getClass().getEnclosingMethod().getName())).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
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
