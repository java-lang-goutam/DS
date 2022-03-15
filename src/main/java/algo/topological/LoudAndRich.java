package algo.topological;

import java.util.*;

/**
 * There is a group of n people labeled from 0 to n - 1 where each person has a different amount of money and a
 * different level of quietness.
 *
 * You are given an array richer where richer[i] = [ai, bi] indicates that ai has more money than bi and an integer
 * array quiet where quiet[i] is the quietness of the ith person. All the given data in richer are logically correct
 * (i.e., the data will not lead you to a situation where x is richer than y and y is richer than x at the same time).
 *
 * Return an integer array answer where answer[x] = y if y is the least quiet person (that is, the person y with the
 * smallest value of quiet[y]) among all people who definitely have equal to or more money than the person x.
 *
 *
 *
 * Example 1:
 *
 * Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0] Output: [5,5,2,5,4,5,6,7]
 * Explanation: answer[0] = 5. Person 5 has more money than 3, which has more money than 1, which has more money than 0.
 * The only person who is quieter (has lower quiet[x]) is person 7, but it is not clear if they have more money than
 * person 0. answer[7] = 7. Among all people that definitely have equal to or more money than person 7 (which could be
 * persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x]) is person 7. The other answers can be
 * filled out with similar reasoning.
 *
 * Example 2:
 *
 * Input: richer = [], quiet = [0] Output: [0]
 * Constraints:
 *
 * n == quiet.length
 * 1 <= n <= 500
 * 0 <= quiet[i] < n
 * All the values of quiet are unique.
 * 0 <= richer.length <= n * (n - 1) / 2
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs of richer are unique.
 * The observations in richer are all logically consistent.
 */
public class LoudAndRich {

    private final int MISSING = -1;

    public int[] loudAndRich(final int[][] richer, final int[] quiet) {

        final int n = quiet.length;
        final ArrayList<Integer>[] graph = new ArrayList[n];
        final int[] result = new int[n];

        // initialize
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            result[i] = MISSING;
        }

        // generate graph
        for (int[] pair : richer) {
            graph[pair[1]].add(pair[0]);
        }

        // call dfs for each node
        for (int i = 0; i < n; i++) {
            result[i] = dfs(graph, quiet, i, result);
        }

        return result;
    }

    private int dfs(final ArrayList<Integer>[] graph, final int[] quiet, final int node, int[] result) {

        if (result[node] == MISSING) {
            result[node] = node;
            for (final int rich : graph[node]) {
                final int richNode = dfs(graph, quiet, rich, result);
                if (quiet[richNode] < quiet[result[node]]) {
                    result[node] = richNode;
                }
            }
        }
        return result[node];
    }

    public static void main(String[] args) {

        final int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
        final int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
//        final int[] quiet = {0, 1};
//        final int[][] richer = {};
        System.out.println(Arrays.toString(new LoudAndRich().loudAndRich(richer, quiet)));
    }
}
