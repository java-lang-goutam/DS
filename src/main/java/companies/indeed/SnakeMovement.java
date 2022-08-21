/**
 *
 * We have a two-dimensional board game involving snakes. The board has two types of squares on it: +'s represent impassable squares where snakes cannot go, and 0's represent squares through which snakes can move.

Here is an example board:

col-->        0  1  2  3  4  5  6  7  8
           +---------------------------
row      0 |  +  +  +  +  +  +  +  0  0
 |       1 |  +  +  0  0  0  0  0  +  +
 |       2 |  0  0  0  0  0  +  +  0  +
 v       3 |  +  +  0  +  +  +  +  0  0
         4 |  +  +  0  0  0  0  0  0  +
         5 |  +  +  0  +  +  0  +  0  +

Find the rows and columns in which the snake can move free freely.

Sample inputs:
board1 =
{{'+', '+', '+', '0', '+', '0', '0'},
{'0', '0', '0', '0', '0', '0', '0'},
{'0', '0', '+', '0', '0', '0', '0'},
{'0', '0', '0', '0', '+', '0', '0'},
{'+', '+', '+', '0', '0', '0', '+'}}
Expected output = [[1], [3, 5]]

board2 =
{{'+', '+', '+', '0', '+', '0', '0'},
{'0', '0', '0', '0', '0', '+', '0'},
{'0', '0', '+', '0', '0', '0', '0'},
{'0', '0', '0', '0', '+', '0', '0'},
{'+', '+', '+', '0', '0', '0', '+'}}
Expected output = [[], [4]]

board3 =
{{'+', '+', '+', '0', '+', '0', '0'},
{'0', '0', '0', '0', '0', '0', '0'},
{'0', '0', '+', '+', '0', '+', '0'},
{'0', '0', '0', '0', '+', '0', '0'},
{'+', '+', '+', '0', '0', '0', '+'}}
Expected output = [[1], []]

board4 = {{'+'}}
Expected output = [[], []]




Question 2

We have a two-dimensional board game involving snakes. The board has two types of squares on it: +'s represent impassable squares where snakes cannot go, and 0's represent squares through which snakes can move.

Snakes may move in any of four directions - up, down, left, or right - one square at a time, but they will never return to a square that they've already visited. If a snake enters the board on an edge square, we want to catch it at a different exit square on the board's edge.

The snake is familiar with the board and will take the route to the nearest reachable exit, in terms of the number of squares it has to move through to get there. Note that there may not be a reachable exit.

Here is an example board:

col-->        0  1  2  3  4  5  6  7  8
           +---------------------------
row      0 |  +  +  +  +  +  +  +  0  0
 |       1 |  +  +  0  0  0  0  0  +  +
 |       2 |  0  0  0  0  0  +  +  0  +
 v       3 |  +  +  0  +  +  +  +  0  0
         4 |  +  +  0  0  0  0  0  0  +
         5 |  +  +  0  +  +  0  +  0  +

Write a function that takes a rectangular board with only +'s and O's, along with a starting point on the edge of the board, and returns the coordinates of the nearest exit to which it can travel. If there is a tie, return any of the nearest exits.

Sample inputs:
board1 = [['+', '+', '+', '+', '+', '+', '+', '0', '0'],
['+', '+', '0', '0', '0', '0', '0', '+', '+'],
['0', '0', '0', '0', '0', '+', '+', '0', '+'],
['+', '+', '0', '+', '+', '+', '+', '0', '0'],
['+', '+', '0', '0', '0', '0', '0', '0', '+'],
['+', '+', '0', '+', '+', '0', '+', '0', '+']]
start1_1 = (2, 0) # Expected output = (5, 2)
start1_2 = (0, 7) # Expected output = (0, 8)
start1_3 = (5, 2) # Expected output = (2, 0) or (5, 5)
start1_4 = (5, 5) # Expected output = (5, 7)

board2 = [['+', '+', '+', '+', '+', '+', '+'],
['0', '0', '0', '0', '+', '0', '+'],
['+', '0', '+', '0', '+', '0', '0'],
['+', '0', '0', '0', '+', '+', '+'],
['+', '+', '+', '+', '+', '+', '+']]
start2_1 = (1, 0) # Expected output = null (or a special value representing no possible exit)
start2_2 = (2, 6) # Expected output = null

board3 = [['+', '0', '+', '0', '+',],
['0', '0', '+', '0', '0',],
['+', '0', '+', '0', '+',],
['0', '0', '+', '0', '0',],
['+', '0', '+', '0', '+']]
start3_1 = (0, 1) # Expected output = (1, 0)
start3_2 = (4, 1) # Expected output = (3, 0)
start3_3 = (0, 3) # Expected output = (1, 4)
start3_4 = (4, 3) # Expected output = (3, 4)

board4 = [['+', '0', '+', '0', '+',],
['0', '0', '0', '0', '0',],
['+', '+', '+', '+', '+',],
['0', '0', '0', '0', '0',],
['+', '0', '+', '0', '+']]
start4_1 = (1, 0) # Expected output = (0, 1)
start4_2 = (1, 4) # Expected output = (0, 3)
start4_3 = (3, 0) # Expected output = (4, 1)
start4_4 = (3, 4) # Expected output = (4, 3)

board5 = [['+', '0', '0', '0', '+',],
['+', '0', '+', '0', '+',],
['+', '0', '0', '0', '+',],
['+', '0', '+', '0', '+']]
start5_1 = (0, 1) # Expected output = (0, 2)
start5_2 = (3, 1) # Expected output = (0, 1)

All test cases:
findExit(board1, start1_1) => (5, 2)
findExit(board1, start1_2) => (0, 8)
findExit(board1, start1_3) => (2, 0) or (5, 5)
findExit(board1, start1_4) => (5, 7)
findExit(board2, start2_1) => null (or a special value representing no possible exit)
findExit(board2, start2_2) => null
findExit(board3, start3_1) => (1, 0)
findExit(board3, start3_2) => (3, 0)
findExit(board3, start3_3) => (1, 4)
findExit(board3, start3_4) => (3, 4)
findExit(board4, start4_1) => (0, 1)
findExit(board4, start4_2) => (0, 3)
findExit(board4, start4_3) => (4, 1)
findExit(board4, start4_4) => (4, 3)
findExit(board5, start5_1) => (0, 2)
findExit(board5, start5_2) => (0, 1)

Complexity Analysis:

r: number of rows in the board
c: number of columns in the board
*/

