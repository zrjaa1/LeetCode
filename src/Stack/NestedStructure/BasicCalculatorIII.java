package Stack.NestedStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 772. Basic Calculator III: https://leetcode.com/problems/basic-calculator-iii/
 * Hard
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1+1"
 * Output: 2
 * Example 2:
 *
 * Input: s = "6-4/2"
 * Output: 4
 * Example 3:
 *
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 *
 *
 * Constraints:
 *
 * 1 <= s <= 104
 * s consists of digits, '+', '-', '*', '/', '(', and ')'.
 * s is a valid expression.
 */

/**
 * A classic problem of using stacks, thought of modulation. Many details that will be testified while writing the code.
 *
 * 1. 在加入operator如栈时，trigger 优先级 <= 它的栈中已有的运算，例如，遇到 + - 时肯定会引发之前 + - * / 的计算。遇到 * / 时会引发 * / 的计算
 * 这样保证了在遇到 ) 无脑运算的时候，总是可以从栈顶开始计算。
 * （注意必须是 <= 不然实际运算过程中可能会因为stack的特性而使同一层的两个 - 变成 +，记忆即可。可以用 1 * 2 - 3 / 4 + 5 * 6 - 7 * 8 + 9 / 10这个test case来debug看）
 */
public class BasicCalculatorIII {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Invalid Arguments provided.");
        }

        Map<Character, Integer> optrMap = setupOptrMap(); // priority map
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> optrStack = new Stack<>();

        int i = 0;
        int val = 0;
        optrStack.push('('); // Add a pair of parentheses at the beginning and end of the expression, to make sure the last operator will be used and calcualted.
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch <= '9' && ch >= '0') { // digits
                while (i < s.length() && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    val = val * 10 + s.charAt(i) - '0';
                    i++; // points to a non-digit character after while loop ends
                }
                numStack.push(val);
                val = 0;
            } else if (ch == '(') {
                optrStack.push(ch);
                i++;
            } else { // dealing with ) and operators: + - * / and
                if (ch == '-' && (i == 0 || s.charAt(i - 1) == '(')) { // dealing with leading -, e.g.: -1 + 1, (-1 + 1)
                    numStack.add(0);
                }
                addOptr(optrMap, numStack, optrStack, ch);
                i++;
            }
        }

        addOptr(optrMap, numStack, optrStack, ')');
        return numStack.peek();
    }

    // to add an operator in stack: +, -, *, /, calculate if necessary
    private void addOptr(Map<Character, Integer> optrMap, Stack<Integer> numStack, Stack<Character> optrStack, Character ch) {
        if (ch == ')') {
            while (optrStack.peek() != '(') {
                if (optrStack.peek() == null) {
                    throw new IllegalStateException("Expression Mismatch, missing '('");
                }
                calculate(numStack, optrStack);
            }
            optrStack.pop(); // remove the peek (
        } else { // operators: + - * /
            while (optrMap.containsKey(optrStack.peek())) { // as long as we have available operators, we calculate
                Character prev = optrStack.peek();
                // until the current operator has higher priority than ones in the stack。例如，遇到 + - 时肯定会引发之前 + - * / 的计算。遇到 * / 时会引发 * / 的计算
                // 这样保证了在遇到 ) 无脑运算的时候，总是可以从栈顶开始计算。注意这里必须是 > 而不能是 >=，具体原因看最开始的注释
                if (optrMap.get(ch) > optrMap.get(prev)) {
                    break;
                }

                calculate(numStack, optrStack);
            }
            optrStack.push(ch); // 最后再将operator push进stack
        }
    }

    private void calculate(Stack<Integer> numStack, Stack<Character> optrStack) {
        int operand2 = numStack.pop();
        int operand1 = numStack.pop();
        char operator = optrStack.pop();
        int res;
        switch (operator) {
            case '+': res = operand1 + operand2; break;
            case '-': res = operand1 - operand2; break;
            case '*': res = operand1 * operand2; break;
            case '/': res = operand1 / operand2; break;
            default: throw new IllegalArgumentException("Not supported operator");
        }
        numStack.push(res);
    }

    private Map<Character, Integer> setupOptrMap() {
        Map<Character, Integer> optrMap = new HashMap<>();
        optrMap.put('+', 1);
        optrMap.put('-', 1);
        optrMap.put('*', 2);
        optrMap.put('/', 2);

        return optrMap;
    }

    public static void main(String[] args) {
        BasicCalculatorIII tester = new BasicCalculatorIII();
        String test = "1*2-3/4+5*6-7*8+9/10";
        int res = tester.calculate(test);
        System.out.println(res);
    }
}
