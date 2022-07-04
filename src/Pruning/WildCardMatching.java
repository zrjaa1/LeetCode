package Pruning;

/**
 * 44. Wildcard Matching: https://leetcode.com/problems/wildcard-matching/
 * Hard
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 *
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 *
 * Constraints:
 *
 * 0 <= s.length, p.length <= 2000
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '?' or '*'.
 */

/**
 * 参考 LC 10 Regular Expression Matching，但本题更简单，因为*不依赖preceding element
 */
public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        return dfsWithPruning(s, p, 0, 0, new Boolean[s.length() + 1][p.length() + 1]);
    }

    private static boolean dfsWithPruning(String s, String p, int idxS, int idxP, Boolean[][] mem) {
        if (mem[idxS][idxP] != null) return mem[idxS][idxP];

        int lenP = p.length(), lenS = s.length();
        if (idxP == lenP) {
            boolean res = idxS == lenS;
            mem[idxS][idxP] = res;
            return res;
        }

        if (p.charAt(idxP) != '*') { // non * case
            if (idxS < lenS && match(s, p, idxS, idxP)) { // our base case doesn't cover idexS == lenS
                return dfsWithPruning(s, p, idxS + 1, idxP + 1, mem);
            } else {
                mem[idxS][idxP] = false;
                return false;
            }
        } else { // * case
            int i = idxS;
            boolean res = dfsWithPruning(s, p, i, idxP + 1, mem); // * matching empty sequence
            while(i < lenS) {
                res |= dfsWithPruning(s, p, i + 1, idxP, mem); // * matching 1 or more characters
                if (res) {
                    mem[idxS][idxP] = true;
                    return true;
                }
                i++;
            }
            mem[idxS][idxP] = res;
            return res;
        }
    }

    private static boolean match(String s, String p, int indexS, int indexP) {
        char pattern = p.charAt(indexP);
        if (pattern == '?') {
            return true;
        } else if (pattern == '*') {
            throw new IllegalArgumentException("This method is not supposed to be used for a * matching");
        } else {
            return pattern == s.charAt(indexS);
        }
    }

    public static void main(String[] args) {
        WildCardMatching tester = new WildCardMatching();
        String s = "ab";
        String p = "*";
        boolean res = tester.isMatch(s, p);
        System.out.println(res);
    }
}
