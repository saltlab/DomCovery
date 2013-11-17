package salt.domcoverage.core.utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import salt.domcoverage.core.code2instrument.ElementData;

public class Utils {

	public static void writeStringToFile(String filename, String out) {
		try {
			FileUtils.writeStringToFile(new File(filename), out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void print2DArray(double[][] distances) {

		for (double[] arr : distances) {
			System.out.println(Arrays.toString(arr));
		}
	}

	public static String printSubstring(String s, int atmost) {
		int ind = (s.length() > atmost) ? atmost : s.length();
		return s.substring(0, ind) + "  ........";

	}

	public static void printList(List cluster) {
		for (Object o : cluster) {
			System.out.println(cluster.size() + ": " + o);
		}
	}

	public static void printArrayList(List<Object> cluster) {
		System.out.println(cluster.size() + "::: ");
		for (Object o : cluster) {
			System.out.println("::: " + o);
		}
	}

	public static String[] convertToArrayofString(ArrayList<File> domFiles) {

		String[] array = new String[domFiles.size()];
		for (int i = 0; i < domFiles.size(); i++) {
			array[i] = domFiles.get(i).getName();
		}
		return array;
	}

	public static String getFileName(String file) {
		String name = new File(file).getName();
		name = (name.contains(".")) ? name.substring(0, name.indexOf(".")) : name;
		return name;
	}

	public static String getFileFullName(String file) {
		file = file.replace(System.getProperty("user.dir"), "");
		file = file.replace("/src/main/java/", "");
		file = (file.contains(".")) ? file.substring(0, file.indexOf(".")) : file;
		file = file.replace("/", ".");
		return file;
	}

	public static void writeArrayToFiles(List<String> doms, String loc) {
		try {
			FileUtils.deleteQuietly(new File(loc));
			for (int i = 0; i < doms.size(); i++) {
				FileUtils.writeStringToFile(new File(loc + i + ".html"), (String) doms.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeMapToFiles(Map<String, String> doms, String loc) {
		try {
			FileUtils.deleteQuietly(new File(loc));
			for (String domfilename : doms.keySet()) {
				FileUtils.writeStringToFile(new File(loc + domfilename), doms.get(domfilename));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void write2DArrayToFile(double[][] distances) {
		String str2write = "";
		try {
			FileUtils.deleteQuietly(new File(ConstantVars.DISTANCES_ARRAY));

			for (int i = 0; i < distances.length; i++) {
				String line = "";
				for (int j = 0; j < distances.length; j++) {
					line += distances[i][j] + ",";
				}
				line += "\n";
				str2write += line;
			}
			FileUtils.writeStringToFile(new File(ConstantVars.DISTANCES_ARRAY), str2write);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static double[][] loadArrayFromFile(File file) {
		try {

			List<String> fileLines = FileUtils.readLines(file);
			double[][] distances = new double[fileLines.size()][fileLines.size()];
			for (int i = 0; i < fileLines.size(); i++) {
				String line = fileLines.get(i);
				String[] split = line.split(",");
				for (int j = 0; j < split.length; j++) {
					distances[i][j] = Double.parseDouble(split[j]);
				}
			}
			return distances;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static List<ElementData> keepUniqueElements(List<ElementData> elementds) {
		List<ElementData> output = new ArrayList<ElementData>();
		for (ElementData ed : elementds) {
			for (ElementData outputED : output) {
				// if (outputED.contains(element))
			}
		}
		return null;

	}

	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public static Map<String, String> readFilesfromDirectory(String location, String ext) {
		String[] st = new String[] { ext };
		Map<String, String> doms = new HashMap<String, String>();
		Collection<File> listFiles = FileUtils.listFiles(new File(location), st, false);
		for (File file2 : listFiles) {
			try {
				doms.put(file2.getName(), FileUtils.readFileToString(file2));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return doms;
	}

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	public static double round(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.#");
		return Double.valueOf(twoDForm.format(d));

	}

	public static double round100(Double d) {
		if (d.isNaN() || d == Double.valueOf(0.0))
			return 0;
		d = d * 100;
		DecimalFormat twoDForm = new DecimalFormat("#.#");
		return Double.valueOf(twoDForm.format(d));

	}

	public static String format(double domStateCoverageFinal, int statesCoveredbyTests, int sumofStates) {
		return domStateCoverageFinal + "% (" + statesCoveredbyTests + " / " + sumofStates + ")";
	}

	public static void resetProgramVariables() {
		ConstantVars.oracleAssertion = false;
		ConstantVars.indirectCoverageMode = false;
		ConstantVars.JS_REWRITE_EXECUTED = false;

	}

	public static void Record(String testName, String domfilename) {
		try {
			String buffer = testName + ConstantVars.SEPARATOR + "time" + ConstantVars.SEPARATOR + "by" + ConstantVars.SEPARATOR + "elementFile" + ConstantVars.SEPARATOR + domfilename + ConstantVars.SEPARATOR + ConstantVars.oracleAssertion + ConstantVars.SEPARATOR + ConstantVars.indirectCoverageMode + "\r";

			FileUtils.writeStringToFile(new File(ConstantVars.EdgeData), buffer, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public static String format(double directCovStr, double directCov, int directCovAllElements) {
	// return directCovStr + " (" + round(directCov) + " / " + directCovAllElements + ")";
	// }

	public static void updatecrawljaxlocation(String crawljaxReportFolder) {
		ConstantVars.CRAWLOVERVIEW = crawljaxReportFolder + "/";
		ConstantVars.CRAWLJAXDOMS = ConstantVars.CRAWLOVERVIEW + "doms/";
		ConstantVars.CRAWLJAXSTATES = ConstantVars.CRAWLOVERVIEW + "states/";
		ConstantVars.CRAWLJAX_IMAGES = ConstantVars.CRAWLOVERVIEW + "screenshots/";

	}
}
