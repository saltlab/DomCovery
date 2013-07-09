package ca.ubc.ece.salt.gallery;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Gallery_1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8888/phormer-photoGallery331/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testHidshow() throws Exception {
    driver.get(baseUrl + "/");
    WebElement element = driver.findElement(By.cssSelector("img"));
	element.click();
    driver.switchTo().window(handleMultipleWindows("\"100_0794\" of My PhotoGallery"));
    
    driver.findElement(By.id("hin")).click();
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().contains("Show info"));
  }

  private String handleMultipleWindows(String windowTitle) {
      Set<String> windows = driver.getWindowHandles();

      for (String window : windows) {
          driver.switchTo().window(window);
          if (driver.getTitle().contains(windowTitle)) {
              return window;
          }
      }
	return null;
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
