package algo.topological;

import java.util.*;

public class AlienDictionary {

    public List<Character> getOrder(final String[] words) {
        final List<Character> order = new ArrayList<>();
        final Map<Character, Integer> degree = new HashMap<>();
        final Map<Character, Set<Character>> smallerToLargerMap = new HashMap<>();

        // compare alternate words
        for(int i=0; i<words.length - 1; i++) {
            final String currentWord = words[i];
            final String nextWord = words[i+1];

            // if both words are same
            if (currentWord.equals(nextWord)) continue;

            // find first mismatch
            int j=0;
            for (; j<currentWord.length() && j < nextWord.length(); j++) {
                if (currentWord.charAt(j) != nextWord.charAt(j)) break;
            }

            if (j == currentWord.length()) continue;

            // we have two characters and their order
            degree.compute(nextWord.charAt(j), (k,v) -> (v == null) ? 1 : v + 1);
            smallerToLargerMap.computeIfAbsent(currentWord.charAt(j), (k)-> new HashSet<>()).add(nextWord.charAt(j));
        }

        // Topological sort
        final char firstChar = words[0].charAt(0);
        final Queue<Character> queue = new LinkedList<>();
        queue.offer(firstChar);
        degree.put(firstChar, 0);

        while(!queue.isEmpty()) {
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

        return order;
    }

    public static void main(String[] args) {
        final AlienDictionary dic = new AlienDictionary();
        final String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(dic.getOrder(words));
    }
}
