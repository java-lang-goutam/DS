package algo.topological;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        if (n < 2) {
            return IntStream.of(0, n).boxed().collect(Collectors.toList());
        }

        final List<Set<Integer>> neighboursList = new ArrayList<>();

        // Step 1: Build graph
        IntStream.range(0, n).forEach(x -> neighboursList.add(new HashSet<>()));

        for (int[] edge : edges) {
            final int start = edge[0];
            final int end = edge[1];
            neighboursList.get(start).add(end);
            neighboursList.get(end).add(start);
        }

        // find all leaf nodes, leaf nodes have size 1
        List<Integer> leaves = IntStream.range(0, n)
                .filter(i -> neighboursList.get(i).size() == 1)
                .boxed()
                .collect(Collectors.toList());

        int remainingNodes = n;

        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            final List<Integer> newLeaves = new ArrayList<>();
            for (final Integer leaf : leaves) {
                // since leaf has only one neighbour
                final Integer neighbour = neighboursList.get(leaf).iterator().next();

                // remove from neighbour's list
                neighboursList.get(neighbour).remove(leaf);
                if (neighboursList.get(neighbour).size() == 1) {
                    newLeaves.add(neighbour);
                }
            }
            leaves = newLeaves;
        }

        return leaves;
    }

    public static void main(String[] args) {

        //int n = 6, edges[][] = {{3,0}, {3,1},{3,2},{3,4},{5,4}};
        int n = 4, edges[][] = {{1, 0}, {1, 2}, {1, 3}};
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(n, edges));
        System.out.println( IntStream.range(0, n).boxed().collect(Collectors.toList()));
    }
}
