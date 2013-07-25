package salt.domcoverage.casestudies.photogallery.originaltests;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class add_story_assert {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl ="http://localhost:8888";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAddStoryAssert() throws Exception {
    driver.get(baseUrl + "/phormer-photoGallery331/");
    driver.findElement(By.linkText("Admin Page")).click();
    driver.findElement(By.id("loginAdminPass")).clear();
    driver.findElement(By.id("loginAdminPass")).sendKeys("editor");
    driver.findElement(By.cssSelector("input.submit")).click();
    driver.findElement(By.linkText("Manage Stories")).click();
    //driver.findElement(By.id("loginAdminPass")).clear();
    //driver.findElement(By.id("loginAdminPass")).sendKeys("editor");
    //driver.findElement(By.cssSelector("input.submit")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("Photos");
    driver.findElement(By.name("desc")).clear();
    driver.findElement(By.name("desc")).sendKeys("Greenery !!");
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Get Comments[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("input.submit")).click();
    //driver.findElement(By.id("loginAdminPass")).clear();
    //driver.findElement(By.id("loginAdminPass")).sendKeys("editor");
    //driver.findElement(By.cssSelector("input.submit")).click();
    driver.findElement(By.cssSelector("a[title=\"Log Out\"] > img.logo")).click();
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
    } 
    finally {
      acceptNextAlert = true;
    }
  }
}
