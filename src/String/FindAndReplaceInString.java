package String;

import java.util.*;

/**
 * 833. Find And Replace in String: https://leetcode.com/problems/find-and-replace-in-string/
 * Medium
 *
 * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 *
 * To complete the ith replacement operation:
 *
 * Check if the substring sources[i] occurs at index indices[i] in the original string s.
 * If it does not occur, do nothing.
 * Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
 *
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.
 *
 * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
 * Return the resulting string after performing all replacement operations on s.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
 * Output: "eeebffff"
 * Explanation:
 * "a" occurs at index 0 in s, so we replace it with "eee".
 * "cd" occurs at index 2 in s, so we replace it with "ffff".
 * Example 2:
 *
 *
 * Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation:
 * "ab" occurs at index 0 in s, so we replace it with "eee".
 * "ec" does not occur at index 2 in s, so we do nothing.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * k == indices.length == sources.length == targets.length
 * 1 <= k <= 100
 * 0 <= indexes[i] < s.length
 * 1 <= sources[i].length, targets[i].length <= 50
 * s consists of only lowercase English letters.
 * sources[i] and targets[i] consist of only lowercase English letters.
 */

/**
 * 核心在于，不是一次次replace，而是先收集起来，然后一起replacement.
 */
public class FindAndReplaceInString {
    /**
     * Sol1: my solution. Use a Map<Idx, StringBuilder> to record the replacement and build later.
     * O(T) = O(n * k) where n is the number of replacement, k is the max length in the source string.
     */
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        // initialization
        Map<Integer, StringBuilder> map = new HashMap<>(); // The map originally contain each character and its content
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(i)); // put the original character in the idx
            map.put(i, sb);
        }

        // replace
        for (int i = 0; i < indices.length; i++) {
            boolean match = true;
            for (int j = 0; j < sources[i].length(); j++) { // check if it's substring
                if (indices[i] + j >= s.length() || s.charAt(indices[i] + j) != sources[i].charAt(j)) {
                    match = false;
                    break;
                }
            }

            if (match) { // replace if match
                map.put(indices[i], new StringBuilder(targets[i]));
                for (int j = 1; j < sources[i].length(); j++) { // remove following characters except the first one, since they will be replaced.
                    map.remove(indices[i] + j);
                }
            }
        }

        // construct the result
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(i)) {
                res = res.append(map.get(i));
            }
        }

        return res.toString();
    }

    /**
     * Sol2: RECOMMENDED. Two maps. An optimized way so that we don't need to worry about removing entries in the map in Sol1.
     * O(T) = O(n)
     */
    public String findReplaceStringSol2(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> sourceMap = new HashMap<>();
        Map<Integer, String> targetMap = new HashMap<>();

        for (int i = 0; i < indexes.length; i++) { // for each index, what is its source and target
            sourceMap.put(indexes[i], sources[i]);
            targetMap.put(indexes[i], targets[i]);
        }

        int i = 0;
        while (i < S.length()) { // Iterate over the original String, append original character or target to the res builder, jump when replacement.
            if (sourceMap.containsKey(i) && S.startsWith(sourceMap.get(i), i)) { // replacement
                sb.append(targetMap.get(i));
                i = i + sourceMap.get(i).length(); // since we don't overlap, it's safe to jump. Also, we have to jump to avoid involving replaced characters in original String.
            } else {
                sb.append(S.charAt(i));
                i++;
            }
        }

        return sb.toString();
    }

}
