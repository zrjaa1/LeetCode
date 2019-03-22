package Sort;
import Util.*;

// Time Complexity: worst O(n^2), average O(nlogn) -> probability, or multiple pivots

public class QuickSort {
	public void quickSort(int[] array) {
		if (array == null || array.length <= 1) return;
		int left = 0;
		int right = array.length-1;
		helper(array, left, right);
	}
	
	// sol1: left right go to mid
	
	public void helper(int[] array, int left, int right) {
		if (left >= right) return; 
		int pivot = left + (right-left)/2;
		
		Util.swapIntArray(array, pivot, right);
		pivot = right;
		right--;
		
		// in the end, left > right
		while (left <= right) {
			if (array[left] < array[pivot]) left++;
			else if (array[right] >= array[pivot]) right--;
			else Util.swapIntArray(array, left++, right--);
		}
		
		// swap with left, since array[left] > array[pivot]
		Util.swapIntArray(array, left, pivot);
		helper(array, 0, left-1);
		helper(array, left+1, pivot);
	}
	
	
	// sol2: slow fast go to the end -> for loop
	/*
	public void helper(int[] array, int left, int right) {
		if (left >= right) return;
		int slow = left;
		int pivot = left + (right-left)/2;
		
		Util.swap(array, pivot, right);
		pivot = right;
		right--;
		
		for (int fast = left; fast <= right; fast++) {
			if (array[fast] < array[pivot]) {
				Util.swap(array, slow, fast);
				slow++;
			}
		}
		
		Util.swap(array, slow, pivot);
		helper(array, left, slow-1);
		helper(array, slow+1, pivot);
	}
	*/
	
	public static void main(String[] args) {
		QuickSort test = new QuickSort();
		int[] array = new int[] {3,3,3,3,4,3,3,3,3};
		test.quickSort(array);
		for (int i : array) {
			System.out.print(i);
			System.out.print(',');
		}
	}
}

