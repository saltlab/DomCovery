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

import javax.imageio.ImageIO;

import static org.imgscalr.Scalr.*;

import org.apache.commons.io.FileUtils;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.dom.DOMScreenShot;
import salt.domcoverage.core.dom.DocumentObjectModel;
import salt.domcoverage.core.dom.DomComparator;
import salt.domcoverage.core.dom.DomComparatorUsingSchema;
import salt.domcoverage.core.dom.clustering.DataClustererWithRelativeSimilarity;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.DOMUtility;
import salt.domcoverage.core.utils.Utils;

public class DomStateCoverage {

	public void getDomStateCoverage() {
		// get doms from mergedstates and crawljax
		Map<String, String> mergedDoms = Utils.readFilesfromDirectory(ConstantVars.MERGEDLOCATION, "html");
		Map<String, String> crawljaxDoms = Utils.readFilesfromDirectory(ConstantVars.CRAWLJAXDOMS, "html");

		Map<String, String> domStateCoverage = getDomStateCoverage(mergedDoms, crawljaxDoms);
		replaceCrawljaxStates(domStateCoverage, mergedDoms);
		for (String string : domStateCoverage.keySet()) {
			System.out.println("crawjlax states covered: " + string + " is similar to mergeddom: " + domStateCoverage.get(string));
		}

		// add border to small snapshots of Overview plugin
		for (String crawljaxStateFileName : domStateCoverage.keySet()) {
			addBorder(ConstantVars.CRAWLJAX_IMAGES + crawljaxStateFileName.replace(".html", "") + "_small.jpg");
		}

		// take an snapshot of doms and copy them to snapshot folder of overview plugin
		DOMScreenShot domScreenShot = new DOMScreenShot();
		List<String> list = new ArrayList<String>();
		for (String crawljaxStateFileName : domStateCoverage.keySet()) {
			String domStateCoveredFileName = domStateCoverage.get(crawljaxStateFileName);
			domScreenShot.takeScreenshot(ConstantVars.MERGEDLOCATION + domStateCoveredFileName, ConstantVars.CRAWLJAX_IMAGES + crawljaxStateFileName.replace(".html", ".jpg"));
		}
		domScreenShot.finalize();

		// print resutls in file
		String outputToFile = "******for Whole Application: \n";
		double domStateCoverageRatio = (double) domStateCoverage.keySet().size() / crawljaxDoms.keySet().size();
		String coverageLine = "DOM state coverage is : " + domStateCoverageRatio + " (" + domStateCoverage.keySet().size() + " / " + crawljaxDoms.keySet().size() + ") \n";
		System.out.println(coverageLine);
		try {
			FileUtils.writeStringToFile(new File(ConstantVars.DomCoverageCriteria), outputToFile + coverageLine, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
