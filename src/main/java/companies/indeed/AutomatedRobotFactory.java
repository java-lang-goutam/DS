/**
I forget what question 1 is.

question 2:
You work in an automated robot factory. Once robots are assembled, they are sent to the shipping center via a series of autonomous delivery carts, each of which moves packages on a one-way route.

Given input that provides the (directed) steps that each cart takes as pairs, write a function that identifies all the start locations, and a collection of all of the possible ending locations for each start location.

In this diagram, starting locations are at the top and destinations are at the bottom - i.e. the graph is directed exclusively downward.

   A         E      J       Key:  {Origins}
  / \       / \      \             \
 B   C     F   L      M            {Destinations}
  \ /  \  /
   K     G
		/ \
	   H   I

paths = {
{"A", "B"},
{"A", "C"},
{"B", "K"},
{"C", "K"},
{"E", "L"},
{"F", "G"},
{"J", "M"},
{"E", "F"},
{"G", "H"},
{"G", "I"},
{"C", "G"}
}

Expected output (unordered):
{ "A": {"K", "H", "I"},
"E:" {"H", "L", "I"},
"J": {"M"} }

N: Number of pairs in the input.
*/

import java.util.*;

class AutomatedRobotFactory {

	private class Pair {
		String src, curr;
		Pair(String source, String current) {
			this.src = source;
			this.curr = current;
		}
	}

	public Map<String, Set<String>> findSourceDestination(String[][] paths) {

		Set<String> sources = new HashSet<>();
		Set<String> destinations = new HashSet<>();
		Map<String, List<String>> allSourceDest = new HashMap<>(); // outdegree

		for (String[] path : paths) {
			String from = path[0];
			String to = path[1];

			if (!destinations.contains(from)) sources.add(from);
			if (from.equals(to)) continue;

			destinations.add(to);
			if (sources.contains(to)) sources.remove(to);

			allSourceDest.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
		}

		Queue<Pair> sourceQueue = new LinkedList<>();
		for (String source : sources) {
			sourceQueue.offer(new Pair(source, null));
		}

		Map<String, Set<String>> result = new HashMap<>();

		while (!sourceQueue.isEmpty()) {
			Pair pair = sourceQueue.poll();
			String curr = pair.curr == null ? pair.src : pair.curr;
			List<String> des = allSourceDest.get(curr);
			if (des == null) {
				result.computeIfAbsent(pair.src, k -> new LinkedHashSet<>()).add(curr);
			} else {
				des.forEach(d -> sourceQueue.offer(new Pair(pair.src, d)));
			}
		}

		return result;
	}

	public static void main(String... args) {
		final String[][] paths = {
			{"A", "B"},
			{"A", "C"},
			{"B", "K"},
			{"C", "K"},
			{"E", "L"},
			{"F", "G"},
			{"J", "M"},
			{"E", "F"},
			{"G", "H"},
			{"G", "I"},
			{"C", "G"}
		};

		System.out.println(new AutomatedRobotFactory().findSourceDestination(paths));
	}
}
