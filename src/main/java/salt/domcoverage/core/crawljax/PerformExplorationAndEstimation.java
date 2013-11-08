package salt.domcoverage.core.crawljax;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.math.stat.descriptive.summary.Product;
import org.jgrapht.DirectedGraph;

import salt.domcoverage.core.metrics.DomInterStateCoverage;
import salt.domcoverage.core.utils.ConstantVars;

import com.crawljax.core.state.Eventable;
import com.crawljax.core.state.StateFlowGraph;
import com.crawljax.core.state.StateVertex;
import com.google.common.collect.ImmutableList;
import com.google.inject.internal.Lists;
import com.sun.jna.platform.win32.VerRsrc;

import flex.messaging.io.ArrayList;

public class PerformExplorationAndEstimation {

	public PerformExplorationAndEstimation() {
		productiveVertices = new ArrayList();
		productiveEdges = new ArrayList();
	}

	private DirectedGraph<StateVertex, Eventable> exploreSFG;
	private DirectedGraph<StateVertex, Eventable> estimateSFG;
	List<StateVertex> productiveVertices;
	private int estimationDepthThreshold;
	private List<Eventable> productiveEdges;

	public PerformExplorationAndEstimation(DirectedGraph<StateVertex, Eventable> exploreSFG, DirectedGraph<StateVertex, Eventable> estimateSFG) {
		super();
		this.exploreSFG = exploreSFG;
		this.estimateSFG = estimateSFG;
	}

	public Map<StateVertex, List<Eventable>> getnonFiredEventablesInSFG() {
		Map<StateVertex, List<Eventable>> map = new HashMap<StateVertex, List<Eventable>>();

		Set<StateVertex> vertexSet = exploreSFG.vertexSet();
		for (StateVertex stateVertex : vertexSet) {
			List<Eventable> eventablesInExplore = getUsedEventables(stateVertex);
			StateVertex containsSameStripedDom = containsSameNamedDom(stateVertex, estimateSFG);
			// Set<Eventable> containsSameStripedDom = estimateSFG.co(stateVertex);
			if (containsSameStripedDom != null) {
				Set<Eventable> eventablesInEstimate = getEdgesofVertexInSFG(containsSameStripedDom, estimateSFG);
				List<Eventable> eventables2add = subtractLists(Lists.newArrayList(eventablesInEstimate), eventablesInExplore);
				if (eventables2add.size() > 0)
					map.put(stateVertex, eventables2add);
			}

		}
		return map;
	}

	private List<Eventable> subtractLists(java.util.ArrayList<Eventable> list1, List<Eventable> list2) {
		// subtract list2 from list1
		List<Eventable> out = new ArrayList();
		for (Eventable e1 : list1) {
			boolean existsinlist2 = false;
			for (Eventable e2 : list2) {
				if (isEqual(e1, e2)) {
					existsinlist2 = true;
					break;
				}
			}
			if (!existsinlist2)
				out.add(e1);
		}
		return out;
	}

	private boolean isEqual(Eventable e1, Eventable e2) {
		StateVertex s_e1 = e1.getSourceStateVertex();
		StateVertex s_e2 = e2.getSourceStateVertex();
		StateVertex t_e1 = e1.getTargetStateVertex();
		StateVertex t_e2 = e2.getTargetStateVertex();
		if ((isEqual(s_e1, s_e2) || isEqual(s_e1, t_e2)) && (isEqual(t_e1, s_e2) || isEqual(t_e1, t_e2)))
			return true;
		return false;
	}

	private List<Eventable> getUsedEventables(StateVertex stateVertex) {
		Set<Eventable> edgeSet = exploreSFG.edgeSet();
		List<Eventable> eventablesInExplore = new ArrayList();
		for (Eventable eventable : edgeSet) {
			if (isEqual(eventable.getSourceStateVertex(), stateVertex) || isEqual(eventable.getTargetStateVertex(), stateVertex)) {
				eventablesInExplore.add(eventable);
			}
		}

		return eventablesInExplore;
	}

	private StateVertex containsSameNamedDom(StateVertex stateVertex, DirectedGraph<StateVertex, Eventable> estimateSFG2) {
		Set<StateVertex> vertexSet = estimateSFG2.vertexSet();
		for (StateVertex stateVertex2 : vertexSet) {
			boolean domsSimilar = isEqual(stateVertex, stateVertex2);// DomStateCoverage.domsSimilar(stateVertex.getDom(), stateVertex2.getDom());
			if (domsSimilar)
				return stateVertex2;
		}
		return null;
	}

