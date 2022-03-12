package algo.topological;


import java.util.*;
import java.util.stream.*;

public class CourseSchedule {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        final Map<Integer, Integer> degree = new HashMap<>();
        final Map<Integer, Set<Integer>> dependencies = new HashMap<>();
        final Set<Integer> order = new LinkedHashSet<>();

        // make dependencies graph
        Stream.of(prerequisites).forEach(ele -> {
            int dependent = ele[0];
            int dependency = ele[1];

            degree.compute(dependency, (k,v) -> v == null ? 0 : v);
            degree.compute(dependent, (k,v) -> v == null ? 1 : v + 1);
            dependencies.computeIfAbsent(dependency, (k) -> new HashSet<>()).add(dependent);
        });

        // add all leaf elements
        final Queue<Integer> queue = degree.keySet().stream()
                .filter(key -> degree.get(key) == 0)
                .collect(Collectors.toCollection(LinkedList::new));

        // remove all leaf element one by one
        while (!queue.isEmpty()) {
            final int ele = queue.poll(); // remove item from queue
            order.add(ele);

            // remove element from map and reduce all dependent degrees
            if (dependencies.containsKey(ele)) {
                for (final int dependent : dependencies.remove(ele)) {
                    final int newDegree = degree.merge(dependent, -1, Integer::sum);
                    if (newDegree == 0) {
                        queue.offer(dependent); // if new leaf add to queue
                    }
                }
            }
        }

        // add remaining elements for which order is unknown
        if (dependencies.isEmpty()) {
            final List<Integer> unknownElements = IntStream.range(0, numCourses)
                    .boxed()
                    .filter(ele -> !order.contains(ele))
                    .collect(Collectors.toList());

            order.addAll(unknownElements);
        } else {
            // there exists element with more than degree 0
            return new int[0];
        }

        return order.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        final CourseSchedule courseSchedule = new CourseSchedule();
        final int numCourses = 3;
        final int[][] prerequisites = {{1,0},{1,2},{0,1}};
        System.out.println(Arrays.toString(courseSchedule.findOrder(numCourses, prerequisites)));
    }

}
