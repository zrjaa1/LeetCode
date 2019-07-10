package DP;

/*
91. Decode Ways
Medium

1144

1347

Favorite

Share
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */

/*
1. DFS
2. DP
本题的关键在于'0'的处理。dp 从后往前填很巧妙地解决了这个问题，必须注意的是dp[n] = 1的初始化条件是为了解题人为设定的。
这道题感觉只需要掌握DFS就可以了，DP如果没见过答案的话很难想出来
唯一比较符合逻辑的是，如果 substring(i, i+2) < 26, dp[i] = dp[i+1]+dp[i+2]的表达式，分别代表了不连接和连接的情况。

 */
public class DecodeWays {
    /* 1. DFS
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] c = s.toCharArray();
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 0;
        if (c[0] == '0') return 0;
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            if (c[i-1] == '0')
                if (c[i-2] != '1' || c[i-2] != '2') return 0;
                else dp[i] = dp[i-1];
            else {
                if (c[i-2] == '1') dp[i] = dp[i-1]+1;
                else if (c[i-2] == '2' && c[i-1] <= '6') dp[i] = dp[i-1]+1;
                else dp[i] = dp[i-1];
            }
        }
        return dp[len];
    }
    */

    // 2. DP
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n+1];
        dp[n] = 1;
        dp[n-1] = s.charAt(n-1) == '0' ? 0 : 1;
        for (int i = n-2; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            if (Integer.parseInt(s.substring(i,i+2))<=26) dp[i] = dp[i+1]+dp[i+2];
            else dp[i] = dp[i+1];
        }
        return dp[0];
    }

    public static void main(String[] args) {
        DecodeWays tester = new DecodeWays();
        String s = "22026";

    }
}
