package DP;

/*
44. Wildcard Matching
Hard

870

63

Favorite

Share
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false

 */

/*
Please refer to '10. RegularExpressionMatch', very similar thought.
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int sLen = s.length(), pLen = p.length();
        char[] sArr = s.toCharArray(), pArr = p.toCharArray();
        boolean dp[][] = new boolean[sLen+1][pLen+1];
        dp[0][0] = true;
        for (int i = 1; i < pLen+1; i++) {
            if (pArr[i-1] == '*') dp[0][i] = dp[0][i-1];
        }

        for (int i = 1; i < sLen+1; i++) {
            for (int j = 1; j < pLen+1; j++) {
                if (pArr[j-1] == '?') dp[i][j] = dp[i-1][j-1];
                else if (pArr[j-1] == sArr[i-1]) dp[i][j] = dp[i-1][j-1];
                else if (pArr[j-1] == '*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j-1] || dp[i-1][j];
                } else dp[i][j] = false;

            }
        }
        return dp[sLen][pLen];
    }
}
