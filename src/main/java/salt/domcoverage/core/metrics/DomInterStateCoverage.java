package salt.domcoverage.core.metrics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import static org.imgscalr.Scalr.*;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.io.FileUtils;

import com.crawljax.core.state.Element;
import com.crawljax.plugins.crawloverview.model.Edge;
import com.crawljax.plugins.crawloverview.model.State;
import com.crawljax.plugins.crawloverview.model.TryStateEdge;
import com.google.common.collect.Lists;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.code2instrument.ElementDataPersist;
import salt.domcoverage.core.dom.DOMScreenShot;
import salt.domcoverage.core.dom.DocumentObjectModel;
import salt.domcoverage.core.dom.DomComparator;
import salt.domcoverage.core.dom.DomComparatorUsingSchema;
import salt.domcoverage.core.dom.clustering.DataClustererWithRelativeSimilarity;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.DOMUtility;
import salt.domcoverage.core.utils.Utils;

public class DomInterStateCoverage {

	private int numberofCrawljaxEdges;

	public Map<String, Double> getDomStateAndTransitionCoverage() {

		// get doms from mergedstates and crawljax
		Map<String, String> mergedDoms = Utils.readFilesfromDirectory(ConstantVars.MERGEDLOCATION, "html");
		Map<String, String> crawljaxDoms = Utils.readFilesfromDirectory(ConstantVars.CRAWLJAXDOMS, "html");
		Map<String, String> domStateCoverage = getDomStateCoverage(mergedDoms, crawljaxDoms);
		// key:crawljax , value:value

		List<String> statesNamesNotCrawled = ListUtils.subtract(com.google.inject.internal.Lists.newArrayList(mergedDoms.keySet()), Lists.newArrayList(domStateCoverage.values()));

		replaceCrawljaxStates(domStateCoverage, mergedDoms);
		Set<String> keySet = domStateCoverage.keySet();
		for (String string : keySet) {
			System.out.println("crawjlax states covered: " + string + " is similar to mergeddom: " + domStateCoverage.get(string));
		}

		// add border to small snapshots of Overview plugin
		for (String crawljaxStateFileName : keySet) {
			addBorder(ConstantVars.CRAWLJAX_IMAGES + crawljaxStateFileName.replace(".html", "") + "_small.jpg");
		}
		DOMScreenShot domScreenShot = new DOMScreenShot();

		for (String mergeddomFileName : statesNamesNotCrawled) {
			// copy states and their screenshots to crawljax folder
			try {
				String pathname = ConstantVars.CRAWLJAXDOMS + "-" + mergeddomFileName;
				File newhtmlfilename = new File(pathname);
				FileUtils.copyFile(new File(ConstantVars.MERGEDLOCATION + mergeddomFileName), newhtmlfilename);
				domScreenShot.takeScreenshot(pathname, ConstantVars.CRAWLJAX_IMAGES + newhtmlfilename.getName().replace(".html", ".jpg"), true);
				// add border
				addBorder(ConstantVars.CRAWLJAX_IMAGES + newhtmlfilename.getName().replace(".html", "") + "_small.jpg");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// take an snapshot of doms and copy them to snapshot folder of overview plugin
		for (String crawljaxStateFileName : keySet) {
			String domStateCoveredFileName = domStateCoverage.get(crawljaxStateFileName);
			domScreenShot.takeScreenshot(ConstantVars.MERGEDLOCATION + domStateCoveredFileName, ConstantVars.CRAWLJAX_IMAGES + crawljaxStateFileName.replace(".html", ".jpg"));
		}
		domScreenShot.finalize();

		createStatesAndEdges4report(statesNamesNotCrawled, domStateCoverage);

		// ////////////////get coverage

		int statesCoveredbyTests = mergedDoms.size();
		int estimatedNumberofStatesNotCrawledbyCrawljax = 0;
		int statesCoveredByCrawljax = crawljaxDoms.size();
		int statesVisitedBytestsbutnotCrwaljax = statesCoveredbyTests - domStateCoverage.size();

		int sumofStates = estimatedNumberofStatesNotCrawledbyCrawljax + statesCoveredByCrawljax + statesVisitedBytestsbutnotCrwaljax;
		double domStateCoverageFinal = (double) statesCoveredbyTests / sumofStates;

		int transitionsCoveredbyTests = edges.size();
		int estimatedNumberoftransitionsNotCrawledbyCrawljax = 0;
		int transitionsCoveredByCrawljax = numberofCrawljaxEdges;
		int transitionsVisitedBytestsbutnotCrwaljax = edges.size();

		int sumofTransitions = estimatedNumberoftransitionsNotCrawledbyCrawljax + transitionsCoveredByCrawljax + transitionsVisitedBytestsbutnotCrwaljax;
		double domTransitionCoverageFinal = (double) transitionsCoveredbyTests / sumofTransitions;

		// print resutls in file
		String outputToFile = "******for Whole Application: \n";
		String coverageSLine = "DOM state coverage is : " + domStateCoverageFinal + " (" + statesCoveredbyTests + " / " + sumofStates + ") \n";
		String coverageTLine = "DOM transition coverage is : " + domTransitionCoverageFinal + " (" + transitionsCoveredbyTests + " / " + sumofTransitions + ") \n";
		System.out.println(coverageSLine);
		System.out.println(coverageTLine);

		// // print resutls in file
		// String outputToFile = "******for Whole Application: \n";
		// int statesCovered = keySet.size();
		// int statesAll = crawljaxDoms.keySet().size();
		// double domStateCoverageRatio = (double) statesCovered / statesAll;
		// String coverageLine = "DOM state coverage is : " + domStateCoverageRatio + " (" + statesCovered + " / " + statesAll + ") \n";
		// System.out.println(coverageLine);
		try {
			FileUtils.writeStringToFile(new File(ConstantVars.DomCoverageCriteria), outputToFile + coverageSLine + coverageTLine, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// CoverageReport cr = new CoverageReport();
		// cr.addStateCoverage(statesCoveredbyTests, sumofStates);

		Map<String, Double> coverageResults = new HashMap<String, Double>();
		coverageResults.put(ConstantVars.DomStateCoverage, domStateCoverageFinal);
		coverageResults.put(ConstantVars.DomTransitionCoverage, domTransitionCoverageFinal);
		return coverageResults;
	}

	private void createStatesAndEdges4report(List<String> domfileNames, Map<String, String> domStateCoverage) {
		ElementCoverage elementCoverage = new ElementCoverage();

		// create state from dom files
		List<State> states = new ArrayList<State>();
		for (String string : domfileNames) {
			String name = "-" + string.replace(".html", "");
			// int fanin = elementCoverage.getDirectElementCoverage(ConstantVars.CRAWLJAXDOMS + name + ".html");
			// State state = new State(name, "", null, fanin, fanout, Integer.parseInt(name), failedevents);
			int parseInt = name.length();// Integer.parseInt(name);
			State state = new State(name, "", null, 0, 0, parseInt, null);
			states.add(state);
		}

		// create edgest from csv file
		List<Edge> edges = getEdges(domStateCoverage);

		TryStateEdge t = new TryStateEdge();
		t.addStatesAndEdges(states, edges, ConstantVars.CRAWLOVERVIEW);
		numberofCrawljaxEdges = t.getNumberofCrawljaxEdges();
	}

	List<Edge> edges = new ArrayList<Edge>();

	private List<Edge> getEdges(Map<String, String> domStateCoverage) {
		List<ElementData> ds = new ElementDataPersist().getElementsFromFile(ConstantVars.COVERAGE_COVERED_ELEMENTS_CSV);
		List<ElementData> doms = replaceDomswithNamesofMergedDoms(ds, domStateCoverage);
		edges = new ArrayList<Edge>();
		List<String> domsSeen = new ArrayList<String>();
		if (doms.isEmpty())
			return null;
		String prev = doms.get(0).getDomFileNamewithoutext();
		if (!domStateCoverage.containsKey(doms.get(0).getDomFileName()))
			prev = "-" + prev;
		for (int i = 1; i < doms.size(); i++) {
			String elem = doms.get(i).getDomFileNamewithoutext();
			if (!domStateCoverage.containsKey(doms.get(i).getDomFileName()))
				elem = "-" + elem;
			if (elem.equals(prev))
				continue;
			Edge e = new Edge(prev, elem, 0, "", String.valueOf(i), "", "", "true");
			edges.add(e);
			prev = elem;
		}

		return edges;
	}

	private List<ElementData> replaceDomswithNamesofMergedDoms(List<ElementData> doms, Map<String, String> domStateCoverage) {
		Map<String, String> md = Utils.readFilesfromDirectory(ConstantVars.MERGEDLOCATION, "html");
		Map<String, String> mergedDoms = replacemergeddomswithsimilarcrawljaxstates(md, domStateCoverage);
		List<ElementData> out = new ArrayList<ElementData>();
		Map<String, String> correspondings = new HashMap<String, String>();// csv dom names to mergeddomnames
		for (String mergeddomname : mergedDoms.keySet()) {
			for (ElementData ed : doms) {
				if (correspondings.containsKey(ed.getDomFileName()))
					continue;
				boolean domsSimilar = domsSimilar(ed.retrieveDomString(), mergedDoms.get(mergeddomname));
				if (domsSimilar) {
					correspondings.put(ed.getDomFileName(), mergeddomname);
				}
			}
		}
		for (ElementData ed : doms) {
			if (correspondings.containsKey(ed.getDomFileName())) {
				ed.setDomFileName(correspondings.get(ed.getDomFileName()));
				out.add(ed);
			}
		}
		return out;
	}

	private Map<String, String> replacemergeddomswithsimilarcrawljaxstates(Map<String, String> md, Map<String, String> domStateCoverage) {
		Map<String, String> out = new HashMap<String, String>();
		for (String s : domStateCoverage.keySet()) {
			String val = domStateCoverage.get(s);
			for (String mergeddomfromFile : md.keySet()) {
				if (val.equals(mergeddomfromFile))
					out.put(s, md.get(mergeddomfromFile));
				else
					out.put(mergeddomfromFile, md.get(mergeddomfromFile));
			}
		}
		return out;
	}

	private void replaceCrawljaxStates(Map<String, String> domStateCoverage, Map<String, String> mergedDoms) {
		List<String> doms2getscreenshot = new ArrayList<String>();
		for (String crawljaxStateFileName : domStateCoverage.keySet()) {
			String mergedDomStateFileName = domStateCoverage.get(crawljaxStateFileName);
			String mergedDomState = mergedDoms.get(mergedDomStateFileName);
			try {
				FileUtils.write(new File(ConstantVars.CRAWLJAXDOMS + crawljaxStateFileName), mergedDomState, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void addBorder(String file) {
		BufferedImage img = null;
		try {
			File input = new File(file);
			img = ImageIO.read(input);
			// Let's add a little border before we return result.
			BufferedImage pad = pad(img, 4, Color.GREEN);
			ImageIO.write(pad, "jpg", input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Map<String, String> getDomStateCoverage(Map<String, String> mergedDoms, Map<String, String> crawljaxDoms) {
		Map<String, String> coveredDoms = new HashMap<String, String>();// from crwalajxdoms toooo mergeddoms

		for (String filename : crawljaxDoms.keySet()) {
			String crawljaxdom = crawljaxDoms.get(filename);
			String similarmergeddomfilename = similardominarray(crawljaxdom, mergedDoms);
			if (similarmergeddomfilename != null) {// this dom is not covered by test suite
				coveredDoms.put(filename, similarmergeddomfilename);
			}

		}
		return coveredDoms;
	}

	private String similardominarray(String crawljaxdom, Map<String, String> mergedDoms) {
		for (String filename : mergedDoms.keySet()) {
			String mergeddom = mergedDoms.get(filename);
			if (domsSimilar(crawljaxdom, mergeddom)) {
				// say yes
				return filename;
			}
		}
		return null;
	}

	public static boolean domsSimilar(String domBefore, String domAfter) {
		DomComparator dc = new DomComparatorUsingSchema();
		List<String> elems = new ArrayList();
		ElementData elementDataBefore = new ElementData("", "", "", domBefore, elems);
		ElementData elementDataAfter = new ElementData("", "", "", domAfter, elems);
		double differences = dc.differences(elementDataBefore, elementDataAfter);
		boolean similar = new DataClustererWithRelativeSimilarity().similarDomBasedonDiff(differences);
		return similar;
	}

}
