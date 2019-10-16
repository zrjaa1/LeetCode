package DP;

/*
64. Minimum Path Sum
Medium

1192

35

Favorite

Share
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */

/*
Thoughts: 典型DP,简单
 sol1: 一维dp
 sol2: in-place
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int[] dp = new int[grid[0].length];

        // initialize
        dp[0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            dp[i] = dp[i-1] + grid[0][i];
        }

        // build
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (j == 0) dp[j] += grid[i][j];
                else dp[j] = grid[i][j] + Math.min(dp[j], dp[j-1]);
            }
        }
        return dp[grid[0].length-1];
    }
}
