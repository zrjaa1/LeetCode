package BinarySearch;

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

//O(nlogn) my solution ��O(n^2)��ΪO(nlogn)�Ĺؼ����ڣ�dp[i]�ĺ����ת�䣺��O(n^2)�ⷨ�У�dp[i]��ʾ����iΪindex��Ԫ��Ϊ��β��longest ascending sequence�ĳ���; ��O(nlog)�ⷨ�У�array[i]��ʾ����Ϊi��ascending sequence����С���ܽ�β�������ĺ���Ϊ����ʹ��binary search�ṩ�˿��ܣ��ɴ˿ɽ�ÿ�����ؿ���O��n����ΪO��logn������ͨ����һ��֮���ص�����������һ��array����С��target�����ֵ����ʵ���ϻ���һ��binary search���⡣ģ��ɲο���С��Ľⷨ�в�ceilIndex()

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