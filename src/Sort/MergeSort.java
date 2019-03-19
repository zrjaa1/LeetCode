package Sort;
import java.util.*;

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

	private List<Integer> mergeII(List<Integer> left, List<Integer> right) {
		int index1 = 0;
		int index2 = 0;
		List<Integer> res = new ArrayList<>();
		while (index1 < left.size() && index2 < right.size()) {
			if (left.get(index1) < right.get(index2)) {
				res.add(left.get(index1));
				index1++;
			} else {
				res.add(right.get(index2));
				index2++;
			}
		}
		if (index1 == left.size()) {
			while (index2 < right.size()) {
				res.add(right.get(index2));
				index2++;
			}
		} else {
			while (index1 < left.size()) {
				res.add(left.get(index1));
				index1++;
			}
		}
		return res;
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
