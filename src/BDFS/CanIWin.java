package BDFS;

import java.util.HashMap;

/**
 * 464. Can I Win: https://leetcode.com/problems/can-i-win/
 * Medium
 *
 * In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10. The player who first causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.
 *
 * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise, return false. Assume both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 11
 * Output: false
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 * Example 2:
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 0
 * Output: true
 * Example 3:
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 1
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= maxChoosableInteger <= 20
 * 0 <= desiredTotal <= 300
 */
public class CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }

        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) {
            return false;
        }

        return dfsPruning(maxChoosableInteger, desiredTotal, 0, (int) Math.pow(2, maxChoosableInteger) - 1, new HashMap<>());
    }

    // example: pool = 1111 1111
    private boolean dfsPruning(int maxChoosableInteger, int desiredTotal, int sum, int pool, HashMap<Integer, Boolean> mem) {
        if (sum >= desiredTotal) {
            mem.put(pool, false);
            return false;
        }

        Boolean res = mem.get(pool);
        if (res != null) return res;

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int mask = 1 << (i - 1);
            if ((pool & mask) != 0) {
                boolean result = dfsPruning(maxChoosableInteger, desiredTotal, sum + i, pool - mask, mem);
                if (!result) {
                    mem.put(pool, true);
                    return true;
                }
            }

        }
        mem.put(pool, false);
        return false;
    }
}
