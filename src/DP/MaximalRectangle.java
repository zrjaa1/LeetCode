package DP;
import java.util.*;

/*
85. Maximal Rectangle
Hard

1263

45

Favorite

Share
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
 */

/*
利用LargestRectangleHistogram, 我们可以初始化一个向上看有多少个连续'1'的 dp[i][j]，
然后将每一行(dp[i])放入函数largestRectangleHistogram, 统计最大值
O（n^2)
 */

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = m-1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') dp[i][j] = 0;
                else if (i == m-1) dp[i][j] = 1;
                else dp[i][j] = dp[i+1][j] + 1;
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            res = Math.max(res, largestRectangleArea(dp[i]));
        }
        return res;
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        // store index
        Stack<Integer> s = new Stack<>();
        int res = 0;
        s.push(-1);
        for (int i = 0; i < n; i++) {
            // keep pushing until local max is meet
            if (i == 0 || heights[i] > heights[i-1]) s.push(i);
            else {
                while (s.peek() != -1 && heights[s.peek()] > heights[i]) {
                    int left = s.pop();
                    int area = heights[left] * (i-s.peek()-1);
                    res = Math.max(area, res);
                }
                s.push(i);
            }
        }
        while (s.peek() != -1) {
            int right = s.pop();
            int area = heights[right] * (n-1-s.peek());
            res = Math.max(area,res);
        }
        return res;
    }

}
