package Sort;

public class MergeSort{
	public void mergeSort(int[] nums) {
		if (nums == null || nums.length <= 1) return;
		divideAndMerge(nums, 0, nums.length-1);
	}

	private void divideAndMerge(int[] nums, int left, int right) {
		if (left == right) return;
		int mid = left + (right - left)/2;
		divideAndMerge(nums, left, mid);
		divideAndMerge(nums, mid+1, right);
		merge(nums, left, mid + 1, mid, right);
	}

	private void merge(int[] nums, int left, int right, int leftBound, int rightBound) {
		int[] helper = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			helper[i] = nums[i];
		}
		int cur = left;
		while (left <= leftBound && right <= rightBound) {
			if (helper[left] < helper[right]) {
				nums[cur] = helper[left];
				left++;
			} else {
				nums[cur] = helper[right];
				right++;
			}
			cur++;
		}
		if (right == rightBound+1) {
			while (left <= leftBound) {
				nums[cur] = helper[left];
				cur++;
				left++;
			}
		}
	}

	public static void main(String[] args) {
		MergeSort tester = new MergeSort();
		int[] nums = {6, 4, 7, 8, 9, 2, 5, 3};
		tester.mergeSort(nums);
		for (int i : nums) {
			System.out.print(i);
		}
	}
}
