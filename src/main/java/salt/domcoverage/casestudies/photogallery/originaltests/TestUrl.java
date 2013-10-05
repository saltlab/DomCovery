package salt.domcoverage.casestudies.photogallery.originaltests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.xml.xpath.XPathExpressionException;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.NodeList;

import salt.domcoverage.core.code2instrument.DomCoverageClass;

import com.crawljax.util.XPathHelper;

public class TestUrl {

	private WebDriver mydriver;

	private String baseUrl;

	private boolean acceptNextAlert = true;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		mydriver = new FirefoxDriver();
		baseUrl = "http://watersmc.ece.ubc.ca:8888/phormer-photoGallery331/";
	}

	@Test
	public void testStoringHitsValue() throws Exception {
		mydriver.get(baseUrl + "/");
		String xpathExpression = "//DIV[@id='sidecolinner']/DIV[3]/DIV[2]/DIV[5]";
		// System.out.println(this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName());
		WebElement findElement = mydriver.findElement(By.xpath(xpathExpression));
	}

	private By f(By xpath) {
		// TODO Auto-generated method stub
		return null;
	}

	private String modifyElement(String location) {
		try {
			// get the test case name: TestUrl.testStoringHitsValue
			// store the elements got accessed in this call
			// store DOM
			// modify the DOM to show elements that got acccessed
			String pageSource = mydriver.getPageSource();
			// System.out.println(pageSource);
			NodeList evaluateXpathExpression = XPathHelper.evaluateXpathExpression(pageSource, location);
			System.out.println("lentgh: " + evaluateXpathExpression.getLength());
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
	}

	@After
	public void tearDown() throws Exception {
		mydriver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
		}
	}

	private boolean isElementPresent(By by) {
		try {
			mydriver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			mydriver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = mydriver.switchTo().alert();
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
