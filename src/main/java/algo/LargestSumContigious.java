package algo;

/**
 * {-2, -3, 4, -1, -2, 1, 5, -3}
 */
public class LargestSumContigious {

    private static int find(int... arr) {

        int maxEndingHere = 0;
        int maxSoFar = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {

            maxEndingHere = arr[i] + maxEndingHere;
            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
            }

            if (maxEndingHere < 0) maxEndingHere = 0;
        }

        return maxSoFar;
    }

    public static void main(String[] args) {

        System.out.println(find(-2, -3, 4, -1, -2, 1, 5, -3));

    }

}
