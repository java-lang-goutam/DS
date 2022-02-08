package dp;

import java.util.*;

public class NoOfLIS {

    public static int findLISCount(int... arr) {
        if (arr == null)
            return 0;
        if (arr.length == 1)
            return 1;

        int n = arr.length;

        // we would need two arrays
        // lis[i] : LIS ending at i
        int[] lis = new int[n];

        // count[i] : Count of LIS ending at i
        int counts[] = new int[n];

        int longest = 1;

        // fill default values
        Arrays.fill(lis, 1);
        Arrays.fill(counts, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {

                    // check if sequence is new and increasing
                    if (lis[i] < lis[j] + 1) {

                        // Add new increasing sequence
                        lis[i] = lis[j] + 1;
                        // counts of LIS will be same
                        counts[i] = counts[j];
                    }

                    // check if another sequence of same length, add counts value
                    else if (lis[i] == lis[j] + 1) {
                        counts[i] += counts[j];
                    }
                }
            }

            longest = Math.max(longest, lis[i]);
        }

        System.out.println(Arrays.toString(lis));
        System.out.println(Arrays.toString(counts));

        // check and add all longest counts to find total counts
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (longest == lis[i]) {
                res += counts[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(findLISCount(1, 2, 4, 3, 5, 4, 7, 2));
    }
}
