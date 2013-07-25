package salt.domcoverage.core.utils;

import java.io.File;
import java.util.ArrayList;

public class Utils {

	public static void printArrayList(ArrayList e) {
		for (Object o : e) {
			System.out.println(e.size() + ": " + o);
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
}
