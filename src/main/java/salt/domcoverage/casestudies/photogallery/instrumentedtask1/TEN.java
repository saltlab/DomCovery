package salt.domcoverage.casestudies.photogallery.instrumentedtask1;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import salt.domcoverage.core.code2instrument.DomCoverageClass;
import com.google.common.collect.Maps;
import com.google.common.collect.Maps.EntryTransformer;

public class TEN {

    private WebDriver driver;

    private String baseUrl;

    private boolean acceptNextAlert = true;

    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver(salt.domcoverage.core.dom.proxy.BrowserProfile.getConfigureProxyandgetProfile());
        baseUrl = "http://watersmc.ece.ubc.ca:8888/phormer-photoGallery331/?feat=slideshow";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSlideShowPlayPause() throws Exception {
        driver.get(baseUrl);
        By by = By.id("ss_playpause_link");
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(by, driver, this.getClass().getName() + "." + new Object() {
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
