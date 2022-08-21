public class BinarySearch {

	private static Integer floor(int[] arr, int ele) {
		int start = 0;
		int end = arr.length - 1;

		if (arr[0] > ele) return null;
		Integer ans = null;

		while (start <= end) {
			int mid = start + ((end-start) >> 1);
			if (arr[mid] <= ele) {
				ans = start;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return ans;
	}

	public static void main(String... args) {
		int[] arr1 = {1, 3, 5, 5, 7, 9, 9, 11};

		System.out.println(floor(arr1, 9));
		System.out.println(floor(arr1, 10));
		System.out.println(floor(arr1, 30));
		System.out.println(floor(arr1, 0));
	} 
}