package salt.domcoverage.core.dom.clustering;

import java.util.ArrayList;
import java.util.List;

import salt.domcoverage.core.code2instrument.ElementData;

public abstract class DataClusterer {

	public List<List<ElementData>> clusterData(List<ElementData> doms, double[][] distances) {
		// TODO Auto-generated method stub
		List<List<ElementData>> clusters = new ArrayList<List<ElementData>>();
		List<ElementData> clustered = new ArrayList<ElementData>();

		for (int i = 0; i < doms.size(); i++) {
			List<ElementData> currentCluster = new ArrayList<ElementData>();
			if (!clustered.contains(doms.get(i))) {
				currentCluster.add(doms.get(i));
				clustered.add(doms.get(i));
			} else
				continue;
			for (int j = i + 1; j < doms.size(); j++) {
				if (!clustered.contains(doms.get(j))) {
					if (domsSimilar(i, j, distances)) {
						currentCluster.add(doms.get(j));
						clustered.add(doms.get(j));
					}
				}
			}
			if (currentCluster.size() > 0) {
				// Utils.printArrayList(cluster);
				clusters.add(currentCluster);
			}
		}
		System.out.println("number of clusters: " + clusters.size());
		return clusters;

	}

	abstract boolean domsSimilar(int i, int j, double[][] distances);
}
