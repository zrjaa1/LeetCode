package BDFS;

import java.util.HashMap;
import java.util.Map;

/**
 * 488. Zuma Game: https://leetcode.com/problems/zuma-game/
 * Hard
 *
 * You are playing a variation of the game Zuma.
 *
 * In this variation of Zuma, there is a single row of colored balls on a board, where each ball can be colored red 'R', yellow 'Y', blue 'B', green 'G', or white 'W'. You also have several colored balls in your hand.
 *
 * Your goal is to clear all of the balls from the board. On each turn:
 *
 * Pick any ball from your hand and insert it in between two balls in the row or on either end of the row.
 * If there is a group of three or more consecutive balls of the same color, remove the group of balls from the board.
 * If this removal causes more groups of three or more of the same color to form, then continue removing each group until there are none left.
 * If there are no more balls on the board, then you win the game.
 * Repeat this process until you either win or do not have any more balls in your hand.
 * Given a string board, representing the row of balls on the board, and a string hand, representing the balls in your hand, return the minimum number of balls you have to insert to clear all the balls from the board. If you cannot clear all the balls from the board using the balls in your hand, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: board = "WRRBBW", hand = "RB"
 * Output: -1
 * Explanation: It is impossible to clear all the balls. The best you can do is:
 * - Insert 'R' so the board becomes WRRRBBW. WRRRBBW -> WBBW.
 * - Insert 'B' so the board becomes WBBBW. WBBBW -> WW.
 * There are still balls remaining on the board, and you are out of balls to insert.
 * Example 2:
 *
 * Input: board = "WWRRBBWW", hand = "WRBRW"
 * Output: 2
 * Explanation: To make the board empty:
 * - Insert 'R' so the board becomes WWRRRBBWW. WWRRRBBWW -> WWBBWW.
 * - Insert 'B' so the board becomes WWBBBWW. WWBBBWW -> WWWW -> empty.
 * 2 balls from your hand were needed to clear the board.
 * Example 3:
 *
 * Input: board = "G", hand = "GGGGG"
 * Output: 2
 * Explanation: To make the board empty:
 * - Insert 'G' so the board becomes GG.
 * - Insert 'G' so the board becomes GGG. GGG -> empty.
 * 2 balls from your hand were needed to clear the board.
 *
 *
 * Constraints:
 *
 * 1 <= board.length <= 16
 * 1 <= hand.length <= 5
 * board and hand consist of the characters 'R', 'Y', 'B', 'G', and 'W'.
 * The initial row of balls on the board will not have any groups of three or more consecutive balls of the same color.
 */
public class ZumaGame {
    /**
     * This is not a completely working solution. As it only considered inserting a ball that the same as its neighbor.
     * This won't work for test case: board = "RRWWRRBBRR", hand = "WB"
     */

    Map<Character, Integer> charCount = new HashMap<>();
    int res = -1;

    public int findMinStep(String board, String hand) {
        for (char i : hand.toCharArray()) {
            charCount.put(i, charCount.getOrDefault(i, 0) + 1);
        }
        dfs(board, 0);
        return res;
    }

    private void dfs(String board, int count) {
        board = boom(board); // to remove 3+ consecutive balls without inserting anything, recursively.
        if (board.length() == 0) {
            res = res == -1 ? count : Math.min(res, count);
            return;
        }

        for (int i = 0; i < board.length(); i++) {
            int t = i + 1;
            char cur = board.charAt(i);

            while (t < board.length() && board.charAt(t) == cur) { // find consecutive characters
                t++;
            }

            int seqLen = t - i;
            int cc = Math.max(charCount.getOrDefault(cur, 0), 0);  // how many balls you have in hand

            if (seqLen + cc >= 3) {
                int numMoves = seqLen < 3 ? 3 - seqLen : 0;
                charCount.put(cur, charCount.getOrDefault(cur, 0) - numMoves); // update your hand
                String newBoard = board.substring(0, i) + board.substring(t, board.length());
                dfs(newBoard, count + numMoves);
                charCount.put(cur, charCount.getOrDefault(cur, 0) + numMoves);
            }
            i = t - 1; // we leave it alone, and don't insert any ball
        }
    }

    private String boom(String board) {
        for (int i = 0; i < board.length(); i++) {
            int t = i + 1;
            char cur = board.charAt(i);

            while (t < board.length() && board.charAt(t) == cur) {
                t++;
            }

            int seqLen = t - i;
            if (seqLen >= 3) {
                board = board.substring(0, i) + board.substring(t);
                board = boom(board);
            }
        }

        return board;
    }

    public static void main(String[] args) {
        ZumaGame tester = new ZumaGame();
        String board = "WWBBWBBWW";
        String hand = "BB";
        int res = tester.findMinStep(board, hand);
        System.out.println(res);
    }
}
