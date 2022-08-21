/*
Validate 2d grid with sequences

0 0 1 1 0 1 -> [2, 1]

0 1 1 0 0 1 → [2, 1]

0 0 0 0 0 0 → [0]

0 1 0 1 0 1 → [1,1,1]

rows: [2,1], [2,1], [0], [1,1,1]
columns : [0], [1,1], [2], [1,1],[0],[2,1]

Explaination :
A group means sequence of consecutive 1s in either row or column

First row has 2 groups of size 2, 1
Second row has 2 groups of size 2, 1
Third row has 0 groups
Fourth row has 3 groups of size 1

Similary first column has 0 groups
Second column has 2 groups of size 1, 1
Third column has 1 group of size 2 and so on..

So this test case should return true.

Test case 2 :
0, 0 -> [1]
1, 0 -> [0]

rows -> [1], [0]
Columns -> [1], [0]
This test case should return false.
*/

import java.util.*;

public class ValidateGrid {

	public void validate(int[][] grid) {
		final int rows = grid.length;
		final int cols = grid[0].length;

		final List<List<Integer>> rowsCount = new ArrayList<>();

		for (int i=0; i<rows; i++) {
			final List<Integer> currCount = new ArrayList<>();
			for (int j=0; j<cols; j++) {
				int count = 0;
				while (j < cols && grid[i][j] == 1) {
					j++; count++;
				}
				if (count != 0) currCount.add(count);
			}
			rowsCount.add(currCount.size() == 0 ? Arrays.asList(0) : currCount);
		}

		System.out.println("rows :" + rowsCount);

		final List<List<Integer>> colsCount = new ArrayList<>();
		for (int j=0; j<cols; j++) {
			final List<Integer> currCount = new ArrayList<>();
			for (int i=0; i<rows; i++) {
				int count = 0;
				while (i < rows && grid[i][j] == 1) {
					i++; count++;
				}
				if (count != 0) currCount.add(count);
			}
			colsCount.add(currCount.size() == 0 ? Arrays.asList(0) : currCount);
		}

		System.out.println("cols :" + colsCount);

	}

	public static void main(String... args) {

		int[][] grid1 = {
			{0, 0, 1, 1, 0, 1},
			{0, 1, 1, 0, 0, 1},
			{0, 0, 0, 0, 0, 0},
			{0, 1, 0, 1, 0, 1}
		};

		int[][] grid2 = {{0, 0}, {1, 0}};

		ValidateGrid vg = new ValidateGrid();

		System.out.println("\nGrid 1 :");
		vg.validate(grid1);

		System.out.println("\nGrid 2 :");
		vg.validate(grid2);
	}
}