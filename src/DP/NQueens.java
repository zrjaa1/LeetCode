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
    Optimization: Use 3 boolean array: conflictUp, conflictLeft, conflictRight to record the possible conflict position. Remember to clone 3 new arrays when you call the recursion (behaves like back-tracing)
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
        updateConflictArray(n, sol, conflictUp, conflictLeft, conflictRight);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!fit(i, conflictUp, conflictLeft, conflictRight)) sb.append('.');
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

    private void updateConflictArray(int n, List<String> sol, boolean[] conflictUp, boolean[] conflictLeft, boolean[] conflictRight) {
        if (sol.size() < 1) return;
        String lastRow = sol.get(sol.size()-1);
        for (int i = 0; i < n; i++) {
            if (lastRow.charAt(i) == 'Q' || conflictUp[i] == true) conflictUp[i] = true;
            else conflictUp[i] = false;
        }
        for (int i = n-1; i >= 1; i--) {
            if (lastRow.charAt(i-1) == 'Q' || conflictLeft[i-1] == true) conflictLeft[i] = true;
            else conflictLeft[i] = false;
        }
        for (int i = 0; i <= n-2; i++) {
            if (lastRow.charAt(i+1) == 'Q' || conflictRight[i+1] == true) conflictRight[i] = true;
            else conflictRight[i] = false;
        }
    }

    private boolean fit(int index, boolean[] conflictUp, boolean[] conflictLeft, boolean[] conflictRight) {
        return !(conflictUp[index] || conflictLeft[index] || conflictRight[index]);
    }

    public static void main(String[] args) {
        NQueens tester = new NQueens();
        tester.solveNQueens(4);
    }
}

