package algo.slidingwindow;

/**
 * Given an unsorted array of nonnegative integers, find a continuous subarray
 * which adds to a given number.
 */
public class SubArrayWithSum {

    // 1, 4, 20, 3, 10, 5 --> k : 33
    public static boolean findLength(int[] arr, int k) {
        int n = arr.length;

        // sum = 1
        int start = 0, end = 0, sum = arr[0];

        while (end < n) {

            while (sum > k) {
                sum -= arr[start];
                start ++;
            }

            if (sum == k) {
                return true;
            }

            // end = 1
            end ++;

            // sum = 5
            if (end < n) {
                sum += arr[end];
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int arr[] = { 15, 2, 4, 8, 9, 5, 10, 23 };
        int sum = 23;
        System.out.println(findLength(arr, sum));
    }

}
