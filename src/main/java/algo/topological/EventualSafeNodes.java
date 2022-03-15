package algo.topological;

import java.util.*;
import java.util.stream.*;

/**
 * There is a directed graph of n nodes with each node labeled from 0 to n - 1.
 * The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes
 * adjacent to node i, meaning there is an edge from node i to each node in graph[i].
 * <p>
 * A node is a terminal node if there are no outgoing edges. A node is a safe node if every
 * possible path starting from that node leads to a terminal node.
 * <p>
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 *
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: The given graph is shown above.
 * Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
 * Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
 *
 */
public class EventualSafeNodes {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        final Set<Integer> safeNodes = new HashSet<>(graph.length);
        final Set<Integer> unSafeNodes = new HashSet<>(graph.length);
        final Set<Integer> visited = new HashSet<>(graph.length);
        final List<Integer> result = new ArrayList<>(graph.length);

        // a safe node has all path to terminal node as safe
        for (int i=0; i< graph.length; i++) {
            if (isSafeNode(i, graph, safeNodes, unSafeNodes, visited)) {
                result.add(i);
            };
        }

        return result;
    }

    private boolean isSafeNode(int node, int[][] graph, Set<Integer> safeNodes, Set<Integer> unSafeNodes, Set<Integer> visited) {
        if (safeNodes.contains(node)) return true;
        if (unSafeNodes.contains(node) || visited.contains(node)) return false;

        visited.add(node);
        boolean isSafe = true;

        for (final int neighbour : graph[node]) {
            if (!isSafeNode(neighbour, graph, safeNodes, unSafeNodes, visited)) {
                isSafe = false;
                unSafeNodes.add(neighbour);
                break;
            }
        }

        visited.remove(node);
        if (isSafe) {
            safeNodes.add(node);
        } else {
            unSafeNodes.add(node);
        }

        return isSafe;
    }

    public static void main(String[] args) {
        final EventualSafeNodes safeNodes = new EventualSafeNodes();
        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        // int[][] graph = {{1,2,3,4},{1,2},{3,4},{0,4},{}};
        System.out.println(safeNodes.eventualSafeNodes(graph));
    }
}
