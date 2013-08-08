package salt.domcoverage.core.dom;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.Utils;

public abstract class DomComparator {
	public double[][] extractDistances(List<ElementData> doms) {
		double[][] distances = new double[doms.size()][doms.size()];
		distances = setValues(distances, Double.MAX_VALUE);
		try {
			File difffile = new File("differences.csv");
			File file = new File(ConstantVars.DISTANCES_ARRAY);
			if (file.exists()) {
				return Utils.loadArrayFromFile(file);
			}
			FileUtils.deleteQuietly(difffile);
			String writeTofile = "";
			for (int i = 0; i < doms.size() - 1; i++) {
				for (int j = i + 1; j < doms.size(); j++) {
					// int size =
					// differencesSize(FileUtils.readFileToString(domFiles.get(i)),
					// FileUtils.readFileToString(domFiles.get(j)));
					double diff = differences(doms.get(i), doms.get(j));
					distances[i][j] = diff;
					String stringtoWrite = doms.get(i).getDomFileName() + "," + doms.get(j).getDomFileName() + "," + diff + "\n";
					// System.out.println(stringtoWrite);
					writeTofile += stringtoWrite;
				}
				writeTofile += "\n";
			}
			FileUtils.writeStringToFile(difffile, writeTofile, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Utils.write2DArrayToFile(distances);
		return distances;
	}

	private double[][] setValues(double[][] distances, double maxValue) {
		double[][] ret = new double[distances.length][distances.length];
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances.length; j++) {
				ret[i][j] = maxValue;
			}
		}
		return ret;
	}

	public abstract double differences(ElementData eData1, ElementData eData2);

}
