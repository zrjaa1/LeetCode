package BDFS;

import java.util.LinkedList;
import java.util.List;

/**
 * 282. Expression Add Operators: https://leetcode.com/problems/expression-add-operators/
 * Hard
 *
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 * Example 3:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -231 <= target <= 231 - 1
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new LinkedList<>();
        if (num == null || num.length() == 0) {
            return res;
        }

        dfs(num, target, new StringBuilder(), 0, res, 0, 0);
        return res;
    }

    private void dfs(String num, int target, StringBuilder path, int idx, List<String> res, long sum, long lastVal) {
        // base case
        if (idx == num.length()) {
            String expression = path.toString();
            if (sum == target) {
                res.add(expression);
            }
            return;
        }

        int len = path.length();

        // keep the value
        long val = 0;
        for (int i = idx; i < num.length(); i++) {
            char c = num.charAt(i);
            val = val * 10 + c - '0'; // for loop 继续往前走就是一种branch

            if (idx == 0) {
                path.append(val);
                dfs(num, target, path, i + 1, res, val, val);
            } else {
                // add operation
                path.append('+');
                path.append(val);
                dfs(num, target, path, i + 1, res, sum + val, val);
                path.setLength(len);

                // minus operation
                path.append('-');
                path.append(val);
                dfs(num, target, path, i + 1, res, sum - val, -val);
                path.setLength(len);

                // multiple operation
                path.append('*');
                path.append(val);
                dfs(num, target, path, i + 1, res, sum - lastVal + lastVal * val, lastVal * val);
            }
            path.setLength(len);
            if (val == 0) break; // to avoid case 00
        }
    }
}
