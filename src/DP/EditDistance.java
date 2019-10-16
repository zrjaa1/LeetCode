package DP;

/*
72. Edit Distance
Hard

1779

27

Favorite

Share
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

/*
DP, dp[i][j] means the mininum steps to change from s1.substring(0,i) to s2.substring(0,j)
if s1.charAt(i) == s1.charAt(j), dp[i][j] = dp[i-1][j-1]
else dp[i][j] = minThree(dp[i-1][j-1]+1, dp[i-1][j]+1, dp[i][j-1]+1);
Note: the size of dp should be [n1+1][n2+1], be careful about the index when read the char in the string.

// sol 2: further optimized Space Complexity to O(min(m,n)).
Use an array and 2 more temp int to implement this.
(Better to draw a 2D -> 1D array map to understand it)
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;

        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1+1][n2+1];

        for (int i = 0; i <= n2; i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i <= n1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = minThree(dp[i-1][j-1]+1, dp[i-1][j]+1, dp[i][j-1]+1);
            }
        }
        return dp[n1][n2];
    }

    //
    /* sol 2: optimized Space Complexity from above
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return -1;

        int n1 = word1.length();
        int n2 = word2.length();
        if (n1 > n2) return minDistance(word2, word1);
        int[] dp = new int[n1+1];

        for (int i = 0; i <= n1; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= n2; i++) {
            dp[0] = i;
            int prev = i-1; // used to record the left-up corner
            for (int j = 1; j <= n1; j++) {
                int cur = prev;
                prev = dp[j];
                if (word1.charAt(j-1) == word2.charAt(i-1)) dp[j] = cur;
                else dp[j] = minThree(cur+1, dp[j]+1, dp[j-1]+1);
            }
        }
        return dp[n1];
    }
     */

    private int minThree(int num1, int num2, int num3) {
        int min = Math.min(num1, num2);
        return min = Math.min(min, num3);
    }

    public static void main(String[] args) {
        EditDistance tester = new EditDistance();
        String word1 = "abcc";
        String word2 = "feab";
        System.out.println(tester.minDistance(word1, word2));
    }
}
