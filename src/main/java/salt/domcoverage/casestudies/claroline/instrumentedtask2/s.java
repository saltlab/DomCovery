package salt.domcoverage.casestudies.claroline.instrumentedtask2;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class s {

    private WebDriver driver;

    private String baseUrl;

    private boolean acceptNextAlert = true;

    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver(salt.domcoverage.core.dom.proxy.BrowserProfile.getConfigureProxyandgetProfile());
        baseUrl = "http://watersmc.ece.ubc.ca:8888/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testS() throws Exception {
        driver.get(baseUrl + "/claroline-1.11.7/index.php");
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("login"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).clear();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("login"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).sendKeys("nainy");
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("password"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).clear();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("password"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).sendKeys("nainy");
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("button[type=\"submit\"]"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Platform administration"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Manage user desktop"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("img[alt=\"visible\"]"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("img[alt=\"invisible\"]"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
        // Warning: verifyTextPresent may require manual changes   
        try {
            salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
            assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver, this.getClass().getName() + "." + new Object() {
            }.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*My course list[\\s\\S]*$"));
            salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff();
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        // Warning: verifyTextPresent may require manual changes   
        try {
            salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
            assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("BODY"), driver, this.getClass().getName() + "." + new Object() {
            }.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]* Claroline[\\s\\S]*$"));
            salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOff();
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Logout"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
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
