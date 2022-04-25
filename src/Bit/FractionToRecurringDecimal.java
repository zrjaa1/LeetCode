package Bit;
import java.util.*;

/*
166. Fraction to Recurring Decimal
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"
 */

/*
Thoughts:
follow basic math knowledge. Use HashMap to store the remaining value to handle the recurring.
 */
public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        sb.append(((numerator > 0) ^ (denominator > 0)) ? '-' : "");
        long num = Math.abs((long)numerator); // warning: we should put the (long) before numerator, not Math.abs, since the Math.abs(Integer.MIN_VALUE) is still Integer.MIN_VALUE;
        long den = Math.abs((long)denominator);
        long res = num%den;
        sb.append(num/den);
        // if the result is an integer
        if (res == 0) return sb.toString();
        // if the result is not an integer
        sb.append('.');
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(res, sb.length());
        while (res != 0) {
            res *= 10;
            sb.append(res/den);
            res %= den;
            if (map.containsKey(res)) {
                int index = map.get(res);
                sb.insert(index, '(');
                sb.append(')');
                break;
            } else {
                map.put(res, sb.length());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FractionToRecurringDecimal tester = new FractionToRecurringDecimal();
        int numerator = -1;
        int denominator = -2147483648;
        tester.fractionToDecimal(numerator, denominator);
    }


}
