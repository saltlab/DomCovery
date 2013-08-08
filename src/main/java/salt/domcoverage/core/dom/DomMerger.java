package salt.domcoverage.core.dom;

import java.util.ArrayList;
import java.util.List;

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

	public List<String> merge(List<ElementData> doms) {
		// compare elements
		double[][] distances = domComparator.extractDistances(doms);
		// cluster doms
		List<List<ElementData>> clusterData = dataClusterer.clusterData(doms, distances);
		// merge
		List<String> mergeDOMsofClusters = mergeDOMsofClusters(clusterData);

		Utils.writeArrayToFiles(mergeDOMsofClusters, ConstantVars.MERGEDLOCATION);

		return mergeDOMsofClusters;

	}

	public String mergeCluster(List<ElementData> clust) {

		String mergedDom = "";
		if (clust.size() == 0)
			return mergedDom;
		int maxIndex = selectStartingDom(clust);
		mergedDom = clust.get(maxIndex).getDomData();
		clust.remove(maxIndex);

		for (int i = 1; i < clust.size(); i++) {
			String dom = clust.get(i).getDomData();
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

	public List<String> mergeDOMsofClusters(List<List<ElementData>> clusterData) {
		List<String> mergedDoms = new ArrayList<String>();
		for (List<ElementData> clust : clusterData) {
			String dom = mergeCluster(clust);
			mergedDoms.add(dom);
		}

		return mergedDoms;
	}

}
