package algo;

/**
 * 862. Shortest Subarray with Sum at Least K Given an integer array nums and an
 * integer k, return the length of the shortest non-empty subarray of nums with
 * a sum of at least k. If there is no such subarray, return -1.
 */
public class ShortestContSubArrayOfAtleastSum {

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int shortest = Integer.MAX_VALUE;

        // i is start pos
        for (int i = 0; i < n; i++) {
            int sumSoFar = 0;
            for (int j = i; j < n; j++) {
                sumSoFar = sumSoFar + nums[j];
                if (sumSoFar >= k) {
                    if (shortest > (j - i) + 1) {
                        shortest = (j - i) + 1;
                    }
                }

            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;

    }

    public static void main(String[] args) {
        int[] nums = { 2, -1, 2, 3 };
        int k = 1;
        System.out.println(
                new ShortestContSubArrayOfAtleastSum().shortestSubarray(nums,
                        k));
    }
}
