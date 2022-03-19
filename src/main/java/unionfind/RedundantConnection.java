package unionfind;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The
 * added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is
 * represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai
 * and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers,
 * return the answer that occurs last in the input.
 *
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 *
 * Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 * Output: [1,4]
 *
 * Constraints:
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * There are no repeated edges.
 * The given graph is connected.
 */
public class RedundantConnection {

        private int[] parent;
        private int[] rank;

        private int find(int x) {
            final List<Integer> list = new LinkedList<>();
            while (parent[x] != x) {
                list.add(x);
                x = parent[x];
            }
            for (int ele : list) parent[ele] = x;
            return x;
        }

        private boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return false;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[py]++;
            }
            return true;
        }

        public int[] findRedundantConnection(int[][] edges) {
            final int n = edges.length + 1; // total nodes in this problem statement is same as edges, nodes start with 1
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
            for (final int[] edge : edges) {
                if (!union(edge[0], edge[1])) {
                   return edge;
                }
            }
            return null;
        }

    public static void main(String[] args) {
        final int[][] edges = {{1, 5}, {3, 4}, {3, 5}, {4, 5}, {2, 4}};
        // int[][] edges = {{1,2},{1,3},{2,3}};
        System.out.println(Arrays.toString(new RedundantConnection().findRedundantConnection(edges)));
    }

}
