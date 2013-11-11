package salt.domcoverage.core.metrics;

import java.io.File;

public class ElementCoverageData {

	public File getDomFile() {
		return domFile;
	}

	public String getDomFileName() {
		return domFile.getName();
	}

	public void setDomFile(File domFile) {
		this.domFile = domFile;
	}

	public String getDirectCoverage() {
		return directCoverage;
	}

	public void setDirectCoverage(String directCoverage) {
		this.directCoverage = directCoverage;
	}

	public String getIndirectCoverage() {
		return indirectCoverage;
	}

	public void setIndirectCoverage(String indirectCoverage) {
		this.indirectCoverage = indirectCoverage;
	}

	public String getAssertedCoverage() {
		return assertedCoverage;
	}

	public void setAssertedCoverage(String assertedCoverage) {
		this.assertedCoverage = assertedCoverage;
	}

	public String getClickableCoverage() {
		return clickableCoverage;
	}

	public void setClickableCoverage(String clickableCoverage) {
		this.clickableCoverage = clickableCoverage;
	}

	private File domFile;
	private String directCoverage;
	private String indirectCoverage;
	private String assertedCoverage;
	private String clickableCoverage;

	public ElementCoverageData(File file, String dirCovAll, String indirectCovAll, String assertedCovAll, String covClickAll) {
		domFile = file;
		directCoverage = dirCovAll;
		indirectCoverage = indirectCovAll;
		assertedCoverage = assertedCovAll;
		clickableCoverage = covClickAll;

	}

}
