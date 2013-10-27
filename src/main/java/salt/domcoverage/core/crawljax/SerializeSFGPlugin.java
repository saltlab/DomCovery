package salt.domcoverage.core.crawljax;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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

public class SerializeSFGPlugin implements Serializable, PostCrawlingPlugin {

	private File file;

	public SerializeSFGPlugin(File f) {
		file = f;
	}

	public void postCrawling(CrawlSession session, ExitStatus exitStatus) {
		InMemoryStateFlowGraph sfg = (InMemoryStateFlowGraph) session.getStateFlowGraph();
		// StateVertex obj = sfg.getAllEdges().asList().get(0).getSourceStateVertex();
		byte[] serialize = SerializationUtils.serialize(sfg);

		try {
			FileUtils.writeByteArrayToFile(file, serialize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// InMemoryStateFlowGraph deserialize = (InMemoryStateFlowGraph) SerializationUtils.deserialize(serialize);
		// DirectedGraph<StateVertex, Eventable> sfgdirectedgraph = deserialize.getsfg();
		// System.out.println("vertex: " + sfgdirectedgraph);

		// PerformExplorationAndEstimation pees = new PerformExplorationAndEstimation();

		// pees.estimate(sfgdirectedgraph, sfgdirectedgraph);

	}
}
