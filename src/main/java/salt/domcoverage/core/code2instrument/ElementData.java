package salt.domcoverage.core.code2instrument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

public class ElementData {

	String DOM;
	String testCase;

	public ElementData(String time, String testName, String by, String domData, ArrayList<String> elements) {

		try {
			String buffer = "";
			String domfilename = testName + "_DOM_" + time;
			String elementFile = testName + "_ELEMENT_" + time;
			for (String element : elements) {
				buffer += testName + "," + time + "," + by + "," + elementFile + "," + domfilename + "\r";
				FileUtils.write(new File("Coverage/" + elementFile), element);
			}

			FileUtils.write(new File("Coverage/CoveredElements.csv"), buffer, null, true);
			FileUtils.write(new File("Coverage/" + domfilename), domData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
