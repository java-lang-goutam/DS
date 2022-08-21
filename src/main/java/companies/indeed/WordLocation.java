/**
 * After catching your classroom students cheating before, you realize your students are getting craftier and hiding words in 2D grids of letters. The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates. If there are multiple matches, return any one.

grid1 = {
{'c', 'c', 't', 'n', 'a', 'x'},
{'c', 'c', 'a', 't', 'n', 't'},
{'a', 'c', 'n', 'n', 't', 't'},
{'t', 'n', 'i', 'i', 'p', 'p'},
{'a', 'o', 'o', 'o', 'a', 'a'},
{'s', 'a', 'a', 'a', 'o', 'o'},
{'k', 'a', 'i', 'o', 'k', 'i'},
]
word1 = "catnip"
word2 = "cccc"
word3 = "s"
word4 = "ant"
word5 = "aoi"
word6 = "ki"
word7 = "aaoo"
word8 = "ooo"

grid2 = {{'a']]
word9 = "a"

find_word_location(grid1, word1) => { (1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
find_word_location(grid1, word2) =>
{(0, 0), (1, 0), (1, 1), (2, 1)]
OR {(0, 0), (0, 1), (1, 1), (2, 1)]
find_word_location(grid1, word3) => {(5, 0)]
find_word_location(grid1, word4) => {(0, 4), (1, 4), (2, 4)] OR {(0, 4), (1, 4), (1, 5)]
find_word_location(grid1, word5) => {(4, 5), (5, 5), (6, 5)]
find_word_location(grid1, word6) => {(6, 4), (6, 5)]
find_word_location(grid1, word7) => {(5, 2), (5, 3), (5, 4), (5, 5)]
find_word_location(grid1, word8) => {(4, 1), (4, 2), (4, 3)]
find_word_location(grid2, word9) => {(0, 0)]
 * 
 * */

import java.util.*;

public class WordLocation {

    private class Trie {
        Trie[] next = new Trie[26];
        String word;
    }

    public void findWords(char[][] board, String[] words) {
        Trie root = new Trie();
        buildTrie(words, root);

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int i=0; i < rows; i++) {
            for (int j=0; j<cols; j++) {
                Set<String> trace = new LinkedHashSet<>();
                backtrack(i, j, root, board, trace, visited, rows, cols, "");
            }
        }
    }

    private void backtrack(int r, int c, Trie node, char[][] board, Set<String> trace, boolean[][] visited, int m, int n, String soFar) {
        if (visited[r][c]) return;

        char currChar = board[r][c];
        node = node.next[currChar - 'a'];

        if (node == null) return;
        String coordinate = String.format("(%d, %d)", r, c);

        trace.add(coordinate);
        soFar += currChar;

        if (node.word != null) {
            node.word = null;
            System.out.println(soFar + " : " + trace);
        }

        visited[r][c] = true;

        if (c < n-1) backtrack(r, c+1, node, board, trace, visited, m , n , soFar);
        if (r < m-1) backtrack(r+1, c, node, board, trace, visited, m , n , soFar);
        //if (c > 0) backtrack(r, c-1, node, board, trace, visited, m , n , soFar);  // if left is desired
        //if (r > 0) backtrack(r-1, c, node, board, trace, visited, m , n , soFar);  // if top is desired

        visited[r][c] = false;
        trace.remove(coordinate);
    }

    private void buildTrie(String[] words, Trie root) {
        for (String word : words) {
            Trie curr = root;
            for (int i=0, n = word.length(); i < n ; i++) {
                if (curr.next[word.charAt(i) - 'a'] == null) curr.next[word.charAt(i) - 'a'] = new Trie();
                curr = curr.next[word.charAt(i) - 'a'];
            }
            curr.word = word;
        }
    }

	public static void main(String... args) {
		final char[][] grid = {
				{'c', 'c', 't', 'n', 'a', 'x'},
				{'c', 'c', 'a', 't', 'n', 't'},
				{'a', 'c', 'n', 'n', 't', 't'},
				{'t', 'n', 'i', 'i', 'p', 'p'},
				{'a', 'o', 'o', 'o', 'a', 'a'},
				{'s', 'a', 'a', 'a', 'o', 'o'},
				{'k', 'a', 'i', 'o', 'k', 'i'},
				};

		final WordLocation wl = new WordLocation();
		wl.findWords(grid, new String[]{"catnip", "cccc", "ant", "aoi", "ki", "aaoo", "ooo"});
	}
}