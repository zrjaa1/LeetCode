package HeapGraphHashMap;

import java.util.Comparator;
import java.util.PriorityQueue;

import javafx.util.converter.NumberStringConverter;

// *LeetCode* #215
// *Thoughts*
// MinHeap - O(nlogk)

// sol1: MinHeap, size k
/*
public class KthLargestElement {
	public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) return 0;
        
        PriorityQueue minHeap = new PriorityQueue<Integer>(k, customizedComparator);
        for (int i = 0; i < k; i++) 
            minHeap.offer(nums[i]);
        
        for (int j = k; j < nums.length; j++) {
            if (nums[j] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[j]);
            }
        }
        return minHeap.peek();
    }
    
	public static Comparator<Integer> customizedComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer num1, Integer num2) {
			return num1 - num2;
		}
	};
}
*/
// sol2: MaxHeap, size n
// sol3: quick Sort + prune

public class KthLargestElement {
	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length < k) return 0;
		int left = 0;
		int right = nums.length-1;
		return helper(nums, left, right, k);
	}
	
	// sol1: left right go to mid
	
	public int helper(int[] array, int left, int right, int targetIndex) {
		if (left >= right) return array[left]; 
		int pivot = left + (right-left)/2;
		int curLength = right - left + 1;
		
		swap(array, pivot, right);
		pivot = right;
		right--;
		
		// in the end, left > right
		while (left <= right) {
			if (array[left] < array[pivot]) left++;
			else if (array[right] >= array[pivot]) right--;
			else swap(array, left++, right--);
		}
		
		// swap with left, since array[left] > array[pivot]
        swap(array, left, pivot);
        if (left == curLength - targetIndex) return array[left];
		else if (left < curLength - targetIndex) return helper(array, left+1, pivot, targetIndex - (left+1));
        else return helper(array, 0, left-1, targetIndex- (pivot-left+1));
	}
    
    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
    
    public static void main(String[] args) {
    	KthLargestElement test = new KthLargestElement();
    	int[] nums = new int[] {3,2,1,5,6,4};
    	int target = 2;
    	int result = test.findKthLargest(nums, target);
    	System.out.println(result);
    }
}
