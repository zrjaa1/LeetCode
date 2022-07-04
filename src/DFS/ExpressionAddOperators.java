package DFS;

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

/**
 * DFS. Branching: 1. insert operators(+, -, *) 2. count it as a value
 * 另外本题一个很重要的点是lastVal在+、-、*运算中的应用，最好画图来观察如何正确应用：
 * 1. 在 +, -， * 时设置lastVal的值（+val, -val, lastVal * val）
 * 2. 在 * 时使用 lastVal：sum = sum - lastVal + lastVal * val
 *
 * 这道题有很多细节，值得复习一下comment
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
            if (sum == target) {
                String expression = path.toString();
                res.add(expression);
            }
            return;
        }

        int len = path.length();

        // keep the value
        long val = 0; // 每次进入dfs时，val始终可以设为0，这是因为无论是第一次进来，还是通过add operator进来，都是一个fresh start
        for (int i = idx; i < num.length(); i++) {
            char c = num.charAt(i);
            val = val * 10 + c - '0'; // for loop 继续往前走就是一种branch

            if (idx == 0) { // 特殊情况，因为可以默认每个算式前面是一个'0 + input'的情况
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
