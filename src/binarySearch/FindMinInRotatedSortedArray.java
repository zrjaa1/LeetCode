package binarySearch;

//*Leetcode* #153
//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
//
//Find the minimum element.
//
//You may assume no duplicate exists in the array.
//
//Example 1:
//
//Input: [3,4,5,1,2] 
//Output: 1
//Example 2:
//
//Input: [4,5,6,7,0,1,2]
//Output: 0

//*Thoughts*

class FindMinInRotatedSortedArray {
 public int findMin(int[] nums) {
     if (nums == null || nums.length == 0) throw new IllegalArgumentException();
     if (nums.length == 1) return nums[0];
     
     int left = 0;
     int right = nums.length-1;
     int mid = 0;
     while (left < right) {
         mid = left+(right-left)/2;
         if (nums[mid] > nums[mid+1]) return nums[mid+1]; 
         else if (nums[left] < nums[mid]) left = mid+1;
         else right = mid;
     }
     return nums[0];
 }
 
 public static void main(String[] args) {
 	FindMinInRotatedSortedArray test = new FindMinInRotatedSortedArray();
 	int[] nums = {3,4,5,1,2};
 	int res = test.findMin(nums);
 	System.out.println(res);
 }
}