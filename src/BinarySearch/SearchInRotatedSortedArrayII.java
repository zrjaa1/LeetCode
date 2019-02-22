package BinarySearch;

// *Leetcode* #81
//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
//
//You are given a target value to search. If found in the array return true, otherwise return false.
//
//Example 1:
//
//Input: nums = [2,5,6,0,0,1,2], target = 0
//Output: true
//Example 2:
//
//Input: nums = [2,5,6,0,0,1,2], target = 3
//Output: false
//Follow up:
//
//This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
//Would this affect the run-time complexity? How and why?

// *Thoughts*
//笔记可以看L33，这道题是follow up，多加了一个duplicate的可能性, 这样的话， 就存在了乐left == mid, 但target出现在中间的情况。这时候想到用recursion，两边都找
/*
class Solution {
  public boolean search(int[] nums, int target) {
      if (nums == null || nums.length == 0) return false;
      int left = 0;
      int right = nums.length - 1;
      int mid = 0;
      while (left < right - 1) {
          mid = left + (right - left) / 2;
          if (nums[mid] == target) return true;
          else {
              if (nums[left] < nums[mid]) {
                  if (nums[left] <= target && target < nums[mid]) right = mid;
                  else left = mid;
              } else if (nums[left] > nums[mid]){
                  if (nums[mid] < target && target <= nums[right]) left = mid;
                  else right = mid;
              } else {
                  return search(Arrays.copyOfRange(nums, 0, mid+1), target) || search(Arrays.copyOfRange(nums, mid+1, right+1), target);
              }
          }
      }
      if (nums[left] == target || nums[right] == target) return true;
      return false;
  }
}
*/

//best solution online: when left == mid, it's possible that the left part is out of order, e.g. [3 1 2 3 3 3 3]), in this case, it's guaranteed that right == mid == left, so we can use left++, right-- to reduce. (don't understand why, but if you write out all possibilities, you will find out that it's a must. ). 
//if left == mid and it's not out of order, which means every element in left part is equal. We can handle this as the normal case.
//Time complexity: average O(logn), worst O(n)
//Space complexity: O(1)
class SearchInRotatedSortedArrayII {
  public boolean search(int[] nums, int target) {
      if (nums == null || nums.length == 0) return false;
      int left = 0;
      int right = nums.length - 1;
      int mid = 0;
      while (left < right - 1) {
          mid = left + (right - left) / 2;
          if (nums[mid] == target) return true;
          else {
              if (nums[left] == nums[mid] && nums[right] == nums[mid]) {
                  left++;
                  right--;
              } else if (nums[left] <= nums[mid]) {
                  if (nums[left] <= target && target < nums[mid]) right = mid;
                  else left = mid;
              } else {
                  if (nums[mid] < target && target <= nums[right]) left = mid;
                  else right = mid;
              } 
          }
      }
      if (nums[left] == target || nums[right] == target) return true;
      return false;
  }
  
  public static void main(String[] args) {
	  SearchInRotatedSortedArrayII test = new SearchInRotatedSortedArrayII();
	  int[] nums = {2,5,6,0,0,1,2};
	  int target = 0;
	  boolean res = test.search(nums, target);
	  System.out.println(res);
  }
}
