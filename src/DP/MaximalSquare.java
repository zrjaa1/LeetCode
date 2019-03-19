package DP;

/*
221. Maximal Square
Medium

1096

26

Favorite

Share
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
 */

/*
Note: 1. matrix给的是char
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int res = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = (int)(matrix[i][0]-'0');
            if (dp[i][0] > res) res = dp[i][0];
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = (int)(matrix[0][i]-'0');
            if (dp[0][i] > res) res = dp[0][i];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (matrix[i][j] == '0') dp[i][j] = 0;
                else {
                    if (dp[i-1][j-1] == 0) dp[i][j] = 1;
                    else {
                        dp[i][j] = 1;
                        for (int k = 1; k <= dp[i-1][j-1]; k++) {
                            if (matrix[i][j-k] == '0' || matrix[i-k][j] == '0') break;
                            else dp[i][j] = k+1;
                        }
                        if (dp[i][j] > res) res = dp[i][j];
                    }
                }
            }
        }
        return res*res;
    }

    public static void main(String[] args) {
        char[][] test = {{'0','0','0','1'},{'1','1','0','1'},{'1','1','1','1'},{'0','1','1','1'},{'0','1','1','1'}};
        MaximalSquare tester = new MaximalSquare();
        tester.maximalSquare(test);
    }
}
