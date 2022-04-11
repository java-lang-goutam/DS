import java.util.*;

public class UndirectedDijkstraList {

	private class Node {
		private int vertex, weight;
		Node (final int vertex, final int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	final List<List<Node>> graph;
	final int[] parent;
	final int INF = Integer.MAX_VALUE;
	final int NO_PARENT = -1;
	final int N;

	public UndirectedDijkstraList(final int N) {
		this.N = N;
		graph = new ArrayList<>();
		parent = new int[N];
		for (int i=0; i<N; i++) graph.add(new ArrayList<>());
	}

	public int[] getShortestDistance(final int source) {
		final int[] dist = new int[N];
		final PriorityQueue<Node> heap = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight); 

		Arrays.fill(dist, INF);

		dist[source] = 0;
		heap.add(new Node(source, 0));

		while (!heap.isEmpty()) {
			final Node curr = heap.poll();
			for (final Node to : graph.get(curr.vertex)) {
				if (dist[curr.vertex] + to.weight < dist[to.vertex]) {
					dist[to.vertex] = dist[curr.vertex] + to.weight;
					heap.offer(new Node(to.vertex, dist[to.vertex]));
					parent[to.vertex] = curr.vertex;
				}
			}
		}

		return dist;
	}

	public void printShortestDistance(final int source) {
		final int[] dist = getShortestDistance(source);
		for (int i=0; i<N; i++) {
			System.out.println(source + " -> " + i + " : " + dist[i]);
		}
	}

	public void addEdge(final int from, final int to, final int weight) {
		graph.get(from).add(new Node(to, weight));
		graph.get(to).add(new Node(from, weight));
	}

	public static void main(String[] args) {
		final UndirectedDijkstraList djkstra = new UndirectedDijkstraList(9);
		djkstra.addEdge(0, 1, 4);
		djkstra.addEdge(0, 7, 8);
		djkstra.addEdge(1, 7, 11);
		djkstra.addEdge(1, 2, 8);
		djkstra.addEdge(7, 8, 7);
		djkstra.addEdge(2, 8, 2);
		djkstra.addEdge(7, 6, 1);
		djkstra.addEdge(8, 6, 6);
		djkstra.addEdge(2, 5, 4);
		djkstra.addEdge(6, 5, 2);
		djkstra.addEdge(2, 3, 7);
		djkstra.addEdge(3, 5, 14);
		djkstra.addEdge(3, 4, 9);
		djkstra.addEdge(5, 4, 10);
		djkstra.printShortestDistance(0);
	}
}