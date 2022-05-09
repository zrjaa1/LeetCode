package BDFS;

import java.util.List;

/**
 * 139. Word Break: https://leetcode.com/problems/word-break/
 * Medium
 *
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class WordBreak {
    /**
     * Sol1: pruning
     */
    public boolean wordBreakPruning(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        return dfsWithPruning(s, wordDict, 0, new Boolean[s.length()]);
    }

    private static boolean dfsWithPruning(String s, List<String> wordDict, int index, Boolean[] mem) {
        // base case
        if (index == s.length()) {
            return true;
        }

        if (mem[index] != null) {
            return mem[index];
        }

        boolean result = false;
        for (int i = index; i < s.length(); i++) {
            String word = s.substring(index, i + 1);
            if (wordDict.contains(word)) {
                result |= dfsWithPruning(s, wordDict, i + 1, mem);
            }
        }
        mem[index] = result;
        return result;
    }

    /**
     * Sol2: DP
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // corner case
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j <= len; j++) {
                if (dp[j]) {
                    if (wordDict.contains(s.substring(i, j))) {
                        dp[i] = true;
                    }
                }
            }
        }
        return dp[0];
    }
}
