package salt.domcoverage.core.utils;

public class ConstantVars {

	public static final String COVERAGE_LOCATION = "Coverage/";
	public static final String SEPARATOR = "~~";
	public static final String COVERAGE_COVERED_ELEMENTS_CSV = COVERAGE_LOCATION + "CoveredElements.csv";
	public static final String MERGEDLOCATION = "MergedState/";
	public static final String HTML_EXTENSION = ".html";

	public static final String STYLE = "<style  type=\"text/css\"> [coverage=\"true\"]{outline:10px solid green;}</style>";
	public static final int MINIMUM_LENGTH_OF_DOM = 50;
	public static final String DISTANCES_ARRAY = "Distances.csv";
	public static final String[] seleniumDomRelatedMethodCallList = new String[] { "findElement" };
	public static final String[] ELEMENTS_NOT_COUNT = new String[] { "p", "form", "tbody", "tabular", "span", "thead", "h1", "h2", "h3", "br", "hr", "code", "i", "kbd", "pre", "small", "strong", "abbr", "ul", "ol", "dl", "th", "select" };
	public static final String[] ELEMENTS_TO_COUNT = new String[] { "div", "input", "a", "li", "td", "tr", "table", "option", "img", "dt", "iframe", "textarea" };
	public static final String INJECT_ELEMENT_ACCESS_JS = "elementaccessinject.js";
	public static final String INJECT_ELEMENT_ACCESS_CSS = "elementaccessinject.css";

	public static final String PROXY_IP = "127.0.0.1";
	public static final int PROXY_PORT = 8884;

}
