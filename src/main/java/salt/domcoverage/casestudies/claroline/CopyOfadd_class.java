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
		driver = new FirefoxDriver(BrowserProfile.getProfile());
		baseUrl = "file://localhost/Applications/MAMP/htdocs/test2.html";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAddClass() throws Exception {
		driver.get(baseUrl);
		WebElement findElement = driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.xpath("/html/body/button"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName()));
		findElement.click();
		assertFalse(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).getText().contains("W3Schools"));
		// Warning: verifyTextPresent may require manual changes

		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Logout"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
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