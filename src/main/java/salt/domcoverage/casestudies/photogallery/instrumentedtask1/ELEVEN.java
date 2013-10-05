package salt.domcoverage.casestudies.photogallery.instrumentedtask1;

//import java.util.regex.Pattern;  
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;  
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

//import org.openqa.selenium.support.ui.Select;  
public class ELEVEN {

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
    public void testSlideShow() throws Exception {
        driver.get("http://localhost:8888/phormer-photoGallery331/?feat=slideshow");
        assertTrue(driver.getTitle().matches("^SlideShow[\\s\\S]*$"));
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.id("ss_playpause_link"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        try {
            assertTrue(isElementPresent(By.cssSelector("img#ss_photo")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("1", driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("span#ss_n"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Next"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        try {
            assertEquals("2", driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("span#ss_n"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Previous"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        try {
            assertEquals("1", driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.cssSelector("span#ss_n"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(salt.domcoverage.core.code2instrument.DomCoverageClass.collectData(By.linkText("Smaller Size"), driver, this.getClass().getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName())).click();
        assertEquals("SlideShow :: My PhotoGallery", driver.getTitle());
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
