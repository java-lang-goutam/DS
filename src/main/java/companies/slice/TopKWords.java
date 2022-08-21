/**
 * Given a stream of strings, find the top 100 frequent words in the latest 1M words.
 **/

import java.util.*;

public class TopKWords {

	// supporting a-z, A-Z, 0-9
	private class Trie {
		Trie[] next = new Trie[36];
		int count;
		String word;
	}

	private class WordCount {
		int count;
		String word;

		WordCount(Trie trie) {
			this.word = trie.word;
			this.count = trie.count;
		}

		public String toString() {
			return word + " : " + count;
		}
	}

	private int getIndex(char ch) {
		if (ch >= 'A' && ch <= 'Z') return ch - 'A';
		else if (ch >= 'a' && ch <= 'z') return ch - 'a';
		else if (ch >= '0' && ch <= '9') return ch - '0' + 26;
		return -1;
	}

	private Trie buildTrie(String[] words) {
		final Trie root = new Trie();
		for (String word : words) {
			Trie currentRoot = root;
			for (int i=0, n = word.length(); i < n; i++) {
				final char currentChar = word.charAt(i);
				final int index = getIndex(currentChar);
				if (index < 0) continue;
				if (currentRoot.next[index] == null) currentRoot.next[index] = new Trie();
				currentRoot = currentRoot.next[index];
			}
			currentRoot.count++;
			if (currentRoot.word == null) currentRoot.word = word.replaceAll("[^a-zA-Z0-9]+", "");
		}

		return root;
	}


	public List<WordCount> find(String[] words, int k) {
		final Trie root = buildTrie(words);
		final Queue<Trie> queue = new LinkedList<>();
		final PriorityQueue<WordCount> minHeap = new PriorityQueue<>(Comparator.comparingInt(wc -> wc.count));

		// add all elements in priority queue using BFS
		queue.offer(root);

		while (!queue.isEmpty()) {
			Trie currNode =  queue.poll();
			if (currNode.word != null) {
				minHeap.offer(new WordCount(currNode));
				if (minHeap.size() > k) minHeap.poll();
				currNode.word = null;
			}

			for (int i=0, n = currNode.next.length; i < n; i++) {
				if (currNode.next[i] != null) queue.offer(currNode.next[i]);
			}
		}

		final LinkedList<WordCount> topWords = new LinkedList<>();
		while (!minHeap.isEmpty()) topWords.addFirst(minHeap.poll());

		return topWords;
	}


	public static void main(String... args) {
		final String str = "Welcome user123& user123/ user123+ user123! to the world of Geeks This portal has been created to provide well written well thought and well explained solutions for selected questions If you like Geeks for Geeks and would like to contribute here is your chance You can write article and mail your article to contribute at geeksforgeeks org See your article appearing on the Geeks for Geeks main page and help thousands of other Geeks";
		final String[] words = str.split("\\s+");
		new TopKWords().find(words, 5).stream().forEach(System.out::println);
	}
}