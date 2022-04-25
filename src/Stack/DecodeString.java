package Stack;

import java.util.Stack;

/**
 * 394. Decode String: https://leetcode.com/problems/decode-string/
 * Medium
 *
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        strStack.push(new StringBuilder());
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                val = val * 10 + ch - '0';
            } else if (ch == '[') {
                numStack.push(val);
                strStack.push(new StringBuilder());
                val = 0;
            } else if (ch == ']') {
                StringBuilder content = strStack.pop();
                int count = numStack.pop();
                StringBuilder sb = new StringBuilder();
                while (count-- > 0) {
                    sb.append(content);
                }
                strStack.peek().append(sb);
            } else { // content
                StringBuilder content = new StringBuilder();
                content.append(ch);
                while (i + 1 < s.length() && s.charAt(i + 1) >= 'a' && s.charAt(i + 1) <= 'z') {
                    content.append(s.charAt(i + 1));
                    i++;
                }
                strStack.peek().append(content);
            }
        }

        return strStack.peek().toString();
    }
}
