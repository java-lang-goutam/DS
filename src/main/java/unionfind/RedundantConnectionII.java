package unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 685. Redundant Connection II
 * Hard
 *
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all
 * other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has
 * no parents.
 *
 * The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n),
 * with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an
 * edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a
 * directed edge connecting nodes ui and vi, where ui is a parent of child vi.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple
 * answers, return the answer that occurs last in the given 2D-array.
 */
public class RedundantConnectionII {

    int[] parent;
    int[] rank;

    private int find(int x) {
        final List<Integer> list = new ArrayList<>();
        while (x != parent[x]) {
            list.add(x);
            x = parent[x];
        }
        for (final Integer ele : list) parent[ele] = x;
        return x;
    }

    private boolean union(final int x, final int y) {
        final int px = find(x);
        final int py = find(y);
        if (px == py) return false;

        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[py] < rank[px]) {
            parent[py] = px;
        } else {
            rank[py]++;
            parent[px] = py;
        }

        return true;
    }

    public int[] findRedundantDirectedConnection(final int[][] edges) {
        final int[] indegree = new int[edges.length + 1];

        // find node with two parents
        int nodeWithTwoParents = -1;
        for (final int[] edge : edges) {
            indegree[edge[1]]++;
            if (indegree[edge[1]] == 2) {
                // node with two parents
                nodeWithTwoParents = edge[1];
                break;
            }
        }

        // case 1 : There aren't any node with two parents
        if (nodeWithTwoParents == -1) {
            // find cycle
            return findRedundantConnection(edges, null);
        }

        // case 2 : If there exist node with 2 parent check for cycle
        boolean alreadyChecked = false;
        // we need to find last edge to remove
        for (int i= edges.length - 1; i >= 0; i-- ) {
            if (edges[i][1] == nodeWithTwoParents) {

                if (alreadyChecked) return edges[i];

                final int[] redundantEdge = findRedundantConnection(edges, edges[i]);
                if (redundantEdge == null) {
                    // no cycle found
                    return edges[i];
                } else {
                    alreadyChecked = true;
                }
            }
        }

        return null;
    }

    private int[] findRedundantConnection(final int[][] edges, final int[] edgeToIgnore) {
        final int n = edges.length + 1;
        parent = new int[n];
        rank = new int[n];

        for (int i = 1; i < n; i++) {
            parent[i] = i;
        }

        for (final int[] edge : edges) {
            if (edge == edgeToIgnore) continue;
            if (!union(edge[0], edge[1])) return edge;
        }
        return null;
    }

    public static void main(String[] args) {
        // final int[][] edges = {{1, 5}, {3, 4}, {3, 5}, {4, 5}, {2, 4}};
        int[][] edges = {{1,2},{1,3},{2,3}};
        // int[][] edges = {{2, 1}, {3, 1}, {4, 2}, {1, 4}};
        System.out.println(Arrays.toString(new RedundantConnectionII().findRedundantDirectedConnection(edges)));
    }
}
