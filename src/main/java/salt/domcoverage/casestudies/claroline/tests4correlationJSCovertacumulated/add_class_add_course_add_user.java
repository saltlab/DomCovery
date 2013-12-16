package salt.domcoverage.casestudies.claroline.tests4correlationJSCovertacumulated;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class add_class_add_course_add_user {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver(salt.domcoverage.core.dom.proxy.BrowserProfile.getProfile());
		baseUrl = "http://localhost:8888";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAddClass() throws Exception {
		driver.get(baseUrl + "/claroline-1.11.7/index.php");
		WebElement findElement = driver.findElement(By.id("login"));
		findElement.clear();
		findElement.sendKeys("nainy");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("nainy");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		driver.findElement(By.linkText("Platform administration")).click();
		driver.findElement(By.linkText("Manage classes")).click();
		driver.findElement(By.linkText("Create a new class")).click();
		driver.findElement(By.name("class_name")).clear();
		driver.findElement(By.name("class_name")).sendKeys("EG");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		// Warning: verifyTextPresent may require manual changes
		try {
			assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*The new class has been created[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Logout")).click();
		driver.get(baseUrl + "/claroline-1.11.7/index.php");
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys("nainy");
		driver.findElement(By.id("password")).clear();
		((JavascriptExecutor) driver).executeScript("console.log('hello world');");
		driver.findElement(By.id("password")).sendKeys("nainy");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		driver.findElement(By.linkText("Create a course site")).click();
		driver.findElement(By.id("course_title")).clear();
		driver.findElement(By.id("course_title")).sendKeys("Computer Science");
		driver.findElement(By.id("course_officialCode")).clear();
		driver.findElement(By.id("course_officialCode")).sendKeys("A7");
		// ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=mslist2 | label=Sciences]]
		driver.findElement(By.linkText("Advanced options")).click();
		driver.findElement(By.id("course_status_date")).click();
		driver.findElement(By.name("changeProperties")).click();
		driver.findElement(By.linkText("Logout")).click();

		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		driver.get(baseUrl + "/claroline-1.11.7/index.php");
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys("nainy");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("nainy");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		driver.findElement(By.linkText("Platform administration")).click();
		driver.findElement(By.linkText("Create user")).click();
		driver.findElement(By.id("lastname")).clear();
		driver.findElement(By.id("lastname")).sendKeys("Naman");
		driver.findElement(By.id("firstname")).clear();
		driver.findElement(By.id("firstname")).sendKeys("Agr");
		driver.findElement(By.id("officialCode")).clear();
		driver.findElement(By.id("officialCode")).sendKeys("a7");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("nam");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("naman");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("password_conf")).clear();
		driver.findElement(By.id("password_conf")).sendKeys("123456");
		driver.findElement(By.id("student")).click();
		driver.findElement(By.id("student")).click();
		driver.findElement(By.id("student")).click();

	}

	@After
	public void tearDown() throws Exception {

		((JavascriptExecutor) driver).executeScript("		if (window.jscoverage_report) {return jscoverage_report('report');}");
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
