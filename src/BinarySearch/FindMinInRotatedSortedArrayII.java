package BinarySearch;

// *Leetcode* #154
//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
//
//Find the minimum element.
//
//The array may contain duplicates.
//
//Example 1:
//
//Input: [1,3,5]
//Output: 1
//Example 2:
//
//Input: [2,2,2,0,1]
//Output: 0
//Note:
//
//This is a follow up problem to Find Minimum in Rotated Sorted Array.
//Would allow duplicates affect the run-time complexity? How and why?

// *Thoughts*
//这道题必须得用right和mid相比较，因为left == mid 但中间无序的情况，只可能出现在左边。因此，当mid == right的时候，我们可以断定向左走。
//类似题：L81 Search in Rotated Sorted Array II

class FindMinInRotatedSortedArrayII {
 public int findMin(int[] nums) {
     if (nums == null || nums.length == 0) throw new IllegalArgumentException();
     if (nums.length == 1) return nums[0];
     
     int left = 0;
     int right = nums.length-1;
     int mid = 0;
     while(left < right) {
         mid = left + (right-left)/2;
         if (nums[mid] > nums[mid+1]) return nums[mid+1];
         if (nums[left] == nums[mid] && nums[right] == nums[mid]) { ++left; --right; }
         else if (nums[mid] > nums[right]) left = mid+1;
         else right = mid;
     }
     return nums[left];
 }
 public static void main(String[] args) {
	 FindMinInRotatedSortedArrayII test = new FindMinInRotatedSortedArrayII();
	 int[] nums = {1,3,5};
	 int res = test.findMin(nums);
	 System.out.println(res);
 }
}
