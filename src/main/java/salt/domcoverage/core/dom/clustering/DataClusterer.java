package salt.domcoverage.core.dom.clustering;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import salt.domcoverage.core.dom.DomComparator;
import salt.domcoverage.core.utils.DOMUtility;
import salt.domcoverage.core.utils.Utils;

public class DataClusterer {

	public List<List<String>> clusterData(ArrayList<File> domFiles, double[][] distances) {
		// TODO Auto-generated method stub
		String[] names = Utils.convertToArrayofString(domFiles);
		List<List<String>> clusters = new ArrayList<List<String>>();
		List<String> clustered = new ArrayList<String>();

		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			List<String> cluster = new ArrayList<String>();
			// cluster.add(name);
			for (int j = 0; j < names.length; j++) {
				if (domsSimilar(i, j, distances)) {
					if (!cluster.contains(names[j]) && !clustered.contains(names[j])) {
						cluster.add(names[j]);
						clustered.add(names[j]);
					}
				}
			}
			if (cluster.size() > 0) {
				Utils.printArrayList(cluster);
				clusters.add(cluster);
			}
		}

		return clusters;

	}

	private boolean domsSimilar(int i, int j, double[][] distances) {

		double disij = distances[i][j];
		double disji = distances[j][i];

		return disij == 1.0 || disji == 1.0;
	}

	public List<List<String>> clusterDataInFolder(String coverageFolder) {
		DomComparator dc = new DomComparator();
		ArrayList<File> domFiles = DOMUtility.getFilesInDirectoryWithExtension(coverageFolder, ".html");
		double[][] distances = dc.extractDistances(domFiles);

		DataClusterer dataClustering = new DataClusterer();
		List<List<String>> clusterData = dataClustering.clusterData(domFiles, distances);

		return clusterData;
	}

}
