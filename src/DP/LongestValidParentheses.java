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

/**
 * 0. dp定义，dp[i] = [i, len - 1]以i为开头的valid parentheses的长度，如果i为 ) ，那么应该为0
 * 1. 两种形式的valid，一种是嵌套式，一种是连续式。这两种都可以通 j = i + dp[i + 1] + 1 来跳过直接找到需要匹配的位置。
 * 2. 当前层与其他层的概念。例如 ())()中前2个括号属于当前层，而最后的（）属于下一层。在计算时，dp值分别为2， 2. 而当我们往前看，多看到一个（ 时，前4个都属于当前层了，而后两个属于下一层，dp值应为6， 2
 * 所以在考虑dp的推演公式时，既要累加当前层，也要累加下一层。
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int len = s.length(), max = 0;
        int dp[] = new int[len + 1]; // [i, len - 1] 以i为开头的valid parentheses的长度
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                dp[i] = 0;
            } else if (s.charAt(i) == '(') {
                int j = i + dp[i + 1] + 1;
                if (j < len && s.charAt(j) == ')') { // 跳到当前层里，需要找 ) 来match的index
                    dp[i] = dp[i + 1] + 2; // 计算 1. 当前层valid parentheses的个数
                    if (j + 1 < len) {
                        dp[i] += dp[j + 1]; // 累加上 2. 其他层valid parentheses的个数，例如: ((()))()中最后一个()，需要靠这里累加
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
