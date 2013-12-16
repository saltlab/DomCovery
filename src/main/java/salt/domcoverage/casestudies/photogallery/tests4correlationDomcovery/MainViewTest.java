package salt.domcoverage.casestudies.photogallery.tests4correlationDomcovery;

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

public class MainViewTest {
	private WebDriver driver;
	// private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testMainView() throws Exception {
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
		Utils.sleep(3000);
		assertEquals("Your rating saved!", driver.findElement(By.cssSelector("span#rateStatus")).getText());
		driver.findElement(By.xpath("//div[@id='Granny']/div[5]/div[2]/center/a/img")).click();
		assertTrue(driver.getCurrentUrl().matches("^http://localhost:8888/[\\s\\S]*p=2$"));
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
}
