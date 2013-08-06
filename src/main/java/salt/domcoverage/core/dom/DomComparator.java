package salt.domcoverage.core.dom;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import salt.domcoverage.core.code2instrument.ElementData;

public abstract class DomComparator {
	public double[][] extractDistances(List<ElementData> doms) {
		double[][] distances = new double[doms.size()][doms.size()];
		try {
			File difffile = new File("differences.csv");
			FileUtils.deleteQuietly(difffile);
			String writeTofile = "";
			for (int i = 0; i < doms.size(); i++) {
				for (int j = 0; j < doms.size(); j++) {
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
		return distances;
	}

	public abstract double differences(ElementData eData1, ElementData eData2);

}
