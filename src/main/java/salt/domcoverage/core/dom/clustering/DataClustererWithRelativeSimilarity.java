package salt.domcoverage.core.dom.clustering;

import salt.domcoverage.core.utils.ConstantVars;

public class DataClustererWithRelativeSimilarity extends DataClusterer {

	boolean domsSimilar(int i, int j, double[][] distances) {

		double disij = distances[i][j];
		// double disji = distances[j][i];

		boolean similar = similarDomBasedonDiff(disij);
		System.out.println("similar?: " + similar);
		return similar;
	}

	public boolean similarDomBasedonDiff(double diff) {
		boolean similar = diff <= ConstantVars.SIMILARITY_THRESHOLD;
		return similar;
	}

}
