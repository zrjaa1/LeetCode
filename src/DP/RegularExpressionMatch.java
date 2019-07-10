package DP;

/*
10. Regular Expression Matching
Hard

2173

433

Favorite

Share
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */

/*
因为c*可以代表0个 c，我们不能用一次 iteration 的方法
这里用二维 dp, dp[i][j]代表的是s至前 i 位，p 至前 j 位是否能匹配
dp 的填图如下:
dp[0][0] -> true，人为设定，因为后面填的时候需要用到这一位
dp[0][n] -> 视情况而定，如果恰好 p里面带'*'，说明可以代表0个之前的元素，此时dp[0][n] = dp[0][n-2]，否则肯定为 false
dp[n][0] -> 肯定为 false
dp[i][j] -> 1. dp[i-1][j-1] if s[i-1] == p[j-1] 两字符直接相等
            2. dp[i-1][j-1] if p[j-1] == '.'    p 是'.'
            3. dp[i][j-2]   if p[j-1] == '*' 并且 '*' 之前的字符与s[i-1]无法匹配
            4. dp[i][j-2] || dp[i][j-1] || dp[i-1][j] if p[j-1] == '*' 并且 '*' 之前的字符能与 s[i-1]匹配
            其中上述4中的dp[i][j] = dp[i-1][j]的可能性是 dp 的精髓，这种情况下，我们把'*'解释为多个，因为我们无法知道'*'具体代表了多少个，我们
            把它交给之前的处理，这样一直迭代回去，总会有'*'代表1个或0个的情况，那时我们就会得出结果

本题的另一个考点是 dp 的含义的初始化问题，需要彻底理清其中的逻辑关系才能解除本题。
例如： 1. dp[i][j]代表的是s长度为i，p 长度为 j，而不是第 i 位，第 j 位
      2. dp[0][n]的初始化情况，需要考虑到'*'的影响，并且认识到只有'*'代表0个，才使该位有 true 的可能性
      3. 在考察dp[i][j]时，考虑的是s[i-1], p[j-1]，这是dp[i][j]代表的含义所决定的

 */
public class RegularExpressionMatch {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int sLen = s.length(), pLen = p.length();
        char[] sArr = s.toCharArray(), pArr = p.toCharArray();
        boolean dp[][] = new boolean[sLen+1][pLen+1];
        dp[0][0] = true;
        for (int i = 1; i < pLen+1; i++) {
            if (pArr[i-1] == '*' && i>=2) dp[0][i] = dp[0][i-2];
            else dp[0][i] = false;
        }

        for (int i = 1; i < sLen+1; i++) {
            for (int j = 1; j < pLen+1; j++) {
                if (pArr[j-1] == '.') dp[i][j] = dp[i-1][j-1];
                else if (pArr[j-1] == sArr[i-1]) dp[i][j] = dp[i-1][j-1];
                else if (pArr[j-1] == '*') {
                    if (pArr[j-2] != sArr[i-1] && pArr[j-2] != '.') dp[i][j] = dp[i][j-2];
                    else dp[i][j] = dp[i][j-2] || dp[i][j-1] || dp[i-1][j];
                } else dp[i][j] = false;

            }
        }
        return dp[sLen][pLen];
    }
}
