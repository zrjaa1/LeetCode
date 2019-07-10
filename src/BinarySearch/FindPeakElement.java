package BinarySearch;

// *Leetcode* #162
//A peak element is an element that is greater than its neighbors.
//
//Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
//
//The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
//You may imagine that nums[-1] = nums[n] = -∞.
//
//Example 1:
//
//Input: nums = [1,2,3,1]
//Output: 2
//Explanation: 3 is a peak element and your function should return the index number 2.
//Example 2:
//
//Input: nums = [1,2,1,3,5,6,4]
//Output: 1 or 5 
//Explanation: Your function can return either index number 1 where the peak element is 2, 
//             or index number 5 where the peak element is 6.
//Note:
//
//Your solution should be in logarithmic complexity.

// *Thoughts*
//Just try to find the max

class FindPeakElement {
 public int findPeak(int[] nums) {
     if (nums == null || nums.length == 0) return -1;
     if (nums.length ==1 || nums[0] > nums[1]) return 0;
     if (nums[nums.length-1] > nums[nums.length-2]) return nums.length-1;
     
     int left = 0;
     int right = nums.length - 1;
     int mid = 0;

     while (left < right - 1) {
         mid = left + (right-left)/2;
         if (nums[mid] > nums[mid-1]) left = mid;    // 每次往中间找，如果符合上升趋势，那么代表left需要朝那边靠
         else right = mid;                          
     }
     if (nums[left] >= nums[right]) {
         return left;
     } 
     return right;
 }
 
 public static void main(String[] args) {
	 FindPeakElement test = new FindPeakElement();
	 int[] nums = {1,2,3,1};
	 int res = test.findPeak(nums);
	 System.out.println(res);
 }
}