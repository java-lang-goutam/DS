package dp;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * <p>
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move
 * outside the boundary (i.e., wrap-around is not allowed).
 * <p>
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 */
public class LongestIncreasingPathInMatrix {

    public int longestIncreasingPath(int[][] matrix) {

        if (matrix == null && matrix.length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int longestPath = 0;
        int[][] mem = new int[m][n];

        // find shortest path starting from all index
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                longestPath = Math.max(longestPath, dfs(matrix, mem, m, n, i, j));
            }
        }

        return longestPath;
    }

    private int dfs(final int[][] matrix, int[][] mem, final int m, final int n, final int i, final int j) {

        if (mem[i][j] != 0) return mem[i][j];
        int longestPath = 0;
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // left right up down
        for (int[] dir : directions) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && y >= 0 && x < m && y < n && matrix[x][y] > matrix[i][j]) {
                longestPath = Math.max(longestPath, dfs(matrix, mem, m, n, x, y));
            }
        }
        mem[i][j] = 1 + longestPath;
        return mem[i][j];
    }

    public static void main(String[] args) {
        final int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(new LongestIncreasingPathInMatrix().longestIncreasingPath(matrix));
    }
}
