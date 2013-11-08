package salt.domcoverage.core.code2instrument;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;

import salt.domcoverage.core.utils.ConstantVars;

public class ElementData {

	private String observerElement;

	public String getElementFile() {
		return elementFile;
	}

	public int getElementSize() {
		List<String> elems = this.getElements();
		int size = 0;
		for (String string : elems) {
			if (string.toLowerCase().contains("body"))
				size++;
		}
		return size;
	}

	public void setElementFile(String elementFile) {
		this.elementFile = elementFile;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getDomData() {
		return domData;
	}

	public void setDomData(String domData) {
		this.domData = domData;
	}

	public String getDomFileName() {
		return domFileName;
	}

	public String getDomFileNamewithoutext() {
		return domFileName.replace(".html", "");
	}

	public void setDomFileName(String domFileName) {
		this.domFileName = domFileName;
	}

	public List<String> getElements() {
		return elements;
	}

	public void setElements(List<String> elements) {
		this.elements = elements;
	}

	private String elementFile;
	String time;
	String testName;
	String by;
	String domData;
	String domFileName;
	List<String> elements;

	/*
	 * fills out the fileds, gets dom data of domfile, and elements if @elementF
	 */
	public ElementData(String testName, String time, String by, String domfilename, String elementF, String observedElement) {
		super();
		this.time = time;
		this.testName = testName;
		this.by = by;
		this.elementFile = elementF;
		this.domFileName = domfilename;
		this.observerElement = observedElement;
		try {
			domData = FileUtils.readFileToString(new File(ConstantVars.COVERAGE_LOCATION + domfilename));
			elements = Arrays.asList((FileUtils.readFileToString(new File(ConstantVars.COVERAGE_LOCATION + elementF + ".txt"))).split(ConstantVars.ELEMENTS_SEPARATOR));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ElementData(String testName, String time, String by, String domdata, List<String> elems) {
		super();
		this.time = time;
		this.testName = testName;
		this.by = by;
		this.elementFile = "";
		this.domFileName = "";
		domData = domdata;
		elements = elems;
	}

	public File getDomFile() {
		return new File(ConstantVars.COVERAGE_LOCATION + domFileName);
	}

	public String retrieveDomString() {
		File f = new File(ConstantVars.COVERAGE_LOCATION + domFileName);
		try {
			return FileUtils.readFileToString(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public boolean contains(String element) {
		for (String elem : this.elements) {
			if (elem.equals(element))
				return true;
		}
		return false;
	}

	public String getObserverElement() {
		return observerElement;
	}

	public void setObserverElement(String observerElement) {
		this.observerElement = observerElement;
	}

}
