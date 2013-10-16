package salt.domcoverage.core.code2instrument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import salt.domcoverage.core.dom.DomComparator;
import salt.domcoverage.core.dom.DomComparatorUsingSchema;
import salt.domcoverage.core.dom.clustering.DataClustererWithRelativeSimilarity;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.DOMUtility;

public class ElementDataPersist {

	public ElementData createElementData(String time, String testName, String by, String domData, ArrayList<String> elements) {

		String buffer = "";
		String domfilename = testName + "_DOM_" + time;
		String elementFile = testName + "_ELEMENT_" + time;

		return new ElementData(testName, time, by, domfilename, elementFile, "false");
	}

	@SuppressWarnings("unused")
	public ElementDataPersist(String time, String testName, String by, String domData, String domfilename, List<String> elements) {

		try {
			// FileUtils.cleanDirectory(new File("Coverage"));
			String buffer = "";
			String elementFile = testName + "_ELEMENT_" + time;
			if (domfilename == "") {
				domfilename = testName + "_DOM_" + time + ".html";
				if (ConstantVars.ENFORCE_SIMILARITY_FROM_BEGINING == true) {
					ElementData similarElement = similarDOM(domData, elements);
					if (similarElement != null) {
						String domDataofSimilarDom = similarElement.getDomData();
						domData = DOMUtility.replaceIndirectCoverageElements(domData, domDataofSimilarDom);
						domfilename = similarElement.getDomFileName();
						if (elementsAreSimilar(elements, similarElement.getElements()))
							return;
						// elements = addUniqueElements(elements, similarElement.getElements());
						// TODO: "by" and "testname" and "time" should be changed to list
					}
				}
				writeDOMtoFile(domData, domfilename);
			}
			String allElements = "";
			for (String element : elements) {
				buffer += testName + ConstantVars.SEPARATOR + time + ConstantVars.SEPARATOR + by + ConstantVars.SEPARATOR + elementFile + ConstantVars.SEPARATOR + domfilename + ConstantVars.SEPARATOR + ConstantVars.oracleAssertion + "\r";
				allElements += element + ConstantVars.ELEMENTS_SEPARATOR;
			}
			FileUtils.write(new File(ConstantVars.COVERAGE_LOCATION + elementFile + ".txt"), allElements);

			FileUtils.write(new File(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV), buffer, true);

			// return new ElementData(testName, time, by, domfilename,
			// elementFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return null;
	}

	private List<String> addUniqueElements(List<String> elementsSource, List<String> elementsToEvaluate) {
		List<String> out = new ArrayList<String>();
		out.addAll(elementsSource);
		for (String eval : elementsToEvaluate) {
			if (out.contains(eval))
				continue;
			else
				out.add(eval);
		}
		return out;
	}

	private List<ElementData> getUniqueElements(List<ElementData> elements) {
		List<ElementData> out = new ArrayList<ElementData>();
		for (ElementData elem : elements) {
			// if (elem.getDomFileName().equals()
			// continue;
			// else
			// out.add(eval);
		}
		return out;
	}

	private boolean elementsAreSimilar(List<String> elementsSource, List<String> elementsToEvaluate) {
		for (String eval : elementsToEvaluate) {
			if (!elementsSource.contains(eval))
				return false;
		}
		return true;
	}

	private ElementData similarDOM(String domData, List<String> elements) {
		ElementData ed = new ElementData("", "", null, domData, elements);
		List<ElementData> elementdatas = this.getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);
		// List<ElementData> elementdatas = Utils.keepUniqueElements(elementds);
		List<String> domFileNames = new ArrayList<String>();
		for (ElementData elementData : elementdatas) {
			DomComparator dc = new DomComparatorUsingSchema();
			if (domFileNames.contains(elementData.getDomFileName()))
				continue;
			domFileNames.add(elementData.getDomFileName());
			double differences = dc.differences(elementData, ed);
			boolean similar = new DataClustererWithRelativeSimilarity().similarDomBasedonDiff(differences);
			if (similar)
				return elementData;
		}
		return null;
	}

	public ElementDataPersist() {
		// TODO Auto-generated constructor stub
	}

	private void writeDOMtoFile(String domData, String domfilename) throws IOException {
		// add style to domData after TITLE
		String title = "<title>";
		String[] split = domData.split(title);
		if (split == null || split.length == 0)
			split = domData.split(title.toUpperCase());
		String modifieddomData = domData;
		if (split.length == 2)
			modifieddomData = split[0] + title + split[1];
		// if contains html does not add html extension!!!
		FileUtils.write(new File(ConstantVars.COVERAGE_LOCATION + domfilename), modifieddomData, false);
	}

	public List<ElementData> getUniqueElements(String filename) {
		List<ElementData> doms = getElementsFromFile(filename);
		return doms = getUniqueElements(doms);

	}

	public List<ElementData> getElementsFromFile(String filename) {
		List<ElementData> elementsData = new ArrayList<ElementData>();
		try {
			File file = new File(filename);
			if (!file.exists())
				return elementsData;
			List<String> fileContents = FileUtils.readLines(file);
			for (String line : fileContents) {
				String[] split = line.split(ConstantVars.SEPARATOR);
				String testName = split[0];
				String time = split[1];
				String by = split[2];
				String elementFile = split[3];
				String domfilename = split[4];
				String observedElement = split[5];
				// public ElementData(String time, String testName, String by,
				// String domData, ArrayList<String> elements) {
				ElementData elem = new ElementData(testName, time, by, domfilename, elementFile, observedElement);
				elementsData.add(elem);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return elementsData;

	}

	public ElementData getElementData(String name, List<ElementData> elementsData) {
		// if (name.contains(".html"))
		// name = name.substring(0, name.length() - 5);
		for (ElementData elementData : elementsData) {
			if (elementData.getDomFileName().contains(name))
				return elementData;
		}
		return null;
		// TODO Auto-generated method stub

	}

}
