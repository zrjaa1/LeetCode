package DP;

/**
72. Edit Distance: https://leetcode.com/problems/edit-distance/
Hard

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 */

/**
 * DP[i][j]: the minimal edit distance between word[0, idx1) and word2[0, idx2)
 */
public class EditDistance {
    // DP, O(m*n)
    public int minDistance(String word1, String word2) {
        // corner case
        if (word1.length() == 0) {
            return word2.length();
        }

        if (word2.length() == 0) {
            return word1.length();
        }

        // initial value
        int[][] dp = new int[word1.length() + 1][word2.length() + 1]; // the minimal edit distance between word[0, idx1) and word2[0, idx2)
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        // fill in values
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    min = Math.min(min, dp[i - 1][j - 1]);
                    dp[i][j] = 1 + min;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        EditDistance tester = new EditDistance();
        String word1 = "abcc";
        String word2 = "feab";
        System.out.println(tester.minDistance(word1, word2));
    }
}
