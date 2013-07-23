package salt.domcoverage.casestudies.photogallery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import salt.domcoverage.core.code2instrument.DomCoverageClass;


public class TestEnteringName {

    private WebDriver driver;

    private String baseUrl;

    private boolean acceptNextAlert = true;

    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testEnteringName() throws Exception {
        driver.get(baseUrl + "file://localhost/Users/mehdimir/Desktop/Dropbox/UBC-research/development/survey_application/index.html");
        driver.findElement(DomCoverageClass.collectData(By.id("agreementCheckbox"), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(DomCoverageClass.collectData(By.id("contestName"), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).clear();
        driver.findElement(DomCoverageClass.collectData(By.id("contestName"), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).sendKeys("Mehdi");
        driver.findElement(DomCoverageClass.collectData(By.id("contestEmail"), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).clear();
        driver.findElement(DomCoverageClass.collectData(By.id("contestEmail"), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).sendKeys("Mir");
        driver.findElement(DomCoverageClass.collectData(By.id("enterContestButton"), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        assertEquals("You have entered in the contest!", driver.findElement(DomCoverageClass.collectData(By.id("enteredInContestDiv"), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).getText());
    }

    //  @Test 
    //  public void testEnteringName() throws Exception { 
    //    driver.get(baseUrl + "file://localhost/Users/mehdimir/Desktop/Dropbox/UBC-research/development/survey_application/index.html"); 
    //    driver.findElement(By.id("agreementCheckbox")).click(); 
    //    driver.findElement(By.id("contestName")).clear(); 
    //    driver.findElement(By.id("contestName")).sendKeys("Mehdi"); 
    //    driver.findElement(By.id("contestEmail")).clear(); 
    //    driver.findElement(By.id("contestEmail")).sendKeys("Mir"); 
    //    driver.findElement(By.id("enterContestButton")).click(); 
    //    assertEquals("You have entered in the contest!", driver.findElement(By.id("enteredInContestDiv")).getText()); 
    //  } 
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
