package salt.domcoverage.core.dom.clustering;

public class DataClustererWithRelativeSimilarity extends DataClusterer {

	boolean domsSimilar(int i, int j, double[][] distances) {

		double disij = distances[i][j];
		double disji = distances[j][i];

		boolean similar = disij <= 16;
		System.out.println("similar?: " + similar);
		return similar;
	}

}
