package salt.domcoverage.core.utils;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class DOMUtility {

	public static int getNumberoofAllClickables(Document document) {
		
		NodeList elemA = document.getElementsByTagName("a");
		NodeList elemI = document.getElementsByTagName("input");
		NodeList elemB = document.getElementsByTagName("button");
		
		return elemA.getLength()+elemB.getLength()+elemI.getLength();
	}
	
	

}
