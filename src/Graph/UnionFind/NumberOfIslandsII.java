package Graph.UnionFind;
import java.util.*;

/**
305. Number of Islands II：https://leetcode.com/problems/number-of-islands-ii/
Hard

A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */

/**
sol1: brute force, 对每次修改，直接调用LC 200 Number of Islands, 得到结果 -> O(k) * O(m*n) -> Time Limit Exceed
sol2: union find, RECOMMENDED, 这道题是典型的 union find，我们需要以O(1)的时间来 union/merge，O（logn)的时间来找到祖先
 */

public class NumberOfIslandsII {
    /** Sol1: Brute force DFS
     */
    public List<Integer> numIslands2Sol1(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0] == null || positions.length == 0) return res;
        boolean[][] area = new boolean[m][n];
        for (int i = 0; i < positions.length; i++) {
            boolean[][] visited = new boolean[m][n];
            int row = positions[i][0];
            int col = positions[i][1];
            area[row][col] = true;
            res.add(numberIslands(area, visited));
        }
        return res;
    }

    private int numberIslands(boolean[][] area, boolean[][] visited) {
        int count = 0;
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[0].length; j++) {
                if (!visited[i][j]) {
                    dfs(area, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(boolean[][] area, boolean[][] visited, int i, int j) {
        if (visited[i][j]) return;
        visited[i][j] = true;
        if (i-1 >= 0 && area[i-1][j]) dfs(area, visited, i-1, j);
        if (i+1 < area.length && area[i+1][j]) dfs(area, visited, i+1, j);
        if (j-1 >= 0 && area[i][j-1]) dfs(area, visited, i, j-1);
        if (j+1 < area[0].length && area[i][j+1]) dfs(area, visited, i, j+1);
    }


    /**
     * Sol2: RECOMMENDED, Union Find
     */
    static int[][] DIRECTIONS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // left, right, up, down
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m, n);
        List<Integer> res = new ArrayList<>();
        for (int[] position : positions) {
            int i = position[0], j = position[1];
            uf.insert(position[0], position[1]);
            for (int[] direction: DIRECTIONS) {
                int ii = i + direction[0];
                int jj = j + direction[1];
                if (ii < 0 || ii >= m || jj < 0 || jj >= n) continue;

                if (uf.exist(ii, jj) && !uf.find(i, j, ii, jj)) {
                    uf.union(i, j, ii, jj);
                }
            }
            res.add(uf.numUnions);
        }

        return res;
    }

    public static void main(String[] args) {
        NumberOfIslandsII tester = new NumberOfIslandsII();
        int m = 3, n = 3;
        int[][] positions = {{0,0},{0,1},{1,2},{2,1}};
        tester.numIslands2(m, n, positions);
    }
}
