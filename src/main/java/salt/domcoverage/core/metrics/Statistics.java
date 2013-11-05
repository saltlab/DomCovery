package salt.domcoverage.core.metrics;

import java.text.DecimalFormat;
import java.util.Date;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.lang3.time.DurationFormatUtils;

import com.crawljax.core.CrawlSession;
import com.crawljax.core.state.StateFlowGraph;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@Immutable
public class Statistics {

	private final String DomStateCoverage;

	public Statistics(String domStateCoverage) {
		super();
		DomStateCoverage = domStateCoverage;
	}

	public String getDomStateCoverage() {
		return DomStateCoverage;
	}

}
