package DP;
import java.util.*;
/*
560. Subarray Sum Equals K
Medium

1724

43

Favorite

Share
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
 */

/*
1. sol1: dp[i] -> [0, i)sum
         subarry sum [i,j) = dp[j]-dp[i]
         O(n^2)求出所有等于k的[i, j)组合, space O(n)
2. HashMap + sum，
         用sum表示[0,i)的sum，
         用HashMap存[0, 1）, [0,2), [0,3) ... [0, i-1)的sum， value代表出现过几次
         subarray = sum - [0, x), x不一定 -> if map.contains (sum - k), k是subarray的理想值，那么就代表存在这样的subarray使其中的和为k
         Time & Space: O(n)
 */

public class SubarraySumEqualsK {
    // sol1
    /*
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1] + nums[i-1];
            for (int j = 0; j < i; j++) {
                if (dp[i]-dp[j] == k) res++;
            }
        }
        return res;
    }
    */

    // sol2:
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int res = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(sum-k)) res += map.get(sum-k);
            if (map.containsKey(sum)) map.put(sum, 1);
            else map.put(sum, map.get(sum)+1);
        }
        return res;
    }

    public  static void main(String[] args) {
        SubarraySumEqualsK tester = new SubarraySumEqualsK();
        int[] nums = {1,1,1};
        tester.subarraySum(nums, 2);
    }
}
