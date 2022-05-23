package BinarySearch;

/**
 * Lint Code 183 · Wood Cut: https://www.lintcode.com/problem/183/
 * Hard
 *
 * Description
 * Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.
 *
 * The unit of length is centimeter.The length of the woods are all positive integers,you couldn't cut wood into float length.If you couldn't get >= k pieces, return 0.
 *
 * Example
 * Example 1
 *
 * Input:
 * L = [232, 124, 456]
 * k = 7
 * Output: 114
 * Explanation: We can cut it into 7 pieces if any piece is 114cm long, however we can't cut it into 7 pieces if any piece is 115cm long.
 * Example 2
 *
 * Input:
 * L = [1, 2, 3]
 * k = 7
 * Output: 0
 * Explanation: It is obvious we can't make it.
 * Challenge
 * O(n log Len), where Len is the longest length of the wood.
 */

/**
 * Binary Search, similar to LC69 Sqrt(x).
 * Mistakes:
 * 1. start initial value should be 1, as 0 is not a valid value to search, and it might cause runtime exception dividing 0.
 */
public class WoodCut {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0 || k == 0) {
            return 0;
        }

        int maxLen = 0;
        for (int l: L) {
            maxLen = Math.max(maxLen, l);
        }
        int max = 0;
        int start = 1; // This is important, as 0 is not a valid value to search
        int end = maxLen;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (cut(L, mid) >= k) { // try to cut w/ larger length, go right
                start = mid + 1;
                max = Math.max(max, mid);
            } else { // go left
                end = mid - 1;
            }
        }
        return end == -1 ? 0 : end;
    }

    /**
     * @param L
     * @param len
     * @return How many pieces can we cut into with provided length
     * Assume that len is never 0.
     */
    private int cut(int[] L, int len) {
        int res = 0;
        for (int l: L) {
            res += l / len;
        }
        return res;
    }
}
