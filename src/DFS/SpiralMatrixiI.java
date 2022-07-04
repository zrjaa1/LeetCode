package DFS;

/*
59. Spiral Matrix II
Medium

390

72

Favorite

Share
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */

/*
similar to Spiral Matrix. Use n to deal with the base case, use offset and bound to deal with the position.
n -> the size remaining
offset -> current base location
bound -> the right, bottom bound for this loop
Above 3 elements are necessary.
further opitimization  (not implemented): 用offset+n的方式来表示bound
 */
public class SpiralMatrixiI {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n <= 0) return res;
        int offset = 0;
        int cur = 1;
        int bound = n;
        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                res[offset][offset+i] = cur++;
            }
            for (int i = 0; i < n - 1; i++) {
                res[offset+i][bound-1] = cur++;
            }
            for (int i = 0; i < n - 1; i++) {
                res[bound-1][bound-1-i] = cur++;
            }
            for (int i = 0; i < n - 1; i++) {
                res[bound-1-i][offset] = cur++;
            }
            n = n-2;
            offset++;
            bound--;
        }
        if (n == 1) res[offset][offset] = cur;
        return res;
    }
}
