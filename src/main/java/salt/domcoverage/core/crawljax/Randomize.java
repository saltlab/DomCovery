package salt.domcoverage.core.crawljax;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.crawljax.core.state.Eventable;
import com.crawljax.core.state.StateVertex;
import com.google.inject.internal.Lists;

public class Randomize {
	private static Set<Integer> VertexHistory = new HashSet<Integer>();
	private static Set<Integer> EdgeHistory = new HashSet<Integer>();

	public int getRandom(int i) {
		return new Random().nextInt(i);
	}

	Randomize() {
		VertexHistory = new HashSet<Integer>();
		EdgeHistory = new HashSet<Integer>();
	}

	public Randomize(int i) {
		super();// TODO Auto-generated constructor stub
	}

	public StateVertex getRandom(Set<StateVertex> keySet) {
		int round = keySet.size() * 2;
		while (round >= 0) {
			round--;
			System.out.println("round: " + round);
			Integer random = getRandom(keySet.size());
			System.out.println("random: " + random);
			if (!VertexHistory.contains(random)) {
				VertexHistory.add(random);
				return Lists.newArrayList(keySet).get(random);
			}
		}
		return null;
	}

	public Eventable getRandom(List<Eventable> immutableList) {
		int round = immutableList.size();
		while (round >= 0) {
			round--;
			int random = getRandom(immutableList.size());
			if (!EdgeHistory.contains(random)) {
				EdgeHistory.add(random);
				return immutableList.get(random);
			}
		}
		return null;
	}

	public Eventable getNonStaticRandom(List<Eventable> immutableList) {
		int random = getRandom(immutableList.size());
		return immutableList.get(random);
	}

}
