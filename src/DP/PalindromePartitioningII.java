package DP;

/**
 * 132. Palindrome Partitioning II: https://leetcode.com/problems/palindrome-partitioning-ii/
 * Hard
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Example 2:
 *
 * Input: s = "a"
 * Output: 0
 * Example 3:
 *
 * Input: s = "ab"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2000
 * s consists of lowercase English letters only.
 */
public class PalindromePartitioningII {
    /**
     * Sol1: dp[i] = s[i, len - 1]中最少有多少个palindromoe
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len + 1]; // dp[i]: s[i, len - 1] 中有多少个Palindrome. 一开始应该是两维，后来发现我们总是取其中一边，因此优化为一维
        boolean[][] isPalindrome = new boolean[len + 1][len + 1];

        for (int i = len - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE - 1;
            for (int j = i; j < len; j++) {
                if (i == j ||
                        (i == j - 1 && s.charAt(i) == s.charAt(j)) ||
                        (i < j - 1 && isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j))) { // branching only when [i, j] is a palindrome
                    isPalindrome[i][j] = true;
                    dp[i] = Math.min(dp[i], 1 + dp[j + 1]);
                }
            }
        }
        return dp[0] - 1;
    }

    /**
     * Sol2: dp[i] = s[0, i]中最少有多少个palindrome
     */
    public int minCut2(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len]; // dp[i]: s[0, i] 中有多少个Palindrome. 一开始应该是两维，后来发现我们总是取其中一边，因此优化为一维
        boolean[][] isPalindrome = new boolean[len + 1][len + 1];
        dp[0] = 1;

        for (int i = 1; i < len; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
            for (int j = i; j >= 0; j--) { // [j, i]
                if (i == j ||
                        (i == j + 1 && s.charAt(i) == s.charAt(j)) ||
                        (i > j + 1 && isPalindrome[i - 1][j + 1] && s.charAt(i) == s.charAt(j))) { // branching only when [i, j] is a palindrome
                    isPalindrome[i][j] = true;
                    int prev = j == 0 ? 0 : dp[j - 1];
                    dp[i] = Math.min(dp[i], 1 + prev);
                }
            }
        }
        return dp[len - 1] - 1;
    }
}
