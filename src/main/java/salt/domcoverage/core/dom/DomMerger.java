package salt.domcoverage.core.dom;

import java.util.ArrayList;
import java.util.List;

import salt.domcoverage.core.utils.DOMUtility;

public class DomMerger {

	public String mergeCluster(List<String> cluster) {

		String mergedDom = "";
		if (cluster.size() == 0)
			return mergedDom;

		mergedDom = DOMUtility.getDomFile(cluster.get(0));

		for (int i = 1; i < cluster.size(); i++) {
			String dom = cluster.get(i);
			String elementAccessedInDOM = DOMUtility.getElementAccessedInDOM(dom);
			if (DOMUtility.contains(elementAccessedInDOM, DOMUtility.getDomFileoffileName(dom))) {
				// replace elementAccessedInDom with corresponding element in
				// mergedDom
				mergedDom = DOMUtility.replace(elementAccessedInDOM, dom, mergedDom);

			}

		}

		return mergedDom;

	}

	public List<String> mergeDOMsofClusters(List<List<String>> clusters) {
		List<String> mergedDoms = new ArrayList<String>();
		for (List<String> clust : clusters) {
			String dom = mergeCluster(clust);
			mergedDoms.add(dom);
		}

		return mergedDoms;
	}

}
