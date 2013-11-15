package salt.domcoverage.casestudies.photogallery.instrumentedtask1;

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
public class NINE {

	private WebDriver driver;

	// private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver(salt.domcoverage.core.dom.proxy.BrowserProfile.getConfigureProxyandgetProfile());
		// baseUrl = "http://localhost/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testMainView() throws Exception {
		driver.get("http://localhost:8888/phormer-photoGallery331/?p=1");
		try {
			salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
			assertTrue(isElementPresent(By.cssSelector("div#theImage")));
			salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff();
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Hide  info"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
		assertTrue(driver.findElement(By.id("photoBoxes")).isDisplayed() == false);
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Show info"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
		assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("photoBoxes"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).isDisplayed());
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff();
		WebElement select = driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("rateSelect"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName()));
		int rating = Integer.parseInt(select.getAttribute("value"));
		int nextRating = rating % 5 + 1;
		System.out.println(nextRating);
		List<WebElement> allOptions = select.findElements(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.tagName("option"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName()));
		for (WebElement option : allOptions) {
			if (Integer.parseInt(option.getAttribute("value")) == nextRating) {
				option.click();
				break;
			}
		}
		Utils.sleep(3000);
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
		assertEquals("Your rating saved!", driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("span#rateStatus"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).getText().trim());
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff();
		driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.xpath("//div[@id='Granny']/div[5]/div[2]/center/a/img"), driver, this.getClass().getName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName())).click();
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
		assertTrue(driver.getCurrentUrl().matches("^http://localhost:8888/[\\s\\S]*p=2$"));
		salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff();
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
}
