package DP;

/**
 * 1937. Maximum Number of Points with Cost: https://leetcode.com/problems/maximum-number-of-points-with-cost/
 * Medium
 *
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.
 *
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.
 *
 * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 *
 * Return the maximum number of points you can achieve.
 *
 * abs(x) is defined as:
 *
 * x for x >= 0.
 * -x for x < 0.
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
 * Output: 9
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
 * You add 3 + 5 + 3 = 11 to your score.
 * However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
 * Your final score is 11 - 2 = 9.
 * Example 2:
 *
 *
 * Input: points = [[1,5],[2,3],[4,2]]
 * Output: 11
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
 * You add 5 + 3 + 4 = 12 to your score.
 * However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
 * Your final score is 12 - 1 = 11.
 *
 *
 * Constraints:
 *
 * m == points.length
 * n == points[r].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 0 <= points[r][c] <= 105
 */

/**
 * The brute force way is for each row, we calculate from each possible position from last row, which takes O(n^3).
 * We notice some duplicates calculation in the above method, thus we introduced left and right. See the definition below to understand
 * O(T) = O(n^2)
 */
public class MaximumNumberOfPointsWithCost {
    public long maxPoints(int[][] points) {
        int row = points.length, col = points[0].length;
        long[] prev = new long[col]; // the maximum value we could achieve in the prev row
        long max = Long.MIN_VALUE;
        long left[] = new long[col]; // the maximum value we could achieve from left of the prev row(inclusive)
        long right[] = new long[col]; // the maximum value we could achieve from right of the prev row(inclusive)

        // initialization
        for (int j = 0; j < col; j++) {
            prev[j] = points[0][j];
            left[j] = j == 0 ? points[0][j] : Math.max(left[j - 1] - 1, points[0][j]);
            right[j] = j == col - 1 ? points[0][j] : Math.max(right[j + 1] - 1, points[0][j]);
            max = Math.max(max, points[0][j]);
        }

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) { // set left
                left[j] = j == 0 ? prev[j] : Math.max(left[j - 1] - 1, prev[j]); // key of the problem.
            }

            for (int j = col - 1; j >= 0; j--) { // set right
                right[j] = j == col - 1 ? prev[j] : Math.max(right[j + 1] - 1, prev[j]); // key of the problem, same as setting left.
            }

            for (int j = 0; j < col; j++) { // set prev and record max
                long candidate1 = j - 1 >= 0 ? left[j - 1] - 1 : Long.MIN_VALUE;
                long candidate2 = j + 1 < col ? right[j + 1] - 1: Long.MIN_VALUE;
                prev[j] = Math.max(prev[j], Math.max(candidate1, candidate2)) + points[i][j];
                max = Math.max(max, prev[j]);
            }
        }

        return max;
    }
}
