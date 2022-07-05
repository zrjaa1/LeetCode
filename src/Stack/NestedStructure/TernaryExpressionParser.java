package Stack.NestedStructure;

import java.util.Stack;

/**
 *  439. Ternary Expression Parser: https://leetcode.com/problems/ternary-expression-parser/
 * Medium
 *
 * Given a string expression representing arbitrarily nested ternary expressions, evaluate the expression, and return the result of it.
 *
 * You can always assume that the given expression is valid and only contains digits, '?', ':', 'T', and 'F' where 'T' is true and 'F' is false. All the numbers in the expression are one-digit numbers (i.e., in the range [0, 9]).
 *
 * The conditional expressions group right-to-left (as usual in most languages), and the result of the expression will always evaluate to either a digit, 'T' or 'F'.
 *
 *
 *
 * Example 1:
 *
 * Input: expression = "T?2:3"
 * Output: "2"
 * Explanation: If true, then result is 2; otherwise result is 3.
 * Example 2:
 *
 * Input: expression = "F?1:T?4:5"
 * Output: "4"
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 * "(F ? 1 : (T ? 4 : 5))" --> "(F ? 1 : 4)" --> "4"
 * or "(F ? 1 : (T ? 4 : 5))" --> "(T ? 4 : 5)" --> "4"
 * Example 3:
 *
 * Input: expression = "T?T?F:5:3"
 * Output: "F"
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 * "(T ? (T ? F : 5) : 3)" --> "(T ? F : 3)" --> "F"
 * "(T ? (T ? F : 5) : 3)" --> "(T ? F : 5)" --> "F"
 *
 *
 * Constraints:
 *
 * 5 <= expression.length <= 104
 * expression consists of digits, 'T', 'F', '?', and ':'.
 * It is guaranteed that expression is a valid ternary expression and that each number is a one-digit number.
 */

/**
 * 1. 通过第三个例子可知，从后往前遍历。T/F也可能成为一个return value，而不是只作为判断字符出现
 * 2. 记得在把content放在stack中时，stringBuilder需要先reverse，因为是从后往前遍历的
 * 3. ? 和 : 是content的分隔符，T/F不能作为分隔符
 */
public class TernaryExpressionParser {
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) {
            return "";
        }

        Stack<String> valStack = new Stack<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char ch = expression.charAt(i);
            if (ch == ':') {
                continue;
            } else if (ch == '?') {
                char judgement = expression.charAt(--i);
                String trueString = valStack.pop();
                String falseString = valStack.pop();
                valStack.push(judgement == 'T' ? trueString : falseString);
            } else { // contents, 非 :  ?. 注意 T/F也可以成为content
                StringBuilder sb = new StringBuilder();
                sb.append(ch);
                while (i - 1 >= 0 && expression.charAt(i - 1) != ':' && expression.charAt(i - 1) != '?') {
                    sb.append(expression.charAt(i - 1));
                    i--;
                }
                valStack.push(sb.reverse().toString()); // 记得reverse，因为是从后往前遍历
            }
        }
        return valStack.peek();
    }
}
