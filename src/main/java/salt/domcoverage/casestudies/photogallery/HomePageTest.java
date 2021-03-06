package salt.domcoverage.casestudies.photogallery;

//import java.util.regex.Pattern;  
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;  
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

//import org.openqa.selenium.support.ui.Select;  
public class HomePageTest {

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
    public void testHomePage() throws Exception {
        driver.get("http://localhost:8888/phormer-photoGallery331/");
        assertTrue(isElementPresent(By.cssSelector("div#Granny")));
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Stories"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        try {
            assertTrue(driver.getCurrentUrl().matches("^http://localhost:8888/[\\s\\S]*mode=stories$"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.partialLinkText("Default Category"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        try {
            assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("div.midInfo"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*category[\\s\\S]*$"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.partialLinkText("Default Story"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        try {
            assertTrue(driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("div.midInfo"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).getText().matches("^[\\s\\S]*story[\\s\\S]*$"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("SlideShow"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        try {
            assertTrue(driver.getTitle().matches("^SlideShow[\\s\\S]*$"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
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
}
