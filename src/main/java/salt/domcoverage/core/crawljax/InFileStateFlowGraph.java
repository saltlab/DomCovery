package salt.domcoverage.core.crawljax;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SerializationUtils;
import org.jgrapht.DirectedGraph;

import com.crawljax.core.ExitNotifier;
import com.crawljax.core.state.Eventable;
import com.crawljax.core.state.InMemoryStateFlowGraph;
import com.crawljax.core.state.StateFlowGraph;
import com.crawljax.core.state.StateVertex;

public class InFileStateFlowGraph {

	public DirectedGraph<StateVertex, Eventable> readGraphFromFile(String fileName) {
		return readGraphFromFile(new File(fileName));
	}

	public DirectedGraph<StateVertex, Eventable> readGraphFromFile(File f) {

		try {
			byte[] byt = FileUtils.readFileToByteArray(f);
			InMemoryStateFlowGraph deserialize = (InMemoryStateFlowGraph) SerializationUtils.deserialize(byt);
			DirectedGraph<StateVertex, Eventable> dgraph = deserialize.getsfg();
			System.out.println(f.getName() + " #vertex:" + dgraph.vertexSet().size() + " #edge: " + dgraph.edgeSet().size());
			return dgraph;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void print(String eXPLORATIONFILE, String eSTIMATIONFILE) {

		InFileStateFlowGraph fsfg = new InFileStateFlowGraph();
		DirectedGraph<StateVertex, Eventable> exploreSFG = fsfg.readGraphFromFile(eXPLORATIONFILE);
		DirectedGraph<StateVertex, Eventable> estimateSFG = fsfg.readGraphFromFile(eSTIMATIONFILE);
		PerformExplorationAndEstimation pee = new PerformExplorationAndEstimation(exploreSFG, estimateSFG);

		Map<StateVertex, List<Eventable>> nonFiredEventablesInSFG = pee.getnonFiredEventablesInSFG();
		Set<StateVertex> keySet = nonFiredEventablesInSFG.keySet();
		int size = 0;
		for (StateVertex stateVertex : keySet) {
			size += nonFiredEventablesInSFG.get(stateVertex).size();
		}
		System.out.println("size of eventables: " + size);
	}
}
