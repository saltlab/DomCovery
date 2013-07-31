package salt.domcoverage.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Utils {

	public static void print2DArray(double[][] distances) {

		for (double[] arr : distances) {
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void printArrayList(ArrayList cluster) {
		for (Object o : cluster) {
			System.out.println(cluster.size() + ": " + o);
		}
	}

	public static void printArrayList(List<String> cluster) {
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
				FileUtils.writeStringToFile(new File(loc + i + ".html"), doms.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
