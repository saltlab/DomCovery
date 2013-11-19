package salt.domcoverage.casestudies.photogallery;

import static org.junit.Assert.*;

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

import salt.domcoverage.core.metrics.ClickableElements;
import salt.domcoverage.core.utils.ConstantVars;

public class add_story_assert {

	private WebDriver driver;

	private String baseUrl;

	private boolean acceptNextAlert = true;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		ConstantVars.Clickable_mode = true;
		driver = new FirefoxDriver(salt.domcoverage.core.dom.proxy.BrowserProfile.getConfigureProxyandgetProfile());
		baseUrl = "http://localhost:8888";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAddStoryAssert() throws Exception {
		driver.get(baseUrl + "/phormer-photoGallery331/");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Admin Page"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("loginAdminPass"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("loginAdminPass"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("editor");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input.submit"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Manage Stories"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		// driver.findElement(By.id("loginAdminPass")).clear();
		// driver.findElement(By.id("loginAdminPass")).sendKeys("editor");
		// driver.findElement(By.cssSelector("input.submit")).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("name"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("name"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("Photos");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.name("desc"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.name("desc"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("Greenery !!");
		// Warning: verifyTextPresent may require manual changes
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
		assertNotNull(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("name"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName()));
		// assertFalse(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.xpath("//div[3]/div[2]/div/div"), driver, this.getClass().getName() + "." + new Object() {
		// }.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*Get Comments[\\s\\S]*$"));
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff();

		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input.submit"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		// driver.findElement(By.id("loginAdminPass")).clear();
		// driver.findElement(By.id("loginAdminPass")).sendKeys("editor");
		// driver.findElement(By.cssSelector("input.submit")).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("a[title=\"Log Out\"]"), driver, this.getClass().getName() + "." + new Object() {
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
			driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(by, driver, this.getClass().getName() + "." + new Object() {
			}.getClass().getEnclosingMethod().getName()));
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
