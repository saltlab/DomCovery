package salt.domcoverage.core.domcomparison.clustering;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import salt.domcoverage.core.utils.Utils;

import com.apporiented.algorithm.clustering.Cluster;
import com.apporiented.algorithm.clustering.ClusteringAlgorithm;
import com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm;
import com.apporiented.algorithm.clustering.SingleLinkageStrategy;

public class DataHierarchicalClustering {

	public HashSet<List<String>> clusterDataToStringClusters(ArrayList<File> domFiles, double[][] distances) {
		HashSet<ArrayList<Cluster>> clusters = new HashSet<ArrayList<Cluster>>();
		HashSet<List<String>> ret = new HashSet<List<String>>();

		clusters = clusterData(domFiles, distances);
		for (ArrayList<Cluster> clus : clusters) {
			List<String> clusString = new ArrayList<String>();
			for (Cluster cluster : clus) {
				clusString.add(cluster.getName());
			}
			ret.add(clusString);
		}
		return ret;
	}

	public HashSet<ArrayList<Cluster>> clusterData(ArrayList<File> domFiles, double[][] distances) {

		String[] names = Utils.convertToArrayofString(domFiles);
		// String[] names = new String[] { "O1", "O2", "O3", "O4", "O5", "O6" };
		// double[][] distances = new double[][] { { 0, 1, 9, 7, 11, 14 }, { 1,
		// 0, 4, 3, 8, 10 }, { 9, 4, 0, 9, 2, 8 },
		// { 7, 3, 9, 0, 6, 13 }, { 11, 8, 2, 6, 0, 10 }, { 14, 10, 8, 13, 10, 0
		// } };

		ClusteringAlgorithm alg = new DefaultClusteringAlgorithm();
		Cluster cluster = alg.performClustering(distances, names, new SingleLinkageStrategy());
		// getleaves(cluster);
		// System.out.println();
		// System.out.println("countleafs: "+cluster.countLeafs());
		// ArrayList<Cluster> myClusters=getClustersAboveLeaves(cluster);
		getMyClusters(cluster);
		// cluster.toConsole(0);
		// System.out.println(cluster.toString());
		// DendrogramPanel dp = new DendrogramPanel();
		// dp.setModel(cluster);
		return clusters;
	}

	HashSet<ArrayList<Cluster>> clusters = new HashSet<ArrayList<Cluster>>();

	private void getMyClusters(Cluster cluster) {
		ArrayList<Cluster> members = new ArrayList<Cluster>();
		for (Cluster child : cluster.getChildren()) {
			if (child.isLeaf()) {
				members.add(child);
				System.out.println(child.getName());
			} else {
				if (members.size() > 0) {
					clusters.add(members);
					System.out.println("------------");
				}

				getMyClusters(child);
			}
		}
		if (members.size() > 0) {
			clusters.add(members);
			System.out.println("------------");
		}

	}

	ArrayList<Cluster> cs = new ArrayList<Cluster>();

	private ArrayList<Cluster> getClustersAboveLeaves(Cluster node) {
		getleaves(node);
		ArrayList<Cluster> aboveleaves = new ArrayList<Cluster>();

		for (Cluster c : cs) {
			Cluster cabove = node.getParent();
			if (!aboveleaves.contains(cabove))
				aboveleaves.add(cabove);
		}

		return aboveleaves;
	}

	private void getleaves(Cluster node) {
		for (Cluster child : node.getChildren()) {
			if (child.isLeaf()) {
				if (!cs.contains(node))
					cs.add(node.getParent());
			} else
				getleaves(child);
		}

	}

	public static void main(String[] args) {
		new DataHierarchicalClustering().clusterData(null, null);
	}

}
