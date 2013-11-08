package salt.domcoverage.core.dom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

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

	// public void takeScreenshots(List<String> keySet) {
	// List<String> list = new ArrayList<String>();
	// for (String crawljaxStateFileName : keySet) {
	// String domStateCoveredFileName = domStateCoverage.get(crawljaxStateFileName);
	// this.takeScreenshot(ConstantVars.MERGEDLOCATION + domStateCoveredFileName, ConstantVars.CRAWLJAX_IMAGES + crawljaxStateFileName.replace(".html", ".jpg"));
	// }
	// this.finalize();
	// }
	public void takeScreenshot(String mergeddomfilename, String crawljaxfilename) {
		takeScreenshot(mergeddomfilename, crawljaxfilename, false);
	}

	public void takeScreenshot(String mergeddomfilename, String crawljaxfilename, boolean withThumbnail) {

		String url = "file://localhost" + System.getProperty("user.dir") + "/" + mergeddomfilename;
		System.out.println("url: " + url);
		driver.get(url);
		if (withThumbnail) {
			byte[] screenshotAs = driver.getScreenshotAs(OutputType.BYTES);
			Image image;
			try {
				image = ImageIO.read(new ByteArrayInputStream(screenshotAs));
				com.crawljax.plugins.crawloverview.ImageWriter.writeThumbNail(new File(crawljaxfilename.replace(".jpg", "_small.jpg")), image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

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
