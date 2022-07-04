package DFS;
import java.util.*;

/**
301. Remove Invalid Parentheses： https://leetcode.com/problems/remove-invalid-parentheses/
Hard

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */

/**
 * DFS, 无脑删，在base case时检查是否valid
 * m个连续相同元素，通过只pick/remove前n个的方式de-dup。以这道题为例子记忆。
 */
public class RemoveInvalidParentheses {
    // Sol1: 无脑删，用HashSet去重
    public List<String> removeInvalidParenthesesSol1(String s) {
        List<String> res = new LinkedList<>();
        if (s == null || s.length() == 0) return res;

        int[] rm = evaluate(s); // pre-processing, 因为并一定是delta == 0就是valid parentheses
        Set<String> resSet = new HashSet<>();
        dfs1(s, new StringBuilder(), resSet, rm[0], rm[1], 0, 0);
        for (String path: resSet) {
            res.add(path);
        }
        return res;
    }

    private void dfs1(String s, StringBuilder path, Set<String> res, int rmL, int rmR, int idx, int delta) {
        if (idx == s.length()) { // success case
            if (rmL == 0 && rmR == 0 && delta == 0) {
                res.add(path.toString());
                return;
            } else { // failure case
                return;
            }
        }

        // failure case
        if (delta < 0) {
            return;
        }

        int len = path.length();
        char c = s.charAt(idx);
        if (c == '(') {
            // keep it
            path.append('(');
            dfs1(s, path, res, rmL, rmR, idx + 1, delta + 1);
            path.setLength(len);

            // remove it
            dfs1(s, path, res, rmL - 1, rmR, idx + 1, delta);
        } else if (c == ')') {
            path.append(')');
            dfs1(s, path, res, rmL, rmR, idx + 1, delta - 1);
            path.setLength(len);
            dfs1(s, path, res, rmL, rmR - 1, idx + 1, delta);
        } else {
            path.append(c);
            dfs1(s, path, res, rmL, rmR, idx + 1, delta);
            path.setLength(len);
        }
    }

    public List<String> removeInvalidParenthesesSol2(String s) {
        List<String> res = new LinkedList<>();
        if (s == null || s.length() == 0) return res;

        int[] rm = evaluate(s);
        dfs2(s, new StringBuilder(), res, rm[0], rm[1], 0, 0);

        return res;
    }

    /**
     * Solution 2. Remove the first k items to de-duplicate.
     * Demo: *****(((******
     * (Do remove)|__ (Don't remove) - 1. (((
     *  (Do remove)|_ (Don't remove) - 2. ((
     *   (Do remove)| (Don't remove) - 3. (
     *                                 4. empty
     */
    private void dfs2(String s, StringBuilder path, List<String> res, int rmL, int rmR, int idx, int delta) {
        // success case
        if (idx == s.length()) {
            if (rmL == 0 && rmR == 0 && delta == 0) {
                res.add(path.toString());
                return;
                // failure case
            } else {
                return;
            }
        }

        // failure case， pruning
        if (delta < 0) {
            return;
        }

        int len = path.length();
        char c = s.charAt(idx);
        if (c == '(') {
            // keep it
            int nextIdx = jump(s, idx, true); // 判断前面还有多少个连续的 (
            for (int i = idx; i < nextIdx; i++) { // 这样写保证了只remove前n个元素，如果直接无脑index+1进循环的话，就会duplicate
                path.append('(');
            }
            dfs2(s, path, res, rmL, rmR, nextIdx, delta + (nextIdx - idx));
            path.setLength(len);

            // remove it
            dfs2(s, path, res, rmL - 1, rmR, idx + 1, delta);
        } else if (c == ')') {
            // keep it
            int nextIdx = jump(s, idx, false);
            for (int i = idx; i < nextIdx; i++) {
                path.append(')');
            }
            dfs2(s, path, res, rmL, rmR, nextIdx, delta - (nextIdx - idx));
            path.setLength(len);

            // remove it
            dfs2(s, path, res, rmL, rmR - 1, idx + 1, delta);
        } else {
            path.append(c);
            dfs2(s, path, res, rmL, rmR, idx + 1, delta);
            path.setLength(len);
        }
    }

    private int jump(String s, int idx, boolean left) {
        char parenthese = left ? '(' : ')';

        int nextIdx = idx;
        while(nextIdx < s.length() && s.charAt(nextIdx) == parenthese) {
            nextIdx++;
        }
        return nextIdx;
    }

    private int[] evaluate(String s) {
        int[] rm = new int[2]; // rm[0] = rmL, rm[1] = rmR;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') rm[0]++;
            else if (c == ')') {
                if (rm[0] > 0) rm[0]--;
                else rm[1]++;
            }
        }
        return rm;
    }
}
