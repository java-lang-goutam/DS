package search.binary;

public class BitonicArray {

    // 5 6 7 8 9 3 2 1
    public static int getMaxElement(int[] arr, int low, int high) {

        if (low == high) {
            return arr[low];
        }
        if (low < high) {

            int mid = low + (high-low)/2;
            if (arr[mid] > arr[mid-1] && arr[mid]> arr[mid+1])
                return arr[mid];
            if (arr[mid] > arr[mid+1] && arr[mid] < arr[mid-1])
                return getMaxElement(arr, low, mid -1);
            if (arr[mid] < arr[mid+1] && arr[mid] > arr[mid-1])
                return getMaxElement(arr, mid +1, high);
        }

        return -1;

    }

    public static void main(String[] args) {
        int[] arr = {5,6,7,8,9,13,112,1111};
        System.out.println(getMaxElement(arr,  0, arr.length -1));
    }
}
