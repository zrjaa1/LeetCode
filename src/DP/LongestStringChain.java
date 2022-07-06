package DP;

import java.util.*;

/**
 * 1048. Longest String Chain: https://leetcode.com/problems/longest-string-chain/
 * Medium
 *
 * You are given an array of words where each word consists of lowercase English letters.
 *
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.
 *
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
 *
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
 * Example 2:
 *
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 * Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 * Example 3:
 *
 * Input: words = ["abcd","dbqca"]
 * Output: 1
 * Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
 * ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] only consists of lowercase English letters.
 */

/**
 * This is a non-traditional dp problem. Although the key of pruning is String, the feature of this problem determines
 * that we could fill in values consecutively, which makes it dp-able.
 * O(T) = O(nlogn + n * L^2) = O(n * (logn + L^2))
 *
 * Optimization: Pre-Build graph instead of build during dp state transition.
 */
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>(); // key - word, val - longest string chain starting from that word

        Arrays.sort(words, (a, b) -> a.length() - b.length()); // O(nlogn)
        for (String word : words) {
            dp.put(word, 1);
        }

        int res = 1;
        for (int i = 0; i < words.length; i++) { // n times
            if (words[i].length() == 1) {
                continue;
            }

            int len = 1;
            for (int j = 0; j < words[i].length(); j++) { // L times, where L is the longest word length
                StringBuilder sb = new StringBuilder(words[i]);
                sb.deleteCharAt(j);
                String predecessor = sb.toString(); // O(L)
                len = Math.max(len, dp.getOrDefault(predecessor, 0) + 1);
            }
            dp.put(words[i], len);
            res = Math.max(res, len);
        }

        return res;
    }
}
