package salt.domcoverage.casestudies.claroline;

import static org.junit.Assert.assertTrue;
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
import org.openqa.selenium.firefox.FirefoxDriver;

import salt.domcoverage.core.dom.proxy.BrowserProfile;
import salt.domcoverage.core.dom.proxy.WebScarabProxy;

public class announcement {

	private WebDriver driver;

	private String baseUrl;

	private boolean acceptNextAlert = true;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		new WebScarabProxy().configureRunProxy();
		driver = new FirefoxDriver(BrowserProfile.getProfile());
		baseUrl = "http://watersmc.ece.ubc.ca:8888/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAnnouncement() throws Exception {
		driver.get(baseUrl + "/claroline-1.11.7/index.php");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("login"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("login"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("nainy");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("password"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("password"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("nainy");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("button[type=\"submit\"]"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Platform administration"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Configuration"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Announcement"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		// Warning: verifyTextPresent may require manual changes
		try {
			assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
			}.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*Use 0 to display all[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("label_announcementPortletMaxItems"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("label_announcementPortletMaxItems"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("0");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input[type=\"submit\"]"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		// Warning: verifyTextPresent may require manual changes
		try {
			assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
			}.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*Properties for Announcement, \\(CLANN\\) are now effective on server[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input[type=\"submit\"]"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
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
