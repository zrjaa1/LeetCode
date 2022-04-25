package Sort;
import Util.*;

// Time Complexity: worst O(n^2), average O(nlogn) -> probability, or multiple pivots

public class QuickSort {
	public void quickSort(int[] array) {
		if (array == null || array.length <= 1) return;
		int left = 0;
		int right = array.length-1;
		quickSort0(array, left, right);
	}

	// sol0: STANDARD, use array[end] as pivot, and left as baffle(挡板)
	public void quickSort0(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}

		int left = start - 1;
		int pivot = array[end];
		for (int i = start; i < end; i++) {
			if (array[i] < pivot) {
				Util.swapIntArray(array, i, ++left);
			}
		}
		Util.swapIntArray(array, end, ++left);
		quickSort0(array, start, left - 1);
		quickSort0(array, left + 1, end);
	}

	// sol1: left right go to mid
	public void quickSort1(int[] array, int left, int right) {
		if (left >= right) return;

		// chose right as pivot directly
		int pivot = right--;

		// in the end, left > right
		while (left <= right) {
			if (array[left] < array[pivot]) left++;
			else if (array[right] >= array[pivot]) right--;
			else Util.swapIntArray(array, left++, right--);
		}

		// swap with left, since array[left] > array[pivot]
		Util.swapIntArray(array, left, pivot);
		quickSort1(array, 0, left-1);
		quickSort1(array, left+1, pivot);
	}

	// sol2: slow fast go to the end -> for loop
	public void quickSort2(int[] array, int left, int right) {
		if (left >= right) return;
		int slow = left;
		int pivot = left + (right-left)/2;

		Util.swapIntArray(array, pivot, right);
		pivot = right;
		right--;

		for (int fast = left; fast <= right; fast++) {
			if (array[fast] < array[pivot]) {
				Util.swapIntArray(array, slow, fast);
				slow++;
			}
		}

		Util.swapIntArray(array, slow, pivot);
		quickSort2(array, left, slow-1);
		quickSort2(array, slow+1, pivot);
	}

	public static void main(String[] args) {
		QuickSort test = new QuickSort();
		int[] array = new int[] {9,6,6,5,6,6,6};
		test.quickSort(array);
		for (int i : array) {
			System.out.print(i);
			System.out.print(',');
		}
	}
}