	private String estimate() {

		// get all non-fired eventables in all vertises of explorSFG
		Map<StateVertex, List<Eventable>> verticesnonFiredEventable = this.getnonFiredEventablesInSFG();
		java.util.ArrayList<StateVertex> keySet = Lists.newArrayList(verticesnonFiredEventable.keySet());
		Collections.shuffle(keySet);
		Randomize randomize = new Randomize();

		int threshold = keySet.size();
		if (threshold > 4)
			threshold = threshold / 2;
		// threshold = 1;
		for (int i = 0; i <= threshold; i++) {
			// randomly choose one Vertice in VerticesFiredEventable and randomly one Eventable from ImmutableList<Eventable> list
			StateVertex randomStateVertex = keySet.get(i);
			List<Eventable> list = verticesnonFiredEventable.get(randomStateVertex);
			// Eventable randomEventableInVertex = randomize.getNonStaticRandom(immutableList);
			estimationDepthThreshold = 3;
			addIfproductive(randomStateVertex, list);
		}
		// estimation is finished time to count

		int unexploredProductiveTransitions = ComputeUnexploredProductiveTransitions(verticesnonFiredEventable);
		int statesFoundDuringSampling = productiveVertices.size();
		int sampledProductiveTransitions = productiveEdges.size();

		int unvisitedstates = (int) unexploredProductiveTransitions * unexploredProductiveTransitions / sampledProductiveTransitions;
		int visited = exploreSFG.vertexSet().size();
		double coverageofexploredsfg = (double) visited / (unvisitedstates + visited);
		String x = unexploredProductiveTransitions + ", " + statesFoundDuringSampling + ", " + sampledProductiveTransitions + ", " + unvisitedstates + ", " + coverageofexploredsfg;
		System.out.println(x);
		return x;

	}

	private Set<Eventable> getEdgesofVertexInSFG(StateVertex stateVertex, DirectedGraph<StateVertex, Eventable> estimateSF) {
		Set<Eventable> edgeSet = new HashSet<Eventable>();

		for (Eventable eventable : estimateSF.edgeSet()) {
			if (isEqual(eventable.getSourceStateVertex(), stateVertex) || isEqual(eventable.getTargetStateVertex(), stateVertex)) {
				edgeSet.add(eventable);
			}
		}
		return edgeSet;
	}

	private boolean isEqual(StateVertex targetStateVertex, StateVertex stateVertex) {
		return targetStateVertex.getName().equals(stateVertex.getName());
	}

	private StateVertex addIfproductive(StateVertex randomStateVertex, List<Eventable> edgesoftargetVertexofrandom) {
		if (estimationDepthThreshold >= 0) {
			// java.util.ArrayList<Eventable> edgesoftargetVertexofrandom = Lists.newArrayList(getEdgesofVertexInSFG(randomStateVertex, estimateSFG));
			Collections.shuffle(edgesoftargetVertexofrandom);
			Eventable edge = null;// new Randomize(1).getNonStaticRandom(edgesoftargetVertexofrandom);
			System.out.println("statevertex: " + randomStateVertex.getName() + " ( " + printEdges(edgesoftargetVertexofrandom) + " )");
			for (Eventable eventable : edgesoftargetVertexofrandom) {
				if (estimationDepthThreshold < 0) {
					break;
				}
				if (!productiveEdges.contains(eventable) && !IsEdgeInExploreSFG(eventable)) {
					edge = eventable;
					StateVertex targetVertexofRandom = getOtherVertexofEventable(edge, randomStateVertex);

					System.out.println("Threshold depth: " + estimationDepthThreshold);
					// System.out.println("statevertex: " + randomStateVertex.getName() +" ("+printEdges(edgesoftargetVertexofrandom));
					System.out.println("target statevertex of edge: " + targetVertexofRandom.getName());

					productiveEdges.add(edge);

					if (!productiveVertices.contains(targetVertexofRandom) && !IsVertexInExploreSFG(targetVertexofRandom)) {
						// it is a productive vertex
						productiveVertices.add(targetVertexofRandom);
						estimationDepthThreshold--;
						addIfproductive(targetVertexofRandom, Lists.newArrayList(getEdgesofVertexInSFG(targetVertexofRandom, estimateSFG)));
					}
				}
			}
		}
		return null;
	}

	private String printEdges(List<Eventable> edgesoftargetVertexofrandom) {
		String s = "";
		for (Eventable eventable : edgesoftargetVertexofrandom) {
			s += "  " + eventable.getSourceStateVertex().getName() + "->" + eventable.getTargetStateVertex().getName();
		}
		return s;
	}

