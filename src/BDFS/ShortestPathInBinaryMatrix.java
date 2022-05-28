package BDFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1091. Shortest Path in Binary Matrix: https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * Medium
 *
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * Example 3:
 *
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 */

/**
 * 如果in-place操作允许的话，可以直接把0 -> 1来get rid of visited，需要跟面试官clarify是否是multi-thread environment
 */
public class ShortestPathInBinaryMatrix {
    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1,}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        Set<Integer> visited = new HashSet<>();
        visited.add(hashCode(0, 0));
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point cur = queue.poll();
                if (cur.x == m - 1 && cur.y == n - 1) {
                    return step;
                }
                for (int[] direction : DIRECTIONS) {
                    int xx = cur.x + direction[0];
                    int yy = cur.y + direction[1];
                    if (!outOfBound(xx, yy, m, n) && grid[xx][yy] == 0 && !visited.contains(hashCode(xx, yy))) {
                        visited.add(hashCode(xx, yy));
                        queue.offer(new Point(xx, yy));
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private int hashCode(int x, int y) {
        return x * 100 + y;
    }

    private boolean outOfBound(int x, int y, int m, int n) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return true;
        }

        return false;
    }
}
