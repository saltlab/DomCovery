package ca.ubc.ece.salt.gallery;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TestWrapper {

//	  JavascriptExecutor js ;

	
	protected void funcEnter(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("__funcEnter(\"1\", \"URL\", \"adminloginlogout\");");

	}

	protected void funcExit(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
	   js.executeScript("__funcExit(\"1\", \"URL\");");
	}
}
