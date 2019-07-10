package BitMath;

/*

 */

/*
Thought:
Sol1: Math
This question is pretty straightforward.

Because all trailing 0 is from factors 5 * 2.

But sometimes one number may have several 5 factors, for example, 25 have two 5 factors, 125 have three 5 factors. In the n! operation, factors 2 is always ample. So we just count how many 5 factors in all number from 1 to n.

One line code:

 */
public class FactorialTrailingZeros {
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }

    public static void main(String[] args) {
        FactorialTrailingZeros tester = new FactorialTrailingZeros();
        int n = 13;
        tester.trailingZeroes(n);
    }
}
