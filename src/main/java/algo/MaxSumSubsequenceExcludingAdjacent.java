package algo;

public class MaxSumSubsequenceExcludingAdjacent {

    public static int find(int... arr) {
        //return find_recursive(arr, 0);
        return find_dp(arr, arr.length);
    }

    public static int find_dp(int[] arr, int n) {

        // dp[i] : max sum sequence ending at i
        int dp[] = new int[n];

        // initialize value
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {

            // including i
            int incl = dp[i - 2] + arr[i];

            // excluding i
            int excl = dp[i - 1];

            dp[i] = Math.max(incl, excl);

        }

        return dp[n - 1];
    }

    public static int find_recursive(int arr[], int i) {

        if (i >= arr.length)
            return 0;

        // including i
        int incl = arr[i] + find_recursive(arr, i + 2);

        // excluding i
        int excl = find_recursive(arr, i + 1);

        return Math.max(incl, excl);
    }

    public static void main(String[] args) {
        System.out.println(find(6, 7, 1, 3, 8, 2, 4));
        System.out.println(find(5, 3, 4, 11, 2));
    }
}
