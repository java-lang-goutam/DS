/**
While your players are waiting for a game, you've developed a solitaire game for the players to pass the time with.
The player is given an NxM board of tiles from 0 to 9 like this:
4 4 4 4
5 5 5 4
2 5 7 5
The player selects one of these tiles, and that tile will disappear, along with any tiles with the same number that are connected with that tile (up, down, left, or right), and any tiles with the same number connected with those, and so on. For example, if the 4 in the upper left corner is selected, these five tiles disappear

    4 4 4 4<
    5 5 5 >4<
    2 5 7 5
    If the 5 just below it is selected, these four tiles disappear. Note that tiles are not connected diagonally.
    4 4 4 4
    5 5 5< 4
    2 >5< 7 5
    Write a function that, given a grid of tiles and a selected row and column of a tile, returns how many tiles will disappear.
    grid1 = [[4, 4, 4, 4],
    [5, 5, 5, 4],
    [2, 5, 7, 5]]
    disappear(grid1, 0, 0) => 5
    disappear(grid1, 1, 1) => 4
    disappear(grid1, 1, 0) => 4
    This is the grid from above.

Additional Inputs
grid2 = [[0, 3, 3, 3, 3, 3, 3],
[0, 1, 1, 1, 1, 1, 3],
[0, 2, 2, 0, 2, 1, 4],
[0, 1, 2, 2, 2, 1, 3],
[0, 1, 1, 1, 1, 1, 3],
[0, 0, 0, 0, 0, 0, 0]]

grid3 = [[0]]

grid4 = [[1, 1, 1],
[1, 1, 1],
[1, 1, 1]]

All Test Cases
disappear(grid1, 0, 0) => 5
disappear(grid1, 1, 1) => 4
disappear(grid1, 1, 0) => 4
disappear(grid2, 0, 0) => 12
disappear(grid2, 3, 0) => 12
disappear(grid2, 1, 1) => 13
disappear(grid2, 2, 2) => 6
disappear(grid2, 0, 3) => 7
disappear(grid3, 0, 0) => 1
disappear(grid4, 0, 0) => 9

N - Width of the grid
M - Height of the grid
*/

import java.util.*;

public class SolitareGame {

	private String hash(int row, int col) {
		return row + ", " + col;
	}

	public int disapperCount(int[][] grid, int row, int col) {
		final int totalRows = grid.length;
		final int totalCols = grid[0].length;
		final Set<String> visited = new HashSet<>(); 
		return dfs(grid, visited, row, col);
	}

	private int dfs(int[][] grid, Set<String> visited, int row, int col) {
		int res = 1;
		visited.add(hash(row, col));

		// left
		if (col > 0 && grid[row][col-1] == grid[row][col] && !visited.contains(hash(row, col-1))) {
			res += dfs(grid, visited, row, col-1);
		}

		// right
		if (col < grid[0].length-1 && grid[row][col+1] == grid[row][col] && !visited.contains(hash(row, col+1))) {
			res += dfs(grid, visited, row, col+1);
		}

		// up
		if (row > 0 && grid[row-1][col] == grid[row][col] && !visited.contains(hash(row-1, col))) {
			res += dfs(grid, visited, row-1, col);
		}

		// down
		if (row < grid.length-1 && grid[row+1][col] == grid[row][col] && !visited.contains(hash(row+1, col))) {
			res += dfs(grid, visited, row+1, col);
		}

		return res;
	}

	public static void main(String... args) {
		int[][] grid1 = {{4, 4, 4, 4},
						 {5, 5, 5, 4},
						 {2, 5, 7, 5}};
		int[][] grid2 = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};
		SolitareGame game = new SolitareGame();
		System.out.println(game.disapperCount(grid1, 0, 0));
		System.out.println(game.disapperCount(grid1, 1, 1));
		System.out.println(game.disapperCount(grid1, 1, 0));
		System.out.println(game.disapperCount(grid2, 0, 0));
	}
} 




