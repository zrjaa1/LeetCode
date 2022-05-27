package DP;

import java.util.Arrays;

/**
 * 828. Count Unique Characters of All Substrings of a Given String: https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
 * Hard
 *
 * Let's define a function countUniqueChars(s) that returns the number of unique characters on s.
 *
 * For example, calling countUniqueChars(s) if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
 * Given a string s, return the sum of countUniqueChars(t) where t is a substring of s.
 *
 * Notice that some substrings can be repeated so in this case you have to count the repeated ones too.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Every substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * Example 2:
 *
 * Input: s = "ABA"
 * Output: 8
 * Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
 * Example 3:
 *
 * Input: s = "LEETCODE"
 * Output: 92
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of uppercase English letters only.
 */
public class CountUniqueCharactersOfSubstrings {
    public int uniqueLetterString(String s) {
        int res = 0;
        if (s == null || s.length() == 0)
            return res;
        int[] showLastPosition = new int[26];
        Arrays.fill(showLastPosition, -1);
        int[] contribution = new int[26];
        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            // cur[i] = cur[i - 1] - contribution[x] + newContribution[x], where newContribution depends on the index difference
            cur -= contribution[x - 'A'];
            contribution[x - 'A'] = i - showLastPosition[x - 'A'];
            cur += contribution[x - 'A'];
            showLastPosition[x - 'A'] = i;
            res += cur;
        }
        return res;
    }

    public static void main(String[] args) {
        CountUniqueCharactersOfSubstrings tester = new CountUniqueCharactersOfSubstrings();
        String s = "ABC";
        int res = tester.uniqueLetterString(s);
        System.out.println(res);
    }
}
