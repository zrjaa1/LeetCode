package BDFS;

import java.util.HashMap;

/**
 * 294. Flip Game II: https://leetcode.com/problems/flip-game-ii/
 * Medium
 *
 * You are playing a Flip Game with your friend.
 *
 * You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the other person will be the winner.
 *
 * Return true if the starting player can guarantee a win, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: currentState = "++++"
 * Output: true
 * Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Example 2:
 *
 * Input: currentState = "+"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= currentState.length <= 60
 * currentState[i] is either '+' or '-'.
 *
 *
 * Follow up: Derive your algorithm's runtime complexity.
 */
public class FlipGameII {
    public boolean canWin(String currentState) {
        return pruning(currentState, new HashMap<>());
    }

    private boolean pruning(String currentState, HashMap<String, Boolean> mem) {
        Boolean value = mem.get(currentState);
        if (value != null) {
            return value;
        }

        int i = 0;
        while (i < currentState.length() - 1) {
            // Do something
            char[] currentChar = currentState.toCharArray();
            if (currentChar[i] == '+' && currentChar[i + 1] == '+') {
                currentChar[i] = '-';
                currentChar[i + 1] = '-';
                boolean res = pruning(String.valueOf(currentChar), mem); // once we have a chance to let our opponent lose, we will play optimally.
                currentChar[i] = '+';
                currentChar[i + 1] = '+';

                if (!res) {
                    mem.put(currentState, true);
                    return true;
                }
            }
            i++;
        }
        mem.put(currentState, false);
        return false;
    }
}
