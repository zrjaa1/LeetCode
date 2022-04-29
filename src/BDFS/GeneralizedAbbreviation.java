package BDFS;

import java.util.LinkedList;
import java.util.List;

/**
 * 320. Generalized Abbreviation: https://leetcode.com/problems/generalized-abbreviation/
 * Medium
 *
 * A word's generalized abbreviation can be constructed by taking any number of non-overlapping and non-adjacent substrings and replacing them with their respective lengths.
 *
 * For example, "abcde" can be abbreviated into:
 * "a3e" ("bcd" turned into "3")
 * "1bcd1" ("a" and "e" both turned into "1")
 * "5" ("abcde" turned into "5")
 * "abcde" (no substrings replaced)
 * However, these abbreviations are invalid:
 * "23" ("ab" turned into "2" and "cde" turned into "3") is invalid as the substrings chosen are adjacent.
 * "22de" ("ab" turned into "2" and "bc" turned into "2") is invalid as the substring chosen overlap.
 * Given a string word, return a list of all the possible generalized abbreviations of word. Return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "word"
 * Output: ["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
 * Example 2:
 *
 * Input: word = "a"
 * Output: ["1","a"]
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 15
 * word consists of only lowercase English letters.
 */
public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new LinkedList<>();
        if (word == null || word.length() == 0) {
            return res;
        }

        dfs(word, 0, res, new StringBuilder());
        return res;
    }

    private void dfs(String word, int idx, List<String> res, StringBuilder path) {
        // base case
        if (idx == word.length()) {
            res.add(path.toString());
            return;
        }

        char c = word.charAt(idx);
        int len = path.length();

        // keep the character
        path.append(c);
        dfs(word, idx + 1, res, path);
        path.setLength(len);

        // convert into number
        /// character case
        if (len == 0 || path.charAt(len-1) < '0' || path.charAt(len-1) > '9') {
            path.append('1');
        } else {
            addNumberByOne(path);
        }
        dfs(word, idx + 1, res, path);
    }

    private void addNumberByOne(StringBuilder path) {
        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException();
        }

        int idx = path.length() - 1;
        // get the actual number
        int num = 0, weight = 1;
        while (idx >= 0 && path.charAt(idx) >= '0' && path.charAt(idx) <= '9') {
            int cur = path.charAt(idx) - '0';
            num += cur * weight;
            weight *= 10;
            idx--;
        }
        // add one to the final number
        num++;
        path.setLength(idx + 1);
        path.append(num);
    }
}
