package String;

import java.util.ArrayList;
import java.util.List;

/**
 * 443. String Compression: https://leetcode.com/problems/string-compression/
 * Medium
 *
 * Given an array of characters chars, compress it using the following algorithm:
 *
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 *
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 *
 * After you are done modifying the input array, return the new length of the array.
 *
 * You must write an algorithm that uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 * Example 2:
 *
 * Input: chars = ["a"]
 * Output: Return 1, and the first character of the input array should be: ["a"]
 * Explanation: The only group is "a", which remains uncompressed since it's a single character.
 * Example 3:
 *
 * Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
 *
 *
 * Constraints:
 *
 * 1 <= chars.length <= 2000
 * chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 */

/**
 * 细节的考察，写码能力的直接体现
 */
public class StringCompression {
    public int compress(char[] chars) {
        int j = 0;
        int count = 0;
        int prev = '\0';
        for (int i = 0; i < chars.length; i++) {
            if (prev != chars[i]) { // new character, append and set prev and count
                prev = chars[i]; // 先设置prev，否则char[i]可能被in place替换掉
                if (count != 1) { // append freq
                    j = updateFreq(chars, j, count);
                    count = 1; // new count
                }
                chars[j++] = chars[i];
            } else { // the same characters
                count++;
            }
        }
        if (count != 1) {
            j = updateFreq(chars, j, count); // for the last group
        }
        return j;
    }

    private int updateFreq(char[] chars, int j, int count) {
        if (count == 0) {
            return j;
        }
        List<Integer> digits = new ArrayList<>();
        while (count / 10 > 0) {
            digits.add(count%10);
            count /= 10;
        }
        digits.add(count);
        for (int i = 0; i < digits.size(); i++) {
            chars[j++] = (char) ('0' + digits.get(digits.size() - 1 - i));
        }
        return j;
    }
}
