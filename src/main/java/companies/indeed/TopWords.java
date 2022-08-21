/**
I recently gave first round karat interview for indeed and here is the question. I was never able to find this question anywhere so thought of sharing.

Given a string S containing (word,count) pair , write a function which accepts this string and returns the top words and its count sorted in descending order limited by the number of words. If the words does not meet a minumum word length then you shoud not put them in the output.


String s = new String {"abc, 500",
"sadhasjhkgdsak, 230239203",
"fsgdfssd, 78",
"sss, 56",
"ss, 56",
"sss, 5678
"sssdsds, 56",
"ssssdsd, 56", }

getTopWords(String [] s, int wordLimit, minwordLength) -> (s, 3, 3)

output
 "sadhasjhkgdsak, 230239203"
 "sss, 5678
 "abc, 500"

*/


import java.util.*;

class TopWords {

	public List<String> find(String[] s, int wordLimit, int minWordLength) {
		final PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(str -> Integer.parseInt(str.split(",\\s+")[1])));

		for (String wc : s) {
			if (wc.split(",\\s+")[0].length() >= minWordLength) pq.offer(wc);
			if (pq.size() > wordLimit) pq.poll();
		}

		final LinkedList<String> res = new LinkedList<>();
		while (!pq.isEmpty()) res.addFirst(pq.poll());

		return res;
	}

	public static void main(String... args) {
		final String[] s = {"abc, 500",
                           "sadhasjhkgdsak, 230239203",
                           "fsgdfssd, 78",
                           "sss, 56",
                           "ss, 56",
                           "sss, 5678",
                           "sssdsds, 56",
                           "ssssdsd, 56",
                          };
        final TopWords tw = new TopWords();
        tw.find(s, 3, 3).forEach(System.out::println);
	}
}