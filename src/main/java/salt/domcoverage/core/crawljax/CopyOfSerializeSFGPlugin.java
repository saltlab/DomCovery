package salt.domcoverage.core.crawljax;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SerializationUtils;
import org.jgrapht.DirectedGraph;

import com.crawljax.core.CrawlSession;
import com.crawljax.core.ExitNotifier.ExitStatus;
import com.crawljax.core.plugin.PostCrawlingPlugin;
import com.crawljax.core.state.Eventable;
import com.crawljax.core.state.InMemoryStateFlowGraph;
import com.crawljax.core.state.StateFlowGraph;
import com.crawljax.core.state.StateVertex;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class CopyOfSerializeSFGPlugin implements Serializable, PostCrawlingPlugin {

	private File file;

	public CopyOfSerializeSFGPlugin(File f) {
		file = f;
	}

	public void postCrawling(CrawlSession session, ExitStatus exitStatus) {
		InMemoryStateFlowGraph sfg = (InMemoryStateFlowGraph) session.getStateFlowGraph();
		// StateVertex obj = sfg.getAllEdges().asList().get(0).getSourceStateVertex();
		for (StateVertex stateVertex : sfg.getAllStates()) {
			System.out.println("vertexname: " + stateVertex.getName());
			ImmutableList<Eventable> usedEventables = stateVertex.getUsedEventables();
			System.out.println(usedEventables.size());

		}

		// PerformExplorationAndEstimation pees = new PerformExplorationAndEstimation();

		// pees.estimate(sfgdirectedgraph, sfgdirectedgraph);

	}
}
