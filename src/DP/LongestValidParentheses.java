package DP;

/**
 * 32. Longest Valid Parentheses: https://leetcode.com/problems/longest-valid-parentheses/
 * Hard
 *
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int len = s.length(), max = 0;
        int dp[] = new int[len + 1];
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                dp[i] = 0;
            } else if (s.charAt(i) == '(') {
                int j = i + dp[i + 1] + 1;
                if (j < len && s.charAt(j) == ')') {
                    dp[i] = dp[i + 1] + 2; // 计算当前层valid parentheses的个数
                    if (j + 1 < len) {
                        dp[i] += dp[j + 1]; // 累加上其他层valid parentheses的个数，例如: ((()))()中最后一个()，需要靠这里累加
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses tester = new LongestValidParentheses();
        String input = "(()";
        int res = tester.longestValidParentheses(input);
        System.out.println(res);
    }
}
