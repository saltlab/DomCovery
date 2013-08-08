package salt.domcoverage.core.dom;

import java.util.List;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.ElementNameQualifier;
import org.w3c.dom.Document;

import salt.domcoverage.core.utils.DOMUtility;

/**
 * This class allows to compare two Document objects and save the differences in
 * a list.
 * 
 * @author beze232056
 */
public class DOMDiffer {
	/**
	 * The control document. This is used as a base to compare the testDOM with.
	 */
	private final Document controlDOM;

	/**
	 * The test document. This is the document in which we want to detect
	 * differences.
	 */
	private final Document testDOM;

	/**
	 * Constructor.
	 * 
	 * @param controlDOM
	 *            The control DOM.
	 * @param testDOM
	 *            The test DOM.
	 */
	public DOMDiffer(Document controlDOM, Document testDOM) {
		this.controlDOM = controlDOM;
		this.testDOM = testDOM;
	}

	/**
	 * Compare the controlDOM and testDOM and save and return the differences in
	 * a list.
	 * 
	 * @return list with differences
	 */
	@SuppressWarnings("unchecked")
	public List<Difference> compare() {
		Diff diff = new Diff(this.controlDOM, this.testDOM, null, new ElementNameQualifier());
		DetailedDiff detDiff = new DetailedDiff(diff);
		// bypass not valid doms
		if (DOMUtility.isInvalidDom(controlDOM) || DOMUtility.isInvalidDom(testDOM))
			return null;
		return detDiff.getAllDifferences();
	}

}