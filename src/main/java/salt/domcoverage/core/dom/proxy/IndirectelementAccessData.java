package salt.domcoverage.core.dom.proxy;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import flex.messaging.io.ArrayList;

public class IndirectelementAccessData {

	public static List<String> Elements = new ArrayList();

	public static void addElements(String rawResponse) {

		Elements.add(rawResponse);
	}

}
