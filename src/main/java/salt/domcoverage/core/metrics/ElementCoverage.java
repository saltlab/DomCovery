package salt.domcoverage.core.metrics;

import java.io.File;
import java.util.ArrayList;

import salt.domcoverage.core.dom.DocumentObjectModel;
import salt.domcoverage.core.utils.DOMUtility;

public class ElementCoverage {

	public void getCoverageOffilesAccordingToCoverageTrue(String coverageFolder) {
		ArrayList<File> domFiles = DOMUtility.getFilesInDirectoryWithExtension(coverageFolder, ".html");
		for (File file : domFiles) {
			DocumentObjectModel DOM = new DocumentObjectModel(file);
			int size = DOM.getElementAccessedInDOMThroughCoverageTrueAttribute();
			int allelementsinDom = DOM.getAllElements();
			int clickableElements = DOM.getNumberofAllClickables();
			printCoverage(allelementsinDom, clickableElements, size, file.getName(), 1);
		}

	}

	// public void getCoverage(String coverageFolder) {
	//
	// // get all doms
	// // merge them
	//
	// DomComparator dc = new DomComparatorUsingSchema();
	// List<ElementData> doms = new
	// ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);
	// double[][] distances = dc.extractDistances(doms);
	//
	// DataClusterer dataClustering = new DataClusterer();
	// List<List<String>> clusterData = dataClustering.clusterData(domFiles,
	// distances);
	//
	// ElementDataPersist edp = new ElementDataPersist();
	// List<ElementData> elementsData =
	// edp.getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);
	//
	// HashMap<Cluster, Double> coverage = new HashMap<Cluster, Double>();
	//
	// for (List<String> clusters : clusterData) {
	// try {
	// if (clusters.size() == 1) {
	// // cluster is a node: get covered elements from
	// // CoveredElements.csv
	// String cluster0 = clusters.get(0);
	// ElementData element = edp.getElementData(cluster0, elementsData);
	// int allelementsinDom;
	//
	// Document asDocument = DomUtils.asDocument(element.getDomData());
	// allelementsinDom = asDocument.getElementsByTagName("*").getLength();
	// int clickableElements = DOMUtility.getNumberoofAllClickables(asDocument);
	// int size = element.getElements().size();
	// String domFileName = element.getDomFileName();
	// printCoverage(allelementsinDom, clickableElements, size, domFileName, 1);
	// } else {
	// int sumofallelementsinDom = 0;
	// int sumofclickableelements = 0;
	// int sumofelementSize = 0;
	// int allelementsinDom = 0;
	// int clickableElements = 0;
	// int elementSize = 0;
	// for (String clust : clusters) {
	// ElementData element = edp.getElementData(clust, elementsData);
	//
	// Document asDocument = DomUtils.asDocument(element.getDomData());
	// allelementsinDom = asDocument.getElementsByTagName("*").getLength();
	// clickableElements = DOMUtility.getNumberoofAllClickables(asDocument);
	// elementSize = 1;// element.getElements().size();
	//
	// sumofallelementsinDom += allelementsinDom;
	// sumofclickableelements += clickableElements;
	// sumofelementSize += elementSize;
	// }
	// String domName = clusters.toString();
	// int clustersize = clusters.size();
	// printCoverage(sumofallelementsinDom, sumofclickableelements,
	// sumofelementSize, domName, clustersize);
	// // System.out.println("clickable element coverage: "
	// // +covclick);
	//
	// }
	//
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// // for each cluster
	// // if cluster is a set of nodes: get
	// }

	private void printCoverage(int sumofallelementsinDom, int sumofclickableelements, int sumofelementSize, String domName, int clustersize) {
		System.out.println("******for DOM: " + domName);
		sumofallelementsinDom = sumofallelementsinDom / clustersize;
		double cov = (double) sumofelementSize / sumofallelementsinDom;
		System.out.format("element coverage: %f (%d / %d) \n", cov, sumofelementSize, sumofallelementsinDom);
		double covclick = (double) sumofelementSize / (sumofclickableelements / clustersize);
		System.out.format("clickable element coverage: %f (%d / %d) \n", covclick, sumofelementSize, sumofclickableelements);
	}

	// private void printCoverage(int allelementsinDom, int clickableElements,
	// int size, String domFileName) {
	// System.out.println("******for DOM: " + domFileName);
	// double cov = (double) size / allelementsinDom;
	// System.out.println("element coverage: " + cov);
	// double covclick = (double) size / clickableElements;
	// System.out.println("clickable element coverage: " + covclick);
	// }
}
