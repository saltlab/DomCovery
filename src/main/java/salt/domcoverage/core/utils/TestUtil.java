package salt.domcoverage.core.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

	public static List<String> getAllTests(String folder) {
		ArrayList<String> ret = new ArrayList<String>();
		ArrayList<File> filesInDirectoryWithExtension = DOMUtility.getFilesInDirectoryWithExtension(folder, ".java");
		for (File file : filesInDirectoryWithExtension) {
			ret.add(file.getAbsolutePath());
		}
		return ret;

	}
}
