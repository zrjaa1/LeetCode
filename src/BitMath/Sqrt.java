package BitMath;
import java.util.*;

/*
69. Sqrt(x)
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
 */

/*
Thought:
Sol1: Binary Search, type 2 (left == right)
Sol2: Bit Manipulation, decide the most significant first. Note: to avoid overflow, we need to use long type.
 */
public class Sqrt {
    public int mySqrt(int x) {
        if (x <= 1) return x;
        int bit = 1;
        int res = 0;
        // first, find the most significant bit
        while ((long)res * res <= x) {
            bit <<= 1;
            res = bit;
        }
        bit >>= 1;
        res = bit;
        bit >>= 1;
        while (bit != 0) {
            res |= bit;
            if ((long)res * res > x) res ^= bit;
            bit >>= 1;
        }
        return res;
    }
}