	private StateVertex addIfproductive(StateVertex randomStateVertex, Eventable randomEventableInVertex) {
		if (estimationDepthThreshold >= 0) {
			System.out.println("Threshold depth: " + estimationDepthThreshold);
			System.out.println("statevertex: " + randomStateVertex.getName());
			StateVertex targetVertexofRandom = getOtherVertexofEventable(randomEventableInVertex, randomStateVertex);
			System.out.println("target statevertex of edge: " + targetVertexofRandom.getName());

			if (targetVertexofRandom == null)
				return null;
			if (!productiveEdges.contains(randomEventableInVertex) && !IsEdgeInExploreSFG(randomEventableInVertex)) {
				// it is a productive edge
				productiveEdges.add(randomEventableInVertex);
			}
			if (!productiveVertices.contains(targetVertexofRandom) && !IsVertexInExploreSFG(targetVertexofRandom)) {
				// it is a productive vertex
				productiveVertices.add(targetVertexofRandom);
				estimationDepthThreshold--;
				java.util.ArrayList<Eventable> edgesoftargetVertexofrandom = Lists.newArrayList(getEdgesofVertexInSFG(targetVertexofRandom, estimateSFG));
				Collections.shuffle(edgesoftargetVertexofrandom);
				Eventable nonStaticRandom = new Randomize(1).getNonStaticRandom(edgesoftargetVertexofrandom);
				for (Eventable eventable : edgesoftargetVertexofrandom) {
					if (!productiveEdges.contains(eventable) && !IsEdgeInExploreSFG(eventable)) {
						nonStaticRandom = eventable;
						break;
					}
				}
				addIfproductive(targetVertexofRandom, nonStaticRandom);
			}
		}
		return null;
	}

	private boolean IsEdgeInExploreSFG(Eventable edge) {
		boolean containsEdge = exploreSFG.containsEdge(edge);
		Set<Eventable> edgeSet = exploreSFG.edgeSet();
		for (Eventable eventable : edgeSet) {
			if (eventable.getIdentification().toString().equals(edge.getIdentification().toString()) && eventable.getSourceStateVertex().getName().equals(edge.getSourceStateVertex().getName()) && eventable.getTargetStateVertex().getName().equals(edge.getTargetStateVertex().getName()))
				return true;
		}
		return containsEdge;

	}

	private int ComputeUnexploredProductiveTransitions(Map<StateVertex, List<Eventable>> verticesFiredEventable) {
		int out = 0;
		Collection<List<Eventable>> values = verticesFiredEventable.values();
		for (List<Eventable> list : values) {
			for (Eventable eventable : list) {
				if (!productiveEdges.contains(eventable)) {
					// it is not explored during estimation
					if (!isVerticesofEdgeAlreadyExpolored(eventable)) {
						out++;
					}
				}
			}
		}

		return out;
	}

	private boolean isVerticesofEdgeAlreadyExpolored(Eventable eventable) {
		// see if eventable is productive
		StateVertex sourceStateVertex = eventable.getSourceStateVertex();
		StateVertex targetStateVertex = eventable.getTargetStateVertex();
		if (IsVertexInExploreSFG(sourceStateVertex) && IsVertexInExploreSFG(targetStateVertex))
			return true;
		if (productiveVertices.contains(sourceStateVertex) || productiveVertices.contains(targetStateVertex))
			return true;

		return false;
	}

	private boolean IsVertexInExploreSFG(StateVertex targetVertexofRandom) {
		StateVertex containsVertex = containsSameNamedDom(targetVertexofRandom, exploreSFG);
		if (containsVertex == null)
			return false;
		else
			return true;
	}

	private StateVertex getOtherVertexofEventable(Eventable eventable, StateVertex vertex) {
		if (eventable == null)
			return null;
		StateVertex sourceStateVertex = eventable.getSourceStateVertex();
		StateVertex targetStateVertex = eventable.getTargetStateVertex();
		if (sourceStateVertex.getName().equals(vertex.getName()))
			return targetStateVertex;
		if (targetStateVertex.getName().equals(vertex.getName()))
			return sourceStateVertex;

		return null;
	}

	public String estimate(String eXPLORATIONFILE, String eSTIMATIONFILE) {

		InFileStateFlowGraph fsfg = new InFileStateFlowGraph();
		exploreSFG = fsfg.readGraphFromFile(eXPLORATIONFILE);
		estimateSFG = fsfg.readGraphFromFile(eSTIMATIONFILE);
		String estimate = "";
		for (int i = 0; i < 20; i++) {
			productiveVertices = new ArrayList();
			productiveEdges = new ArrayList();
			estimate += "\n" + estimate();
		}
		getAverage(estimate);
		return estimate;
		// System.out.println(estimate);

	}

	private void getAverage(String estimate) {
		String[] lines = estimate.split("\n");
		double sumcoverageofexploredsfg = 0;
		double sumunvisitedstates = 0;
		if (lines.length <= 2)
			return;
		for (String line : lines) {
			if (line.isEmpty())
				continue;
			String[] data = line.split(", ");
			String unexploredProductiveTransitions = data[0];
			String statesFoundDuringSampling = data[1];
			String sampledProductiveTransitions = data[2];
			String unvisitedstates = data[3];
			String coverageofexploredsfg = data[4];
			sumunvisitedstates += Double.parseDouble(unvisitedstates);
			sumcoverageofexploredsfg += Double.parseDouble(coverageofexploredsfg);
		}
		double aveunvisited = (double) sumunvisitedstates / (lines.length - 1);
		System.out.println("average of unvisited states: " + aveunvisited);
		double ave = (double) sumcoverageofexploredsfg / (lines.length - 1);
		System.out.println("average state space extimation: " + ave);
	}

}
