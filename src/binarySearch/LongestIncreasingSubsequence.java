package binarySearch;

import java.util.ArrayList;

// *Leetcode* #300
//Given an unsorted array of integers, find the length of longest increasing subsequence.
//
//Example:
//
//Input: [10,9,2,5,3,7,101,18]
//Output: 4 
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
//Note:
//
//There may be more than one LIS combination, it is only necessary for you to return the length.
//Your algorithm should run in O(n2) complexity.
//Follow up: Could you improve it to O(n log n) time complexity?

// *Thoughts*
//O(n^2), dp
/*
class LongestIncreasingSubsequence {
 public int lengthOfLIS(int[] nums) {
     if (nums == null || nums.length == 0) return 0;
     int maxSoFar = 0;
     int[] dp = new int[nums.length];
     dp[0] = 1;
     for (int i = 0; i < nums.length; i++) {
         for (int j = 0; j < i; j++) {
             if (nums[i] > nums[j]) {
                 maxSoFar = dp[j] > maxSoFar ? dp[j] : maxSoFar;
             }
         }
         dp[i] = maxSoFar+1;
         maxSoFar = 0;
     }
     
     int res = 0;
     for (int k = 0; k < nums.length; k++) {
         res = dp[k] > res ? dp[k] : res;
     }
     return res;
 }
}
*/

//O(nlogn) my solution 将O(n^2)改为O(nlogn)的关键在于，dp[i]的含义的转变：在O(n^2)解法中，dp[i]表示，以i为index的元素为结尾的longest ascending sequence的长度; 在O(nlog)解法中，array[i]表示长度为i的ascending sequence的最小可能结尾。这样的含义为我们使用binary search提供了可能，由此可将每次往回看的O（n）变为O（logn）。想通了这一点之后，重点就在于如何在一个array中找小于target的最大值，这实质上还是一个binary search问题。模板可参考最小面的解法中测ceilIndex()

class LongestIncreasingSubsequence {
 private int binarySearch(ArrayList<Integer> nums, int target) {
     if (nums == null || nums.size() == 0) return -1;
     if (nums.size() == 1 && nums.get(0) >= target) return 0;
     int left = 0;
     int right = nums.size()-1;
     int mid = 0;
     while (left+1 < right) {
         mid = left + (right-left)/2;
         if (nums.get(mid) == target) return mid;
         else if (nums.get(mid) > target) right = mid;
         else left = mid;
     }
     if (nums.get(left) >= target) return left;
     if (nums.get(left) < target && nums.get(right) > target) return right;
     if (nums.get(right) == target) return right;
     return -1;
 }
 
 public int lengthOfLIS(int[] nums) {
     if (nums == null || nums.length == 0) return 0;
     
     ArrayList<Integer> array = new ArrayList<Integer>();
     int pos = 0;
     for (int i = 0; i < nums.length; i++) {
         pos = binarySearch(array, nums[i]);
         if (pos < 0) {
             array.add(nums[i]);
         } else {
             array.set(pos, nums[i]);
         }
     }
     return array.size();
     
 }
 
 public static void main(String[] args) {
	 LongestIncreasingSubsequence test = new LongestIncreasingSubsequence();
	 int[] nums = {10,9,2,5,3,7,101,18};
	 int res = test.lengthOfLIS(nums);
	 System.out.println(res);
 }
}