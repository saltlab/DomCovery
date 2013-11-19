package salt.domcoverage.casestudies.photogallery;

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

import salt.domcoverage.core.utils.ConstantVars;

public class Add_category {

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
	public void testAddCategory() throws Exception {
		driver.get(baseUrl + "/phormer-photoGallery331/");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Admin Page"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("loginAdminPass"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("loginAdminPass"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("editor");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input.submit"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Manage Categories"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("name"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("name"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("flowers");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.name("desc"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.name("desc"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("contains photos of all the flowers !");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("listRadioNo"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("listRadioYe"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input.submit"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		// Warning: verifyTextPresent may require manual changes
		try {
			assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver, this.getClass().getName() + "." + new Object() {
			}.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*Category \"flowers\" added succesfully![\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.xpath("(//a[contains(text(),'Delete / Clear')])[2]"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		// Warning: verifyTextPresent may require manual changes
		try {
			assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver, this.getClass().getName() + "." + new Object() {
			}.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*Clears Category flowers of all its own \\(direct\\) photos, nothing will be removed[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// FileUtils.write(new
		// File("salt.domcoverage.casestudies.photogallery.Add_category.testAddCategory.html"),
		// driver.getPageSource());
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.xpath("(//input[@name='howto'])[4]"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input.submit"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete \"flowers\"[\\s\\S]$"));
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
