package salt.domcoverage.casestudies.claroline;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class EditTextZone {

    private WebDriver driver;

    private String baseUrl;

    private boolean acceptNextAlert = true;

    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://watersmc.ece.ubc.ca:8888/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testEditTextZone() throws Exception {
        driver.get(baseUrl + "/claroline-1.11.7/");
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("login"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).clear();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("login"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).sendKeys("nainy");
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("password"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).clear();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("password"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).sendKeys("nainy");
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("button[type=\"submit\"]"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Platform administration"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Edit text zones"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("img[alt=\"Preview\"]"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        // Warning: verifyTextPresent may require manual changes   
        try {
            assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
            }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
            }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*textzone_top\\.inc\\.html[\\s\\S]*$"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.xpath("(//img[@alt='Preview'])[5]"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("img[alt=\"Edit\"]"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input.claroButton"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        // Warning: verifyTextPresent may require manual changes   
        try {
            assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
            }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
            }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*Hello !! Welcome to Clatoline\\.\\. :\\)[\\s\\S]*$"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Logout"), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName()), driver.getPageSource(), this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
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
