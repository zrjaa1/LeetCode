package SlidingWindow;

/**
 * 424. Longest Repeating Character Replacement: https://leetcode.com/problems/longest-repeating-character-replacement/
 * Medium
 *
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */

/**
 * We can choose to use a maxChar to record the most frequent characters, and dynamically update it
 * Clarify: the encoding method
 */
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.length() <= k) {
            return s.length();
        }

        char maxChar = '\0';
        int[] freq = new int[26];
        int res = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);

            // update max char
            if (maxChar == '\0' || ch == maxChar) {
                maxChar = ch;
                freq[maxChar - 'A']++;
            } else { // ch != maxChar
                freq[ch - 'A']++;
                for (int i = 0; i < 26; i++) { // attempt to update max char
                    if (freq[i] > freq[maxChar - 'A']) {
                        maxChar = (char)(i + 'A');
                    }
                }
            }

            // if we cannot change it to a valid string within k operations, move left
            while (right - left + 1 - freq[maxChar - 'A'] > k) {
                char chRemoved = s.charAt(left++);
                freq[chRemoved - 'A']--;
                if (chRemoved == maxChar) {
                    for (int i = 0; i < 26; i++) { // attempt to update max char
                        if (freq[i] > freq[maxChar - 'A']) {
                            maxChar = (char)(i + 'A');
                        }
                    }
                }
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
