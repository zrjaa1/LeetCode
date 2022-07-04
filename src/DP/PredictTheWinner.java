package DP;

/**
 * 486. Predict the Winner: https://leetcode.com/problems/predict-the-winner/
 * Medium
 *
 * You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.
 *
 * Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0. At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1. The player adds the chosen number to their score. The game ends when there are no more elements in the array.
 *
 * Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return true. You may assume that both players are playing optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,2]
 * Output: false
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return false.
 * Example 2:
 *
 * Input: nums = [1,5,233,7]
 * Output: true
 * Explanation: Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 107
 */
public class PredictTheWinner {
    /**
     * Original Solution: dp is the maximum value the player 1 could get in nums[i, j], needs to see if the current player is player 1 or not.
     */
    public boolean PredictTheWinner(int nums[]) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int len = nums.length;
        int sum = 0;
        int[][] dp = new int[len][len]; // dp[i][j]: the max value the player 1 could get in nums[i, j]

        for (int i = len - 1; i >= 0; i--) {
            sum += nums[i];
            for (int j = i; j < len; j++) {
                if (playerOnePlaying(i, j, len)) {
                    int caseI = nums[i] + (i + 1 >= len ? 0 : dp[i + 1][j]);
                    int caseJ = nums[j] + (j - 1 < 0 ? 0 : dp[i][j - 1]);
                    dp[i][j] = Math.max(caseI, caseJ);
                } else {
                    int caseI = i + 1 >= len ? 0 : dp[i + 1][j];
                    int caseJ = j - 1 < 0 ? 0 : dp[i][j - 1];
                    dp[i][j] = Math.min(caseI, caseJ);
                }
            }
        }
        return dp[0][len-1] >= sum - dp[0][len-1];
    }

    private boolean playerOnePlaying(int i, int j, int totalLength) {
        return (totalLength - (j - i + 1)) % 2 == 0;
    }

    /**
     * Improved solution that I come up with: dp is the maximum value that the current player could get in nums[i, j). Beats 100%!
     */

    public boolean PredictTheWinner2(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int len = nums.length;
        int sum = 0;
        int[][] dp = new int[len + 1][len + 1]; // dp[i][j]: the max value the current player could get in nums[i, j)
        for (int i = len - 1; i >= 0; i--) {
            sum += nums[i];
            int curSum = 0;
            for (int j = i + 1; j <= len; j++) {
                curSum += nums[j - 1];
                dp[i][j] = curSum - Math.min(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[0][len] >= sum - dp[0][len];
    }
}
