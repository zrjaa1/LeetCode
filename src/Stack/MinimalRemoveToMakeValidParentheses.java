package Stack;

/**
 * 1249. Minimum Remove to Make Valid Parentheses: https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * Medium
 *
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either'(' , ')', or lowercase English letter.
 */

import java.util.Stack;

/**
 * 1. Two pass, 1. remove extra right parentheses in 1st pass 2. remove the extra left parentheses in the 2nd pass.
 * 2. Always remove from the end will make sure it's a valid string. Think about the test case: ())()(((
 *    其实可以考虑从右边往左看，用1st pass同样的方法来remove )，只需要把（ 和 ）的地位调换即可
 * 3. Using a stack is not recommended solution here as the stack would mess up the content sequence, e.g., "lee(t(c)o)de)"
 */
public class MinimalRemoveToMakeValidParentheses {
    // Sol: Two pass, remove the extra left parentheses in the 2nd pass.
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int extraLeft = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                extraLeft++;
            } else if (ch == ')') {
                if (--extraLeft < 0) {
                    extraLeft = 0;
                    continue; // we skip the extra right parenthesis in the 1st pass.
                }
            }

            sb.append(ch);
        }

        // 2nd pass, always remove from the end will make sure it's a valid string. Think about the test case: ())()(((
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (extraLeft == 0) {
                break;
            }

            if (sb.charAt(i) == '(') {
                sb.deleteCharAt(i);
                extraLeft--;
            }
        }

        return sb.toString();
    }
}
