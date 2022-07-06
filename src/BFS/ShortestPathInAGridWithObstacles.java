package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1293. Shortest Path in a Grid with Obstacles Elimination: https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 * Hard
 *
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 * Example 2:
 *
 *
 * Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
 * Output: -1
 * Explanation: We need to eliminate at least two obstacles to find such a walk.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 40
 * 1 <= k <= m * n
 * grid[i][j] is either 0 or 1.
 * grid[0][0] == grid[m - 1][n - 1] == 0
 */

/**
 * 1. 这道题需要改写hashCode()和equals()函数，否则Hashmap默认根据object的reference来查重
 * 2. BFS可以在poll()时判断退出条件，但在offer()时就设置visited
 */
public class ShortestPathInAGridWithObstacles {
    class Wrapper {
        int x;
        int y;
        int k;
        public Wrapper(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }

        @Override
        public int hashCode() {
            return (this.x + 1) * (this.y + 1) * this.k;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Wrapper) {
                Wrapper w = (Wrapper) o;
                return this.x == w.x && this.y == w.y && this.k == w.k;
            } else {
                return false;
            }
        }
    }

    public static final int[][] DIRECTIONS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int shortestPath(int[][] grid, int k) {
        int row = grid.length, column = grid[0].length;
        if (k >= row + column - 2) {
            return row + column - 2;
        }
        Queue<Wrapper> queue = new LinkedList<>();
        Wrapper start = new Wrapper(0, 0, k);
        queue.offer(start);
        Set<Wrapper> visited = new HashSet<>();
        visited.add(start);
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                Wrapper cur = queue.poll();
                if (cur.x == row - 1 && cur.y == column - 1) {
                    return step;
                }
                for (int[] direction : DIRECTIONS) {
                    int xx = cur.x + direction[0];
                    int yy = cur.y + direction[1];
                    if (xx >= 0 && xx < row && yy >= 0 && yy < column) {
                        Wrapper candidate = (grid[xx][yy] == 1) ? new Wrapper(xx, yy, cur.k - 1) : new Wrapper(xx, yy, cur.k);
                        if (!visited.contains(candidate) && candidate.k >= 0) {
                            queue.offer(candidate);
                            visited.add(candidate);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathInAGridWithObstacles tester = new ShortestPathInAGridWithObstacles();
        int[][] grid = {{0, 1, 1}, {1, 1, 1}, {1, 0, 0}};
        int k = 1;
        int res = tester.shortestPath(grid, k);
        System.out.println(res);
    }
}
