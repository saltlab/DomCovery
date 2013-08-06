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
			String name = doms.get(i).getDomFileName();
			List<ElementData> cluster = new ArrayList<ElementData>();
			// cluster.add(name);
			for (int j = 0; j < doms.size(); j++) {
				if (domsSimilar(i, j, distances)) {
					if (!cluster.contains(doms.get(j)) && !clustered.contains(doms.get(j))) {
						cluster.add(doms.get(j));
						clustered.add(doms.get(j));
					}
				}
			}
			if (cluster.size() > 0) {
				// Utils.printArrayList(cluster);
				clusters.add(cluster);
			}
		}

		return clusters;

	}

	abstract boolean domsSimilar(int i, int j, double[][] distances);
}
