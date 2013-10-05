package salt.domcoverage.casestudies.theorganizer;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Note {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver(salt.domcoverage.core.dom.proxy.BrowserProfile.getProfile());
		// driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testNote() throws Exception {
		driver.get(baseUrl + "theorganizer/");
		driver.findElement(By.id("logon_username")).clear();
		driver.findElement(By.id("logon_username")).sendKeys("mehdi");
		driver.findElement(By.id("logon_password")).clear();
		driver.findElement(By.id("logon_password")).sendKeys("m");
		driver.findElement(By.cssSelector("input[type=\"image\"]")).click();
		assertEquals("Welcome to The Organizer!", closeAlertAndGetItsText());
		driver.findElement(By.id("newNote")).click();
		driver.findElement(By.id("noteCreateShow_subject")).clear();
		driver.findElement(By.id("noteCreateShow_subject")).sendKeys("mynote");
		driver.findElement(By.id("noteCreateShow_text")).clear();
		driver.findElement(By.id("noteCreateShow_text")).sendKeys("note text");
		driver.findElement(By.cssSelector("input[type=\"image\"]")).click();
		// assertEquals("Note has been created.", driver.findElement(By.id("mainContent")).getText());
	}

	@After
	public void tearDown() throws Exception {
		((JavascriptExecutor) driver).executeScript("		if (window.jscoverage_report) {return jscoverage_report('report');}");
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
