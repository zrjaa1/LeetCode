package binarySearch;

//Leetcode #33: Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

//(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
//
//You are given a target value to search. If found in the array return its index, otherwise return -1.
//
//You may assume no duplicate exists in the array.
//
//Your algorithm's runtime complexity must be in the order of O(log n).
//
//Example 1:
//
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
//Example 2:
//
//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1


// Thoughts: 
// 一定只有一边是无序的，左边有序，看是否在左边范围内，如果在的话，就在左边范围里搜，这时候target一定不会出现在右边，因为左边的一定整体比右边大。如果左边无序，那么就看右边，是否在右边范围内，如果在的话，那一定在右边，因为右边一定整体比左边小。

class SearchInRotatedSortedArray {
 public int search(int[] nums, int target) {
     if (nums == null || nums.length == 0) return -1;
     int left = 0;
     int right = nums.length - 1;
     int mid = 0;
     while (left < right - 1) {
         mid = left + (right - left) / 2;
         if (nums[mid] == target) return mid;
         else {
             if (nums[left] < nums[mid]) {
                 if (nums[left] <= target && target < nums[mid]) right = mid;
                 else left = mid;
             } else {
                 if (nums[mid] < target && target <= nums[right]) left = mid;
                 else right = mid;
             }
         }
     }
     if (nums[left] == target) return left;
     if (nums[right] == target) return right;
     return -1;
 }
 
 	public static void main(String[] args) {
 		SearchInRotatedSortedArray test = new SearchInRotatedSortedArray();
 		int[] nums = {4,5,6,7,0,1,2};
 		int target = 0;
 		int res = test.search(nums, target);
 		System.out.println(res);
 	}
}