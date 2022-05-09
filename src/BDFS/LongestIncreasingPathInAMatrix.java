package BDFS;

/**
 * 329. Longest Increasing Path in a Matrix: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * Hard
 *
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */
public class LongestIncreasingPathInAMatrix {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        int max = 0;
        int[][] mem = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j, mem));
            }
        }
        return max;
    }

    // 无法连续填值，因此不能DP
    private int dfs(int[][] matrix, int x, int y, int[][] mem) {
        if (mem[x][y] > 0) return mem[x][y];
        int max = 0;
        for(int[] direction: DIRECTIONS) {
            int xx = x + direction[0];
            int yy = y + direction[1];
            // not crossing border
            if (xx >= 0 && xx < matrix.length && yy >= 0 && yy < matrix[0].length) {
                if (matrix[xx][yy] > matrix[x][y]) {
                    max = Math.max(max, dfs(matrix, xx, yy, mem));
                }
            }
        }
        mem[x][y] = max + 1;
        return max + 1;
    }
}
