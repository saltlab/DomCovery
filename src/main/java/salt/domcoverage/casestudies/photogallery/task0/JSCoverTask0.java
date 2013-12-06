package salt.domcoverage.casestudies.photogallery.task0;

//import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import salt.domcoverage.core.utils.Utils;

//import org.openqa.selenium.support.ui.Select;

public class JSCoverTask0 {
	private WebDriver driver;
	// private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver(salt.domcoverage.core.dom.proxy.BrowserProfile.getProfile());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test1() throws Exception {
		driver.get("http://localhost:8888/phormer-photoGallery331/?feat=slideshow");
		assertTrue(driver.getTitle().matches("^SlideShow[\\s\\S]*$"));
		driver.findElement(By.id("ss_playpause_link")).click();
		assertTrue(isElementPresent(By.cssSelector("img#ss_photo")));
		assertEquals("1", driver.findElement(By.cssSelector("span#ss_n")).getText());
		driver.findElement(By.linkText("Next")).click();
		assertEquals("2", driver.findElement(By.cssSelector("span#ss_n")).getText());
		driver.findElement(By.linkText("Previous")).click();
		assertEquals("1", driver.findElement(By.cssSelector("span#ss_n")).getText());
		driver.findElement(By.linkText("Smaller Size")).click();
		assertEquals("SlideShow :: My PhotoGallery", driver.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		Utils.collectJSCoverageResults(driver, "0");
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
}
