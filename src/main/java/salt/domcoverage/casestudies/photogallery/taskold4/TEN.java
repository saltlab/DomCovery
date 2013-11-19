package salt.domcoverage.casestudies.photogallery.taskold4;

//import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import salt.domcoverage.core.utils.Utils;

//import org.openqa.selenium.support.ui.Select;

public class TEN {
	private WebDriver driver;
	// private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8888";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
