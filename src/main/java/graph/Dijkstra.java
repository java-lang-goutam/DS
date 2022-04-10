import java.util.Arrays;

public class Dijkstra {

	final int[][] adj;
	final int N;
	final int INF = Integer.MAX_VALUE;

	public Dijkstra(final int[][] adj) {
		this.adj = adj;
		this.N = adj.length;
	}

	public int[] getShortestPath(final int source) {
		final int[] dist = new int[N];
		final boolean[] included = new boolean[N];

		Arrays.fill(dist, INF);
		dist[source] = 0;

		for (int i = 0; i < N; i++) {

			// pick min unprocess vertex
			int u = minDistanceNode(dist, included);
			included[u] = true;

			// Update adjacent vertex distance
			for (int v = 0; v < N; v++) {
				if (!included[v] && adj[u][v] != 0 && dist[u] != INF && dist[u] + adj[u][v] < dist[v]) {
					dist[v] = dist[u] + adj[u][v];
				}
			}
		}

		return dist;
	}

	private int minDistanceNode(final int[] dist, final boolean[] included) {

		int min = INF, minIndex = -1;
		for (int i = 0; i < N; i++) {
			if (!included[i] && dist[i] < min) {
				min = dist[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	public void printShortestPaths(final int source) {
		final int[] dist = getShortestPath(source);
		for (int i = 0; i < N; i++) {
			System.out.println(source + " -> " + i + " : " + dist[i]);
		}
	}

	public static void main(String[] args) {
		final int graph[][] = new int[][] { 
			{ 0, 4, 0, 0, 0, 0, 0, 8, 0 },
			{ 4, 0, 8, 0, 0, 0, 0, 11, 0 },
			{ 0, 8, 0, 7, 0, 4, 0, 0, 2 },
			{ 0, 0, 7, 0, 9, 14, 0, 0, 0 },
			{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
			{ 0, 0, 4, 14, 10, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 2, 0, 1, 6 },
			{ 8, 11, 0, 0, 0, 0, 1, 0, 7 },
			{ 0, 0, 2, 0, 0, 0, 6, 7, 0 }
		};
		final Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.printShortestPaths(0);
	}
}