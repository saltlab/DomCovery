package salt.domcoverage.casestudies.photogallery.task2;

import static org.junit.Assert.*;

import java.util.List;
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

import salt.domcoverage.core.utils.Utils;

public class JSCoverTask2 {
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
	public void testA() throws Exception {
		driver.get("http://localhost:8888/phormer-photoGallery331/?p=1");

		assertTrue(isElementPresent(By.cssSelector("div#theImage")));
		driver.findElement(By.linkText("Hide  info")).click();
		assertTrue(driver.findElement(By.id("photoBoxes")).isDisplayed() == false);
		driver.findElement(By.linkText("Show info")).click();
		assertTrue(driver.findElement(By.id("photoBoxes")).isDisplayed());
		WebElement select = driver.findElement(By.id("rateSelect"));
		int rating = Integer.parseInt(select.getAttribute("value"));
		int nextRating = rating % 5 + 1;
		System.out.println(nextRating);
		List<WebElement> allOptions = select.findElements(By.tagName("option"));
		for (WebElement option : allOptions) {
			if (Integer.parseInt(option.getAttribute("value")) == nextRating) {
				option.click();
				break;
			}
		}
		// Thread.sleep(10000);
		// assertEquals("Your rating saved!", driver.findElement(By.cssSelector("span#rateStatus")).getText().trim());
		driver.findElement(By.xpath("//div[@id='Granny']/div[5]/div[2]/center/a/img")).click();
		assertTrue(driver.getCurrentUrl().matches("^http://localhost:8888/[\\s\\S]*p=2$"));

		driver.get("http://localhost:8888/phormer-photoGallery331/?feat=slideshow");
		assertTrue(driver.getTitle().matches("^SlideShow[\\s\\S]*$"));
		driver.findElement(By.id("ss_playpause_link")).click();
		assertTrue(isElementPresent(By.cssSelector("img#ss_photo")));
		assertEquals("1", driver.findElement(By.cssSelector("span#ss_n")).getText());
		driver.findElement(By.linkText("Next")).click();
		assertEquals("2", driver.findElement(By.cssSelector("span#ss_n")).getText());
		driver.findElement(By.linkText("Previous")).click();
		assertEquals("1", driver.findElement(By.cssSelector("span#ss_n")).getText());
		// driver.findElement(By.linkText("Smaller Size")).click();
		// assertEquals("SlideShow :: My PhotoGallery", driver.getTitle());
		//
		driver.get(baseUrl + "/phormer-photoGallery331/");
		driver.findElement(By.linkText("Admin Page")).click();
		driver.findElement(By.id("loginAdminPass")).clear();
		driver.findElement(By.id("loginAdminPass")).sendKeys("editor");
		driver.findElement(By.cssSelector("input.submit")).click();
		driver.findElement(By.linkText("Manage Drafts")).click();
		driver.findElement(By.cssSelector("input.submit")).click();
		assertEquals("At least one file should be selected!", closeAlertAndGetItsText());

	}

	@After
	public void tearDown() throws Exception {
		Utils.collectJSCoverageResults(driver, "2");
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
