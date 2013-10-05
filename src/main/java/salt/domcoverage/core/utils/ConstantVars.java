package salt.domcoverage.core.utils;

public class ConstantVars {

	public static final String COVERAGE_LOCATION = "Coverage/";
	public static final String SEPARATOR = "~~";
	public static final String COVERAGE_COVERED_ELEMENTS_CSV = COVERAGE_LOCATION + "CoveredElements.csv";
	public static final String MERGEDLOCATION = "MergedState/";
	public static final String INSTRUMENTED_CODE_LOCATION = "instrumentedCode/";
	public static final String HTML_EXTENSION = ".html";

	public static final String STYLE = "<style  type=\"text/css\"> [coverage=\"true\"]{outline:10px solid green;}</style>";
	public static final int MINIMUM_LENGTH_OF_DOM = 50;
	public static final String DISTANCES_ARRAY = "Distances.csv";
	public static final String[] seleniumDomRelatedMethodCallList = new String[] { "findElement" };
	public static final String[] ELEMENTS_NOT_COUNT = new String[] { "p", "form", "tbody", "tabular", "span", "thead", "h1", "h2", "h3", "br", "hr", "code", "i", "kbd", "pre", "small", "strong", "abbr", "ul", "ol", "dl", "th", "select" };
	public static final String[] ELEMENTS_TO_COUNT = new String[] { "div", "input", "a", "li", "td", "tr", "table", "option", "img", "dt", "iframe", "textarea" };
	public static final String INJECT_ELEMENT_ACCESS_JS = "elementaccessinject.js";
	public static final String INJECT_ELEMENT_ACCESS_CSS = "elementaccessinject.css";
	public static final String CLICKABLE_DETECTOR_JS = "clickable-detector-pre.js";
	public static final String CLICKABLE_DETECTOR_CSS = "clickable-detected.css";
	public static final String VISIBLE_ELEMENT_DETECTOR_JS = "visible-element-detect.js";
	public static final String VISIBLE_ELEMENT_DETECTOR_CSS = "visible-element-detect.css";
	public static final String CSS_CODE_INJECT = "cssCodeInject.css";
	public static final String JS_CODE_INJECT = "jsCodeInject.js";

	public static final String PROXY_IP = "127.0.0.1";
	public static final int PROXY_PORT = 8884;
	public static final String[] SELENIUMDRIVER_METHODCALLS = new String[] { "FirefoxDriver", "ChromeDriver" };
	public static final String DomCoverageCriteria = "DomCoverageCriteriaReport.txt";
	public static final double SIMILARITY_THRESHOLD = 34;// 5;// 34;//
	public static final String ELEMENTS_SEPARATOR = "@@@@@@@@@";
	public static final String CRAWLJAXDOMS = "out/doms/";
	public static final String CRAWLOVERVIEW = "out";
	public static final String CRAWLJAX_IMAGES = "out/screenshots/";
	public static final boolean ENFORCE_SIMILARITY_FROM_BEGINING = true;
	public static boolean JS_REWRITE_EXECUTED = false;

}
