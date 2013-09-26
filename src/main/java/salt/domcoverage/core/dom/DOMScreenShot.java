package salt.domcoverage.core.dom;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;

import salt.domcoverage.core.utils.ConstantVars;

public class DOMScreenShot {
	FirefoxDriver driver;

	public DOMScreenShot() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void takeScreenshot(String mergeddomfilename, String crawljaxfilename) {

		String url = "file://localhost" + System.getProperty("user.dir") + "/" + mergeddomfilename;
		System.out.println("url: " + url);
		driver.get(url);

		File scrFile = driver.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(scrFile, new File(crawljaxfilename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// driver.getScreenshotAs(target) url.replace(".html", ".jpg"));
	}

	public void finalize() {
		driver.quit();

	}

}
