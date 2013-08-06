package salt.domcoverage.core.dom;

import salt.domcoverage.core.code2instrument.ElementData;

public class DomComparatorUsingCoveredElements extends DomComparator {

	public double differences(ElementData eData1, ElementData eData2) {
		String element_dom1 = eData1.getElements().get(0);

		boolean contains = false;
		if (!element_dom1.equals("")) {
			if (eData2.contains(element_dom1))
				return 1;
			else
				return 10;
		}
		return 0;
	}

}
