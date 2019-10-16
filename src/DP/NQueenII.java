package DP;
import java.util.*;

/*
similar to N-Queens and easier. The only difference is to use a int[1] not int (since java is pass by value)
 */

public class NQueenII {
    public int totalNQueens(int n) {
        int[] res = new int[1];
        if (n <= 0) return res[0];
        List<String> sol = new ArrayList<>();
        helper(n, 0, sol, res);
        return res[0];
    }

    private void helper(int n, int index, List<String> sol, int[] res) {
        if (index == n) {
            res[0]++;
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!fit(n, i, sol)) sb.append('.');
            else {
                sb.append('Q');
                while (sb.length() != n) sb.append('.');
                sol.add(sb.toString());
                helper(n, index+1, sol, res);
                sol.remove(sol.size()-1);
                sb.setLength(i);
                sb.append('.');
            }
        }
    }

    private boolean fit(int n, int index, List<String> sol) {
        int mid = index;
        int left = mid-1;
        int right = mid+1;
        for (int i = sol.size()-1; i >= 0; i--) {
            String temp = sol.get(i);
            if (left >= 0 && temp.charAt(left) == 'Q') return false;
            if (right < n && temp.charAt(right) == 'Q') return false;
            if (temp.charAt(mid) == 'Q') return false;
            left--;
            right++;
        }
        return true;
    }
}
