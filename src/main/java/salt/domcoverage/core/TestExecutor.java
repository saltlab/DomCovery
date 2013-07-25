package salt.domcoverage.core;

import org.junit.runner.JUnitCore;

import salt.domcoverage.core.utils.Utils;

public class TestExecutor {

	public void execute(String test) {
		try {
			String fileName = Utils.getFileFullName(test);
			Class<?> forName = Class.forName(fileName);
			JUnitCore.runClasses(forName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
