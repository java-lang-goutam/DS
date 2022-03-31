package algo;

/**
 * 74. Search a 2D Matrix
 * Medium
 *
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the
 * following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 */
public class SearchIn2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (target < matrix[0][0]) return false;
        int low = 0, high = matrix.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid][0] == target) return true;
            if (target < matrix[mid][0]) high = mid - 1;
            else low = mid + 1;
        }
        return binarySearch(matrix[Math.max(high, 0)], target);
    }

    private boolean binarySearch(final int[] matrix, final int target) {
        int low = 0, high = matrix.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid] == target) return true;
            if (target < matrix[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }

    public static void main(String[] args) {

        final int matrix[][] = {{1}}, target = 2;
        final SearchIn2DMatrix search = new SearchIn2DMatrix();
        System.out.println(search.searchMatrix(matrix, target));
    }
}
