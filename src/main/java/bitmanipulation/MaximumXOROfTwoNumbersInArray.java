package main.java.bitmanipulation;

import java.util.HashSet;
import java.util.Set;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 * Medium
 *
 * 4062
 *
 * 330
 *
 * Add to List
 *
 * Share
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 * Example 2:
 *
 * Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * Output: 127
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 105
 * 0 <= nums[i] <= 231 - 1
 */
public class MaximumXOROfTwoNumbersInArray {

    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);

            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }

            System.out.println(set);
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        final MaximumXOROfTwoNumbersInArray findMax = new MaximumXOROfTwoNumbersInArray();
        final int ans = findMax.findMaximumXOR(new int[] {3,10,5,25,2,8});
        System.out.println("ans = " + ans);
    }
}
