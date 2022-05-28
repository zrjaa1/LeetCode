package BDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 140. Word Break II: https://leetcode.com/problems/word-break-ii/
 * Hard
 *
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * Example 2:
 *
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return res;
        }
        dfsWithPruning(s, wordDict, 0, res, "", new Boolean[s.length()]);
        return res;
    }

    private static void dfsWithPruning(String s, List<String> wordDict, int idx, List<String> res, String cur, Boolean[] cuttable) {
        // base case
        if (idx == s.length()) {
            res.add(cur.substring(1));
            return;
        }

        if (cuttable[idx] != null && cuttable[idx] == false) {
            return;
        }

        int numRes = res.size();
        // branching
        for (int i = idx + 1; i <= s.length(); i++) {
            String word = s.substring(idx, i);
            if (wordDict.contains(word)) {
                int len = cur.length();
                cur = cur + ' ' + word;
                dfsWithPruning(s, wordDict, i, res, cur, cuttable);
                cur = cur.substring(0, len);
            }
        }
        if (res.size() != numRes) {
            cuttable[idx] = true;
        } else {
            cuttable[idx] = false;
        }
    }
}
