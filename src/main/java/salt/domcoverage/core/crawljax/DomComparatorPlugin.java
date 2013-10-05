package salt.domcoverage.core.crawljax;

import java.util.List;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.dom.DomComparator;
import salt.domcoverage.core.dom.DomComparatorUsingSchema;
import salt.domcoverage.core.dom.clustering.DataClustererWithRelativeSimilarity;
import salt.domcoverage.core.metrics.DomStateCoverage;

import com.crawljax.core.CrawlerContext;
import com.crawljax.core.plugin.DomChangeNotifierPlugin;
import com.crawljax.core.plugin.Plugin;
import com.crawljax.core.state.Eventable;

import flex.messaging.io.ArrayList;

public class DomComparatorPlugin implements DomChangeNotifierPlugin {

	public boolean isDomChanged(CrawlerContext context, String domBefore, Eventable e, String domAfter) {
		// DomStateCoverage.domsSimilar(this.getDom(), that.getDom())
		String before = new String(domBefore);
		String after = new String(domAfter);
		boolean similar = DomStateCoverage.domsSimilar(before, after);
		System.out.println("Domchanged? : " + !similar);
		return !similar;
	}

}
