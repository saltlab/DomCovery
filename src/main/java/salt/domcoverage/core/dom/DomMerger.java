package salt.domcoverage.core.dom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.dom.clustering.DataClusterer;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.DOMUtility;
import salt.domcoverage.core.utils.Utils;

public class DomMerger {
	private DomComparator domComparator;
	private DataClusterer dataClusterer;

	public DomMerger(DomComparator dComp, DataClusterer dCluster) {

		domComparator = dComp;
		dataClusterer = dCluster;

	}

	public Map<String, String> merge(List<ElementData> doms) {
		// compare elements
		// double[][] distances = domComparator.extractDistances(doms);
		// cluster doms
		// List<List<ElementData>> clusterData = dataClusterer.clusterData(doms, distances);

		// clustering is already done at this stage. we just create a clusteredData with dom's elementData.
		List<List<ElementData>> clusterData = clusterDoms(doms);
		// merge
		Map<String, String> mergeDOMsofClusters = mergeDOMsofClusters(clusterData);

		Utils.writeMapToFiles(mergeDOMsofClusters, ConstantVars.MERGEDLOCATION);

		return mergeDOMsofClusters;

	}

	private List<List<ElementData>> clusterDoms(List<ElementData> doms) {
		List<List<ElementData>> ret = new ArrayList<List<ElementData>>();
		HashMap<String, List<ElementData>> hashmap = new HashMap<String, List<ElementData>>();
		for (ElementData elementData : doms) {
			String domFileName = elementData.getDomFileName();
			List<ElementData> hashmapValue = new ArrayList<ElementData>();
			if (hashmap.containsKey(domFileName)) {
				hashmapValue = hashmap.get(domFileName);
			}
			hashmapValue.add(elementData);
			hashmap.put(domFileName, hashmapValue);
		}

		// turn hashmap to list of lists
		for (String domfilename : hashmap.keySet()) {
			ret.add(hashmap.get(domfilename));
		}

		return ret;
	}

	public String mergeCluster(List<ElementData> clust) {

		String mergedDom = "";
		if (clust.size() == 0)
			return mergedDom;
		int maxIndex = selectStartingDom(clust);
		mergedDom = clust.get(maxIndex).getDomData();
		// if (clust.size() == 1)
		// return DOMUtility.replace(clust.get(0).getBy(), mergedDom);
		// clust.remove(maxIndex);

		for (int i = 0; i < clust.size(); i++) {
			mergedDom = DOMUtility.replace(clust.get(i).getBy(), mergedDom);

			// String elementAccessedInDOM =
			// DOMUtility.getElementAccessedInDOM(dom);

			// if (DOMUtility.contains(elementAccessedInDOM,
			// DOMUtility.getDomFileoffileName(dom))) {
			// replace elementAccessedInDom with corresponding element in
			// mergedDom
			// mergedDom = DOMUtility.replace(elementAccessedInDOM, dom,
			// mergedDom);

			// }

		}

		return mergedDom;

	}

	private int selectStartingDom(List<ElementData> clust) {
		List sizeofDom = new ArrayList<Integer>();
		for (int i = 0; i < clust.size(); i++) {
			sizeofDom.add(clust.get(i).getDomData().length());
		}
		// getIndexof
		int maxIndex = 0;
		for (int i = 1; i < sizeofDom.size(); i++) {
			int newnumber = (Integer) sizeofDom.get(i);
			Integer clusterMaxIndex = (Integer) sizeofDom.get(maxIndex);
			if (newnumber > clusterMaxIndex) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	// public Map<String, String> mergeDOMsofClusters(List<List<ElementData>> clusterData) {
	// Map<String, String> mergedDomsWith = new HashMap<String, String>();
	// int i = 0;
	// for (List<ElementData> clust : clusterData) {
	// String dom = mergeCluster(clust);
	// if (clust.size() > 0)
	// mergedDomsWith.put(String.valueOf(i++), dom);
	// }
	// return mergedDomsWith;
	// }
	public Map<String, String> mergeDOMsofClusters(List<List<ElementData>> clusterData) {
		Map<String, String> mergedDomsWith = new HashMap<String, String>();
		for (List<ElementData> clust : clusterData) {
			String dom = mergeCluster(clust);
			if (clust.size() > 0)
				mergedDomsWith.put(clust.get(0).getDomFileName(), dom);
		}
		return mergedDomsWith;
	}
	// public List<String> mergeDOMsofClusters(List<List<ElementData>> clusterData) {
	// // List<String> mergedDoms = new ArrayList<String>();
	// for (List<ElementData> clust : clusterData) {
	// String dom = mergeCluster(clust);
	// mergedDoms.add(dom);
	// }
	// Collections.sort(mergedDoms);
	// return mergedDomsWith;
	// }

}
