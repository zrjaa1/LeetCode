package DP;

/**
 * 474. Ones and Zeroes: https://leetcode.com/problems/ones-and-zeroes/
 * Medium
 *
 * You are given an array of binary strings strs and two integers m and n.
 *
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 *
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 * Example 2:
 *
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] consists only of digits '0' and '1'.
 * 1 <= m, n <= 100
 */

/**
 * Learning:
 * 1. 在思考dfs -> dp的过程中，visited/availability不考虑为一个状态转移变量，因为dp的属性自带了memory
 * 2. 为什么在dp填值时从大到小？-> 防止在当层更新时用到早些时候在同层被更新的数值。例如：dp[10][4]可能用到dp[9][3]而dp[9][3]需要是上一层的值。
 *    在逻辑上，这表示同一个String被重复用了两次。在大多数dp问题中，我们没有涉及到过这个问题，但在这个问题的填值过程中，需要考虑到这一点。
 * 3. 为什么需要里面的两层循环？-> 因为一个新的词出现会影响很多个dp的填值，不要陷入定式思维
 */
public class OnesAndZeros {
    // Solution: dp[i][j] means the maximum # of subsets with at most i 0s and j 1s
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int[] count = count0and1(strs[i]);
            for (int zeros = m; zeros >= count[0]; zeros--) {
                for (int ones = n; ones >= count[1]; ones--) {
                    dp[zeros][ones] = Math.max(1 + dp[zeros - count[0]][ones - count[1]], dp[zeros][ones]);
                }
            }
        }

        return dp[m][n];
    }

    // DFS solution, Exceeds Time Limit
    int max = 0;
    private void dfs(int[][] strs, int idx, boolean[] used, int count, int m, int n) {
        int[] str = strs[idx];
        if (used[idx]) {
            return;
        }
        if (str[0] > m || str[1] > n) {
            return;
        }

        used[idx] = true;
        count++;
        max = Math.max(max, count);

        for (int i = 0; i < strs.length; i++) {
            dfs(strs, i, used, count, m - str[0], n - str[1]);
        }

        used[idx] = false;
        count--;
    }

    // res[0] indicates number of 0s
    private int[] count0and1(String str) {
        int[] res = new int[2];
        for (char ch : str.toCharArray()) {
            if (ch == '0') {
                res[0]++;
            } else if (ch == '1') {
                res[1]++;
            }
        }

        return res;
    }
}
