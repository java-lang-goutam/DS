package algo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 1296. Divide Array in Sets of K Consecutive Numbers Medium Given an array of
 * integers nums and a positive integer k, check whether it is possible to
 * divide this array into sets of k consecutive numbers. Return true if it is
 * possible. Otherwise, return false. Example 1: Input: nums =
 * [1,2,3,3,4,4,5,6], k = 4 Output: true Explanation: Array can be divided into
 * [1,2,3,4] and [3,4,5,6]. Example 2: Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11],
 * k = 3 Output: true Explanation: Array can be divided into [1,2,3] , [2,3,4] ,
 * [3,4,5] and [9,10,11]. Example 3: Input: nums = [1,2,3,4], k = 3 Output:
 * false Explanation: Each array should be divided in subarrays of size 3.
 * Constraints: 1 <= k <= nums.length <= 105 1 <= nums[i] <= 109
 */
public class DivideArraySetsKConsecutive {
    /*public boolean isPossibleDivide(int[] nums, int k) {
        final int n = nums.length;
        if (n % k != 0)
            return false;

        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(nums[i]);
        }

        while (!pq.isEmpty()) {
            final int curr = pq.poll();
            for (int i = 1; i < k; i++) {
                if (!pq.remove(curr + i)) {
                    return false;
                }
            }
        }

        return true;
    }*/

    public boolean isPossibleDivide(int[] nums, int k) {
        final int n = nums.length;
        if (n % k != 0)
            return false;

        final Map<Integer, Integer> c = new TreeMap<>();
        for (int i : nums) {
            c.put(i, c.getOrDefault(i, 0) + 1);
        }

        for (final Integer val : c.keySet()) {
            if (c.get(val) > 0) {
                for (int i=k-1; i>=0;i--) {
                    if (c.getOrDefault(val + i, 0) < c.get(val)) {
                        return false;
                    }
                    c.put(val + i, c.get(val + i) - c.get(val));
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int nums[] = { 6, 1, 2, 3, 3, 4, 4, 5 };
        int k = 4;
        final DivideArraySetsKConsecutive obj = new DivideArraySetsKConsecutive();
        System.out.println(obj.isPossibleDivide(nums, k));
    }

}
