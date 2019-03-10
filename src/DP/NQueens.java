package DP;
import java.util.*;

/*
51. N-Queens
Hard

776

35

Favorite

Share
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

/* Thought
    DFS, easy. Remember to 1. back-tracing and 2. create a new list when adding to result (deep copy).
 */

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        List<String> sol = new ArrayList<>();
        boolean[] conflictUp = new boolean[n];
        boolean[] conflictLeft = new boolean[n];
        boolean[] conflictRight = new boolean[n];
        helper(n, 0, sol, res, conflictUp, conflictLeft, conflictRight);
        return res;
    }

    private void helper(int n, int index, List<String> sol, List<List<String>> res, boolean[] conflictUp, boolean[] conflictLeft, boolean[] conflictRight) {
        if (index == n) {
            List<String> temp = new ArrayList<>(sol);
            res.add(temp);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!fit(n, i, sol, conflictUp, conflictLeft, conflictRight)) sb.append('.');
            else {
                sb.append('Q');
                while (sb.length() != n) sb.append('.');
                sol.add(sb.toString());
                boolean[] newConflictUp = conflictUp.clone();
                boolean[] newConflictLeft = conflictLeft.clone();
                boolean[] newConflictRight = conflictRight.clone();
                helper(n, index+1, sol, res, newConflictUp, newConflictLeft, newConflictRight);
                sol.remove(sol.size()-1);
                sb.setLength(i);
                sb.append('.');
            }
        }
    }

    private boolean fit(int n, int index, List<String> sol, boolean[] conflictUp, boolean[] conflictLeft, boolean[] conflictRight) {
        if (sol.size() < 1) return true;
        String lastRow = sol.get(sol.size()-1);
        if (index - 1 >= 0 && (lastRow.charAt(index-1) == 'Q' || conflictLeft[index] ==  true)) {
            conflictLeft[index] = true;
            return false;
        }
        if (index + 1 < n && (lastRow.charAt(index+1) == 'Q' || conflictRight[index] ==  true)) {
            conflictRight[index] = true;
            return false;
        }
        if (lastRow.charAt(index) == 'Q' || conflictUp[index] ==  true) {
            conflictUp[index] = true;
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        NQueens tester = new NQueens();
        tester.solveNQueens(4);
    }
}

