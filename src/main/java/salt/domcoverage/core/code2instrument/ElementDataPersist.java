package salt.domcoverage.core.code2instrument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import salt.domcoverage.core.utils.ConstantVars;

import com.crawljax.util.DomUtils;

public class ElementDataPersist {


	public ElementDataPersist(String time, String testName, String by, String domData, ArrayList<String> elements) {

		try {
			// FileUtils.cleanDirectory(new File("Coverage"));
			String buffer = "";
			String domfilename = testName + "_DOM_" + time;
			String elementFile = testName + "_ELEMENT_" + time;
			String allElements="";
			for (String element : elements) {
				buffer += testName + ConstantVars.SEPARATOR + time + ConstantVars.SEPARATOR + by + ConstantVars.SEPARATOR + elementFile + ConstantVars.SEPARATOR + domfilename + "\r";
				 allElements= element+"\n";
			}
			FileUtils.write(new File(ConstantVars.COVERAGE_LOCATION + elementFile+".txt"), allElements);

			FileUtils.write(new File(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV), buffer, null, true);
			writeDOMtoFile(domData, domfilename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ElementDataPersist() {
		// TODO Auto-generated constructor stub
	}

	private void writeDOMtoFile(String domData, String domfilename) throws IOException {
		// add style to domData after TITLE
		String style = "<style> [coverage=\"true\"]{border:10px solid green;}</style>";
		String[] split = domData.split("<title>");
		String modifieddomData = domData;
		if (split.length == 2)
			modifieddomData = split[0] + style + split[1];
		
		FileUtils.write(new File(ConstantVars.COVERAGE_LOCATION + domfilename + ".html"), modifieddomData);
	}
	
	public List<ElementData> getElementsFromFile(String file){
		List<ElementData> elementsData = new ArrayList<ElementData>();
		try {
			List<String> fileContents = FileUtils.readLines(new File(file));
			for (String line : fileContents) {
				String[] split = line.split(ConstantVars.SEPARATOR);
				String testName = split[0];
				String time=split[1];
				String by=split[2];
				String elementFile=split[3];
				String domfilename=split[4];	
//				public ElementData(String time, String testName, String by, String domData, ArrayList<String> elements) {
				ElementData elem = new ElementData(testName,time,by,domfilename,elementFile);
				elementsData.add(elem);
					
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return elementsData;
		
	}

	public ElementData getElementData(String name, List<ElementData> elementsData) {
		if (name.contains(".html")) 
			name= name.substring(0,name.length()-5);
		for (ElementData elementData : elementsData) {
			if (elementData.getdomfilename().equals( name) )
				return elementData;
		}
		return null;
		// TODO Auto-generated method stub
		
	}

}
