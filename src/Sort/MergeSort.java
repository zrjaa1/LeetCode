package Sort;

public class MergeSort{
	private int[] helper;
	
	public MergeSort(int[] array) {
		if (array == null || array.length == 0) return;
		for (int i=0; i < array.length; i++) 
			helper[i] = array[i];
	}
	
	public void mergeSort(int[] array, int left, int right) {
		if (array == null || array.length <= 1) return;
			if (left == right) return;
			int mid = left + (right-left)/2;
			// divide
		mergeSort(array, left, mid);
		mergeSort(array, mid+1, right);
		// merge
		int cur = left;
		right = mid+1;
		while (left <= mid && right <= array.length-1) {
			if (array[left] < array[right]) {
				helper[cur] = array[left];
				left++;
			} else {
				helper[cur] = array[right];
				right++;
			}
				cur++;
		}
			// post processing
		if (right == array.length-1) {
			while (left <= mid && cur <= array.length-1) {
				helper[cur] = array[left];
				left++;
				cur++;
			}
		}
		
		// keep the array is synchronized with helper
		for (int i = left; i <= right; i++) {
			array[i] = helper[i];
		}
	}
}