import java.util.*;

class SnakeMovement {

	private List<List<Integer>> findFreeSquares(char[][] board) {

		// find rows
		final int rows = board.length;
		final int cols = board[0].length;

		final List<Integer> freeRows = new ArrayList<>();
		final List<Integer> freeCols = new ArrayList<>();

		outer : for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (board[r][c] == '+') {
					continue outer;
				}
			}

			freeRows.add(r);
		}

		outer : for (int c = 0; c < cols; c++) {
			for (int r = 0; r < rows; r++) {
				if (board[r][c] == '+') {
					continue outer;
				}
			}

			freeCols.add(c);
		}

		return Arrays.asList(freeRows, freeCols);
	}

	private int[] findNearestExit(char[][] board, int[] entry) {
		final int m = board.length;
		final int n = board[0].length;
		final boolean[][] visited = new boolean[m][n];
		final Queue<int[]> queue = new LinkedList<>();
		queue.offer(entry);

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];

			visited[r][c] = true;
			if (curr != entry && (r == 0 || r == m - 1 || c == 0 || c == n - 1)) return curr;

			if (c < n - 1 && !visited[r][c + 1] && board[r][c + 1] == '0') queue.offer(new int[] {r, c + 1});
			if (r < m - 1 && !visited[r + 1][c] && board[r + 1][c] == '0') queue.offer(new int[] {r + 1, c});
			if (c > 0 && !visited[r][c - 1] && board[r][c - 1] == '0') queue.offer(new int[] {r, c - 1});
			if (r > 0 && !visited[r - 1][c] && board[r - 1][c] == '0') queue.offer(new int[] {r - 1, c});
		}

		return null;
	}

	public static void main(String... args) {
		SnakeMovement sm = new SnakeMovement();

		// Solution 1
		char[][] board = {	{'+', '+', '+', '0', '+', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '+', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '+', '0', '0'},
			{'+', '+', '+', '0', '0', '0', '+'}
		};
		System.out.println(sm.findFreeSquares(board));

		// Solution 2
		char[][] board1 = {
			{'+', '+', '+', '+', '+', '+', '+', '0', '0'},
			{'+', '+', '0', '0', '0', '0', '0', '+', '+'},
			{'0', '0', '0', '0', '0', '+', '+', '0', '+'},
			{'+', '+', '0', '+', '+', '+', '+', '0', '0'},
			{'+', '+', '0', '0', '0', '0', '0', '0', '+'},
			{'+', '+', '0', '+', '+', '0', '+', '0', '+'}
		};
		System.out.println(Arrays.toString(sm.findNearestExit(board1, new int[] {2, 0})));
		System.out.println(Arrays.toString(sm.findNearestExit(board1, new int[] {0, 7})));
		System.out.println(Arrays.toString(sm.findNearestExit(board1, new int[] {5, 2})));
		System.out.println(Arrays.toString(sm.findNearestExit(board1, new int[] {5, 5})));

		char[][] board2 = {
			{'+', '+', '+', '+', '+', '+', '+'},
			{'0', '0', '0', '0', '+', '0', '+'},
			{'+', '0', '+', '0', '+', '0', '0'},
			{'+', '0', '0', '0', '+', '+', '+'},
			{'+', '+', '+', '+', '+', '+', '+'}
		};

		System.out.println(Arrays.toString(sm.findNearestExit(board2, new int[] {1, 0})));
		System.out.println(Arrays.toString(sm.findNearestExit(board2, new int[] {2, 6})));
	}
}