package salt.domcoverage.casestudies.photogallery.instrumentedtask2;

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
public class TEN {

    private WebDriver driver;

    // private String baseUrl;   
    private boolean acceptNextAlert = true;

    private StringBuffer verificationErrors = new StringBuffer();

    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver(salt.domcoverage.core.dom.proxy.BrowserProfile.getConfigureProxyandgetProfile());
        baseUrl = "http://localhost:8888";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testMain() throws Exception {
        driver.get(baseUrl + "/phormer-photoGallery331/");
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Admin Page"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("loginAdminPass"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).clear();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("loginAdminPass"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).sendKeys("editor");
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input.submit"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Manage Drafts"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("input.submit"), driver, this.getClass().getName() + "." + new Object() {
        }.getClass().getEnclosingMethod().getName())).click();
        salt.domcoverage.core.code2instrument.DomCoverageClass.assertionModeOn();
        assertEquals("At least one file should be selected!", closeAlertAndGetItsText());
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
