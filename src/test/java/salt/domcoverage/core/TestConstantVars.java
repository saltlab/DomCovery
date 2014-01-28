package salt.domcoverage.core;

import salt.domcoverage.core.utils.ConstantVars;

public class TestConstantVars {

	public static String userDir = System.getProperty("user.dir");

	public static String currentTest = "src/main/java/salt/domcoverage/casestudies/clarolinetest";

	public static String clarolineTestLocation4test = userDir + "/" + currentTest;
	public static String clarolineTestLocation4testInstrumented = userDir + "/" + ConstantVars.INSTRUMENTED_CODE_LOCATION + currentTest;

	public static String clarolineTestLocation = userDir + "/src/main/java/salt/domcoverage/casestudies/claroline";
	public static String saltlabTestLocation = userDir + "/src/main/java/salt/domcoverage/casestudies/saltlabinstrumented";
	public static String saltlabTestDemoLocation = userDir + "/src/main/java/salt/domcoverage/casestudies/saltlabinstrumented/Demo.java";
	public static String saltlabTestFuncLocation = userDir
			+ "/src/main/java/salt/domcoverage/casestudies/saltlabinstrumented/FuncSaltTest.java";

	public static String photogalleryTestLocation = userDir + "/src/main/java/salt/domcoverage/casestudies/photogallery";

	public static String jsCoveredElement = userDir + "/src/main/java/salt/domcoverage/casestudies/claroline/CopyOfadd_class.java";
	public static String add_class = userDir + "/src/main/java/salt/domcoverage/casestudies/claroline/add_class.java";

	public static String theorgnizerlocation = userDir + "/src/main/java/salt/domcoverage/casestudies/theorganizerdomcovery";

	public static final String add_story_assert = userDir
			+ "/src/main/java/salt/domcoverage/casestudies/photogallery/add_story_assert.java";

}
