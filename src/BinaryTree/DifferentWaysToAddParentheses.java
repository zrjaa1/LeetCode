package BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 241. Different Ways to Add Parentheses: https://leetcode.com/problems/different-ways-to-add-parentheses/
 * Medium
 *
 * Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: expression = "2-1-1"
 * Output: [0,2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 *
 * Input: expression = "2*3-4*5"
 * Output: [-34,-14,-10,-10,10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 *
 * Constraints:
 *
 * 1 <= expression.length <= 20
 * expression consists of digits and the operator '+', '-', and '*'.
 * All the integer values in the input expression are in the range [0, 99].
 *
 */

/**
 * Notes
 * 1. Branching of which operator is the root of current level.
 * 2. 2 methods to remove parentheses: Convert to Syntax Tree, Reverse Polish notation(4 * 5 - 3 as 4 5 * 3 -)
 * 3. The different structure of Syntax Tree represents the calculation sequence of given numbers(leaves) and operators(non-leaf nodes)
 */
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                // branching: which operator is the root of current level
                List<Integer> lefts = diffWaysToCompute(expression.substring(0, i));
                List<Integer> rights = diffWaysToCompute(expression.substring(i + 1, expression.length()));
                for (Integer left: lefts) {
                    for (Integer right: rights) {
                        Integer result = calculate(left, right, c);
                        results.add(result);
                    }
                }
            }
        }
        if (results.size() == 0) {
            return Collections.singletonList(Integer.parseInt(expression));
        }
        return results;
    }

    private Integer calculate(Integer num1, Integer num2, char operator) {
        if (operator == '+') {
            return num1 + num2;
        } else if (operator == '-') {
            return num1 - num2;
        } else if (operator == '*') {
            return num1 * num2;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
