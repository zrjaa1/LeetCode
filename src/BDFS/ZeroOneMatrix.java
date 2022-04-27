package BDFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 542. 01 Matrix: https://leetcode.com/problems/01-matrix/
 * Medium
 *
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 * Example 2:
 *
 *
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 */
public class ZeroOneMatrix {
    private static int NOT_VISITED_VALUE = -1;
    public int[][] updateMatrix(int[][] mat) {
        // corner case
        if (mat.length == 0 || mat[0].length == 0) {
            return mat;
        }
        // put the start point into the BFS queue
        Queue<int[]> queue = new LinkedList<>();

        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else if (mat[i][j] == 1) {
                    mat[i][j] = NOT_VISITED_VALUE;
                }
            }
        }
        // BFS
        bfs(mat, queue);

        return mat;
    }

    private static void bfs(int[][] mat, Queue<int[]> queue) {
        // corner case
        if (queue == null || queue.size() == 0) {
            return;
        }

        int m = mat.length, n = mat[0].length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // left, right, up, down
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] current = queue.poll();
                int currentValue = mat[current[0]][current[1]];
                for (int[] direction: directions) {
                    int[] neighbor = new int[]{current[0] + direction[0], current[1] + direction[1]};
                    if (neighbor[0] >= 0 && neighbor[0] < m && neighbor[1] >= 0 && neighbor[1] < n && mat[neighbor[0]][neighbor[1]] == NOT_VISITED_VALUE) {
                        mat[neighbor[0]][neighbor[1]] = level + 1;
                        queue.add(neighbor);
                    }
                }
            }
            level++;
        }
    }
}
