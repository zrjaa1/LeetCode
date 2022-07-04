package BFS;

import java.util.*;

/**
 * 417. Pacific Atlantic Water Flow: https://leetcode.com/problems/pacific-atlantic-water-flow/
 * Medium
 *
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Example 2:
 *
 * Input: heights = [[2,1],[1,2]]
 * Output: [[0,0],[0,1],[1,0],[1,1]]
 *
 *
 * Constraints:
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */

/**
 * Thought: BFS和DFS都可以做，但这里选用了BFS，可能是因为有水慢慢往四周淹的思想。
 */
public class PacificAtlanticWaterFlow {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // left, right, up, down

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0] == null || heights[0].length == 0) {
            return res;
        }

        boolean[][] pacificReachable = new boolean[heights.length][heights[0].length];
        boolean[][] atlanticReachable = new boolean[heights.length][heights[0].length];

        // starting from atlantic
        bfs(heights, pacificReachable, true);
        bfs(heights, atlanticReachable, false);

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void bfs(int[][] heights, boolean[][] reachable, boolean pacific) {
        // initialization
        int m = heights.length, n = heights[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (pacific) {
                queue.offer(new int[] {i, 0});
                reachable[i][0] = true;
            }
            else {
                queue.offer(new int[] {i, n - 1});
                reachable[i][n - 1] = true;
            }
        }

        for (int j = 0; j < n; j++) {
            if (pacific) {
                queue.offer(new int[] {0, j});
                reachable[0][j] = true;
            }
            else {
                queue.offer(new int[] {m - 1, j});
                reachable[m - 1][j] = true;
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                for (int[] direction: DIRECTIONS) {
                    int[] neighbor = new int[] {cur[0] + direction[0], cur[1] + direction[1]};
                    if (neighbor[0] >= 0 && neighbor[0] < m && neighbor[1] >= 0 && neighbor[1] < n) {
                        if (heights[cur[0]][cur[1]] <= heights[neighbor[0]][neighbor[1]] && !reachable[neighbor[0]][neighbor[1]]) {
                            reachable[neighbor[0]][neighbor[1]] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }
    }
}
