package BDFS;

/**
 * 10. Regular Expression Matching: https://leetcode.com/problems/regular-expression-matching/
 * Hard
 *
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
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
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class RegularExpressionMatching {
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

        if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') { // non * case, look forward to check if there is a '*' behind
            if (idxS < lenS && match(s, p, idxS, idxP)) { // our base case doesn't cover idexS == lenS
                return dfsWithPruning(s, p, idxS + 1, idxP + 1, mem);
            } else {
                mem[idxS][idxP] = false;
                return false;
            }
        } else { // * case
            int i = idxS - 1;
            while(i < lenS && (i == idxS - 1 || match(s, p, i, idxP))) {
                if (dfsWithPruning(s, p, i + 1, idxP + 2, mem)) {
                    mem[idxS][idxP] = true;
                    return true;
                }
                i++;
            }
            mem[idxS][idxP] = false;
            return false;
        }
    }

    private static boolean match(String s, String p, int indexS, int indexP) {
        char pattern = p.charAt(indexP);
        if (pattern == '.') {
            return true;
        } else if (pattern == '*') {
            throw new IllegalArgumentException("This method is not supposed to be used for a * matching");
        } else {
            return pattern == s.charAt(indexS);
        }
    }
}
