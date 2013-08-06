package salt.domcoverage.core.dom.clustering;

public class DataClustererWithCoveredElement extends DataClusterer {

	boolean domsSimilar(int i, int j, double[][] distances) {

		double disij = distances[i][j];
		double disji = distances[j][i];

		return disij == 1.0 || disji == 1.0;
	}

}
