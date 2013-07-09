package ca.ubc.ece.salt.gallery;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Admin_login_and_logout {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js ;
  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
    driver = new ChromeDriver();
	  js = (JavascriptExecutor) driver;
	  js.executeScript("__funcEnter(\"1\", \"URL\", \"adminloginlogout\");");
    baseUrl = "http://localhost:8888/phormer-photoGallery331/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAdmin_login_and_logout() throws Exception {
    //String String = driver.findElement(By.cssSelector("div.note_valid")).getText();
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Admin Page")).click();
    driver.findElement(By.id("loginAdminPass")).clear();
    driver.findElement(By.id("loginAdminPass")).sendKeys("editor");
    driver.findElement(By.cssSelector("input.submit")).click();
    driver.findElement(By.linkText("Log Out")).click();

  }

  @After
  public void tearDown() throws Exception {
	    js.executeScript("__funcExit(\"1\", \"URL\");");
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
