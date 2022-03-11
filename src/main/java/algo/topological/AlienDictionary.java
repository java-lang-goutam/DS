package algo.topological;

import java.util.*;
import java.util.stream.Collectors;

public class AlienDictionary {

    public List<Character> getOrder(final String[] words) {
        final List<Character> order = new ArrayList<>();
        final Map<Character, Integer> degree = new HashMap<>();
        final Map<Character, Set<Character>> smallerToLargerMap = new HashMap<>();

        // compare alternate words
        for (int i = 0; i < words.length - 1; i++) {
            final String currentWord = words[i];
            final String nextWord = words[i + 1];

            // if both words are same
            if (currentWord.equals(nextWord)) continue;

            // find first mismatch
            int j = 0;
            for (; j < currentWord.length() && j < nextWord.length(); j++) {
                if (currentWord.charAt(j) != nextWord.charAt(j)) break;
            }

            if (j == currentWord.length() || j == nextWord.length()) continue;

            // we have two characters and their order
            degree.compute(nextWord.charAt(j), (k, v) -> (v == null) ? 1 : v + 1);
            degree.compute(currentWord.charAt(j), (k, v) -> (v == null) ? 0 : v);
            smallerToLargerMap.computeIfAbsent(currentWord.charAt(j), (k) -> new HashSet<>()).add(nextWord.charAt(j));
        }

        final Queue<Character> queue = degree.keySet().stream()
                .filter(key -> degree.get(key) == 0)
                .collect(Collectors.toCollection(LinkedList::new));

        // Topological sort
        while (!queue.isEmpty()) {
            final char ele = queue.poll();
            if (degree.get(ele) == 0) {
                order.add(ele);
            }
            if (smallerToLargerMap.containsKey(ele)) {
                for (final char ch : smallerToLargerMap.remove(ele)) {
                    final int newVal = degree.merge(ch, -1, Integer::sum);
                    if (newVal == 0) queue.add(ch);
                }
            }
        }

        if (order.isEmpty() && words.length > 0) {
            order.add(words[0].charAt(0));
        }

        return order;
    }

    public static void main(String[] args) {
        final AlienDictionary dic = new AlienDictionary();
        final String[] words = {"a", "aaa"};
        System.out.println(dic.getOrder(words));
    }
}
