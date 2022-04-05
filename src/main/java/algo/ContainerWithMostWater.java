package algo;

/**
 * 11. Container With Most Water
 * Medium
 *
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of
 * the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of
 * water (blue section) the container can contain is 49.
 *
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int area = 0, max = 0;
        while (i < j) {
            if (height[i] <= height[j]) {
                area = height[i] * (j - i);
                i++;
            } else {
                area = height[j] * (j - i);
                j--;
            }
            if (area > max) max = area;
        }

        return max;
    }

    public static void main(String[] args) {
        final ContainerWithMostWater container = new ContainerWithMostWater();
        final int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(container.maxArea(height));
    }

}
