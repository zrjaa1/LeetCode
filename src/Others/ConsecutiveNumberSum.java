package Others;

/*
829. Consecutive Numbers Sum
Hard

160

199

Favorite

Share
Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:

Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3
Example 2:

Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
Example 3:

Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 */

/*
问题转化为，求等差数列的和是否为N
以 n 表示项数，x表示首项 -> N = (x + (x+n-1))*(n/2), 以nx = N - n * (n-1)/2
如果nx<0,代表x<0，那么直接break，后面n更大的情况，x只会更小，也不用再考虑了
我们以项数来遍历，项数已确定，那么首项就一定确定了，如果我们也不需要知道首项是多少，只需要确定是否存在(xn%n == 0)就可以。
算法是O(N^0.5)的复杂度，因为n在这个复杂度上
 */
public class ConsecutiveNumberSum {
    public int consecutiveNumbersSum(int N) {
        if (N <= 1) return N;
        int res = 0;
        // i - start point, n - number of integers in the sequence
        for (int n = 1; ;n++) {
            // n*(n-1)奇数*偶数，除以2不会有损失

            int xn = N- n * (n-1) / 2;
            if (xn <= 0) break;
            // 取模也不会有损失
            if (xn % n == 0) res++;
        }
        return res;
    }
}
