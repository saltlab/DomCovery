package salt.domcoverage.core.metrics;

import java.util.List;

import flex.messaging.io.ArrayList;
import salt.domcoverage.core.RoundTripDOMCoverage;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.TestUtil;

public class ClickableElements {
	public static void findClickableElements(List<String> allTests) {
		ConstantVars.Clickable_mode = true;
		ConstantVars.oracleAssertion = false;
		//
		// execute test
		// RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		// rtdc.execute(testLocationFolder, "DomcoveryOutput/gallery/Tas/");

		// List<String> allTests = TestUtil.getAllTests(testLocationFolder, ".java");
		ConstantVars.PROXY_PORT++;
		TestUtil.executeUnitTests(allTests);

		ConstantVars.Clickable_mode = false;

	}

	public static List<String> Elements = new ArrayList();

	public static void manipulateElements(String rawResponse) {
		if (!Elements.contains(rawResponse)) {
			for (String typeandId : Elements) {
				if (equals(typeandId, rawResponse)) {
					if (getResponseType(rawResponse).equals("remove"))
						Elements.remove(typeandId);
					return;
				}
			}
			if (getResponseType(rawResponse).equals("add"))
				Elements.add(rawResponse);
		}
	}

	private static Object getResponseType(String rawResponse) {
		String[] splitRaw = rawResponse.split(ConstantVars.SEPARATOR);
		String typeRaw = splitRaw[2].toLowerCase().trim();
		return typeRaw;
	}

	private static boolean equals(String typeandId, String rawResponse) {

		String[] split = typeandId.split(ConstantVars.SEPARATOR);
		String type = split[0].toLowerCase().trim();
		String id = split[1].trim();
		String html = typeandId.substring(typeandId.indexOf(split[3].trim()));
		// String html = split[3].trim();

		String[] splitRaw = rawResponse.split(ConstantVars.SEPARATOR);
		String typeRaw = splitRaw[0].toLowerCase().trim();
		String idRaw = splitRaw[1].trim();
		int indexOf = rawResponse.indexOf(splitRaw[3].trim());
		String htmlRaw = rawResponse.substring(indexOf);

		if (!typeRaw.equals(type))
			return false;

		if (!idRaw.equals(id))
			return false;

		boolean domsSimilar = DomInterStateCoverage.domsSimilar(html, htmlRaw);
		if (!domsSimilar)
			return false;

		return true;
	}

}
