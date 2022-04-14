package Sort;

/**
 * 215. Kth Largest Element in an Array: https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Medium
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 */
public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    // return the Kth largest element in nums[start, end], relatively
    private int findKthLargest(int[] nums, int start, int end, int k) {
        // start > end would never happen, see code below for details
        if (start == end) {
            return nums[start];
        }

        int idxP = partition(nums, start, end);
        int rank = idxP - start + 1;
        if (rank == k) {
            return nums[rank + start - 1];
        } else if (rank > k) { // go left
            return findKthLargest(nums, start, idxP - 1, k);
        } else { // go right
            return findKthLargest(nums, idxP + 1, end, k - rank);
        }
    }

    // return the index of pivot in nums[start, end], relatively
    // 3 areas: [start, left] < pivot, [left + 1, i - 1] >= pivot, [i, end] un-processed
    private int partition(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        }

        int pivot = nums[end];
        int left = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i] > pivot) {
                swap(nums, i, ++left);
            }
        }

        swap(nums, end, ++left);
        return left;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        KthLargestElementInAnArray tester = new KthLargestElementInAnArray();
        int[] nums = {0, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(tester.findKthLargest(nums, k));
    }
}
