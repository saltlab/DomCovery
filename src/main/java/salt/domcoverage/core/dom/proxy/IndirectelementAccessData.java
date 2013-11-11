package salt.domcoverage.core.dom.proxy;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import salt.domcoverage.core.metrics.DomInterStateCoverage;
import salt.domcoverage.core.utils.ConstantVars;
import flex.messaging.io.ArrayList;

public class IndirectelementAccessData {

	public static List<String> Elements = new ArrayList();

	public static void addElements(String rawResponse) {
		if (!Elements.contains(rawResponse)) {
			for (String typeandId : Elements) {
				if (equals(typeandId, rawResponse))
					return;
			}
			Elements.add(rawResponse);
		}
	}

	private static boolean equals(String typeandId, String rawResponse) {

		String[] split = typeandId.split(ConstantVars.SEPARATOR);
		String type = split[0].toLowerCase().trim();
		String id = split[1].trim();
		String html = split[2].trim();

		String[] splitRaw = rawResponse.split(ConstantVars.SEPARATOR);
		String typeRaw = splitRaw[0].toLowerCase().trim();
		String idRaw = splitRaw[1].trim();
		String htmlRaw = splitRaw[2].trim();

		if (!typeRaw.equals(type))
			return false;

		if (!idRaw.equals(idRaw))
			return false;

		boolean domsSimilar = DomInterStateCoverage.domsSimilar(html, htmlRaw);
		if (!domsSimilar)
			return false;

		return true;
	}

}
