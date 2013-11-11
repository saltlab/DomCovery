package salt.domcoverage.casestudies.theorganizerdomcovery;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import salt.domcoverage.core.utils.Utils;

public class Note {

	private WebDriver driver;

	private String baseUrl;

	private boolean acceptNextAlert = true;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver(salt.domcoverage.core.dom.proxy.BrowserProfile.getConfigureProxyandgetProfile());
		// driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testNote() throws Exception {
		driver.get(baseUrl + "theorganizer/");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("logon_username"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("logon_username"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("mehdi");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("logon_password"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("logon_password"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("m");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input[type=\"image\"]"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		assertEquals("Welcome to The Organizer!", closeAlertAndGetItsText());
		Utils.sleep(1000);
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("newNote"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("noteCreateShow_subject"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("noteCreateShow_subject"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("mynote");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("noteCreateShow_text"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).clear();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("noteCreateShow_text"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).sendKeys("note text");
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input[type=\"image\"]"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
		assertEquals("Note has been created.", driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("mainContent"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).getText());
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff();

	}

	@After
	public void tearDown() throws Exception {
		// ((JavascriptExecutor) driver).executeScript("		if (window.jscoverage_report) {return jscoverage_report('report');}");
		driver.quit();
		// driver.quit();
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
