package BDFS;
import java.util.*;

/*
22
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */

/*
Thoughts: 1. return conditions: left + right >= 2n 2. back-tracing
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) return null;
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(n, 0, 0, sb, res);
        return res;
    }

    void dfs(int n, int left, int right, StringBuilder path, List<String> res) {
        if (left + right == 2 * n) {
            res.add(path.toString());
            return;
        }

        // append left
        if (left < n) {
            path.append('(');
            dfs(n, left + 1, right, path, res);
            path.setLength(path.length() - 1);
        }

        // append right
        if (left > right) {
            path.append(')');
            dfs(n, left, right + 1, path, res);
            path.setLength(path.length() - 1);
        }
    }
}
