package salt.domcoverage.core.metrics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;

import salt.domcoverage.core.dom.DocumentObjectModel;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.DOMUtility;

public class ElementCoverage {

	public void addCoverageToStateReport(String folder) {
		ArrayList<File> domFiles = DOMUtility.getFilesInDirectoryWithExtension(folder, ".html");

		for (File file : domFiles) {
			DocumentObjectModel DOM = new DocumentObjectModel(file);
			int coverageTrueSize = DOM.getElementAccessedInDOMThroughAttribute("coverage=true");
			int indirectcoverageTrueSize = DOM.getElementAccessedInDOMThroughAttribute("indirectcoverage=true");
			int assertedCoverageTrueSize = DOM.getElementAccessedInDOMThroughAttribute("assertedcoverage=true");
			int allelementsinDom = DOM.getAllElements();
			int clickableElements = DOM.getNumberofAllClickables();
			double directCov = (double) coverageTrueSize / allelementsinDom;
			double indirectCov = (double) indirectcoverageTrueSize / allelementsinDom;
			double assertedCov = (double) assertedCoverageTrueSize / allelementsinDom;
			Map<String, Double> rep = new HashMap<String, Double>();
			rep.put(ConstantVars.DirectElementCoverage, directCov);
			rep.put(ConstantVars.IndirectElementCoverage, indirectCov);
			rep.put(ConstantVars.AssertedElementCoverage, assertedCov);
			// return rep;// replaceInStateReport(file.getName(), rep);

		}
	}

	private void replaceInStateReport(String filename, Map<String, Double> rep) {
		// statereportfile= new File(ConstantVars.CRAWLJAXSTATES+filename);
		for (String covType : rep.keySet()) {

		}

	}

	public Map<String, Double> getCoverageOffilesAccordingToCoverageTrue(String coverageFolder) {
		ArrayList<File> domFiles = DOMUtility.getFilesInDirectoryWithExtension(coverageFolder, ".html");
		double directCov = 0, indirectCov = 0, assertedCov = 0, clickableCov = 0;
		int allallelements = 0;
		for (File file : domFiles) {
			DocumentObjectModel DOM = new DocumentObjectModel(file);
			int coverageTrueSize = DOM.getElementAccessedInDOMThroughAttribute("coverage=true");
			int assertedCoverageTrueSize = DOM.getElementAccessedInDOMThroughAttribute("assertedcoverage=true");
			int indirectcoverageTrueSize = DOM.getElementAccessedInDOMThroughAttribute("indirectcoverage=true");
			int allelementsinDom = DOM.getAllElements();
			int clickableElements = DOM.getNumberofAllClickables();
			String domName = file.getName();

			String outputToFile = "******for DOM: " + domName + "\n";
			int clustersize = 1;
			allelementsinDom = allelementsinDom / clustersize;
			double cov = (double) coverageTrueSize / allelementsinDom;
			outputToFile += "direct coverage: " + cov + " (" + coverageTrueSize + " / " + allelementsinDom + ") \n";
			double indircov = (double) indirectcoverageTrueSize / allelementsinDom;
			outputToFile += "inidirect coverage: " + indircov + " (" + indirectcoverageTrueSize + " / " + allelementsinDom + ") \n";
			double ac = (double) assertedCoverageTrueSize / allelementsinDom;
			outputToFile += "Asserted Element coverage: " + ac + " (" + assertedCoverageTrueSize + " / " + allelementsinDom + ") \n";
			double covclick = (double) coverageTrueSize / (clickableElements / clustersize);
			outputToFile += "clickable element coverage: " + covclick + " (" + coverageTrueSize + " / " + clickableElements + ") \n";

			directCov += cov;
			indirectCov += indircov;
			assertedCov += ac;
			clickableCov += covclick;
			try {
				System.out.println(outputToFile);
				FileUtils.writeStringToFile(new File(ConstantVars.DomCoverageCriteria), outputToFile, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// printCoverage(allelementsinDom, clickableElements, coverageTrueSize, file.getName(), 1, assertedCoverageTrueSize);
		}

		Map<String, Double> rep = new HashMap<String, Double>();
		int no = domFiles.size();
		rep.put(ConstantVars.DirectElementCoverage, (double) directCov / no);
		rep.put(ConstantVars.IndirectElementCoverage, (double) indirectCov / no);
		rep.put(ConstantVars.AssertedElementCoverage, (double) assertedCov / no);
		rep.put(ConstantVars.ClickableElementCoverage, (double) clickableCov / no);

		return rep;
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

	private void printCoverage(int sumofallelementsinDom, int sumofclickableelements, int sumofelementSize, String domName, int clustersize, double sumofasserted) {

		String outputToFile = "******for DOM: " + domName + "\n";
		sumofallelementsinDom = sumofallelementsinDom / clustersize;
		double cov = (double) sumofelementSize / sumofallelementsinDom;
		outputToFile += "element coverage: " + cov + " (" + sumofelementSize + " / " + sumofallelementsinDom + ") \n";
		double ac = (double) sumofasserted / sumofallelementsinDom;
		outputToFile += "Asserted Element coverage: " + ac + " (" + sumofasserted + " / " + sumofallelementsinDom + ") \n";
		double covclick = (double) sumofelementSize / (sumofclickableelements / clustersize);
		outputToFile += "clickable element coverage: " + covclick + " (" + sumofelementSize + " / " + sumofclickableelements + ") \n";
		try {
			System.out.println(outputToFile);
			FileUtils.writeStringToFile(new File(ConstantVars.DomCoverageCriteria), outputToFile, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public double getDirectElementCoverage(String string) {
		DocumentObjectModel DOM = new DocumentObjectModel(new File(string));
		int coverageTrueSize = DOM.getElementAccessedInDOMThroughAttribute("coverage=true");
		int allElements = DOM.getAllElements();

		return (double) coverageTrueSize / allElements;
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
