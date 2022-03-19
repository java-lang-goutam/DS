package algo;

import java.util.HashMap;
import java.util.Map;

/**
 * 1124. Longest Well-Performing Interval
 * Medium
 *
 * We are given hours, a list of the number of hours worked per day for a given employee.
 *
 * A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 *
 * A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the
 * number of non-tiring days.
 *
 * Return the length of the longest well-performing interval.
 *
 *
 * Example 1:
 *
 * Input: hours = [9,9,6,0,6,6,9]
 * Output: 3
 * Explanation: The longest well-performing interval is [9,9,6].
 *
 * Example 2:
 *
 * Input: hours = [6,6,6]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= hours.length <= 104
 * 0 <= hours[i] <= 16
 */
public class LongestWellPerformingInterval {
    public int longestWPI(int[] hours) {
        final Map<Integer, Integer> map = new HashMap<>();

        final int[] hash = new int[hours.length];

        int max = 0, val = 0;
        for (int i = 0; i < hours.length; i++) {
            val += hours[i] > 8 ? 1 : -1;

            if (val > 0)  {
                max = i + 1;
                continue;
            }

            if (map.containsKey(val)) {
                max = Math.max(max, i - map.get(val - 1));
            } else {
                map.put(val, i);
            }
        }
        return max;
    }
}
