package search.binary;

/**
 * Given an array of n distinct integers sorted in ascending order, write a
 * function that returns a Fixed Point in the array, if there is any Fixed Point
 * present in array, else returns -1. Fixed Point in an array is an index i such
 * that arr[i] is equal to i. Note that integers in array can be negative.
 *
 * Input: arr[] = {-10, -5, 0, 3, 7}
 *   Output: 3  // arr[3] == 3
 */
public class FixedPoint {

    public int find(int arr[], int startIndex, int endIndex) {
        if (startIndex <= endIndex) {
            int mid = startIndex + (endIndex - startIndex) / 2;
            if (arr[mid] == mid) {
                return mid;
            }
            if (mid > arr[mid]) {
                return find(arr, mid + 1, endIndex);
            }
            return find(arr, startIndex, mid - 1);
        }
        return -1;
    }

    public static void main(String[] args) {

        FixedPoint fp = new FixedPoint();
        int arr[] = {-10, -5, 0, 3, 7};
        System.out.println(fp.find(arr, 0, arr.length - 1));

        int arr2[] = {0, 2, 5, 8, 17};
        System.out.println(fp.find(arr2, 0, arr.length - 1));
    }

}
