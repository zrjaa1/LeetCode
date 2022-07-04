package DFS;

/*
695. Max Area of Island
Medium

1011

58

Favorite

Share
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
 */

/*
dfs, global visited
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j]) continue;
                res = Math.max(res, dfs(grid, visited, i, j));
            }
        }
        return res;
    }

    private int dfs(int[][] grid, boolean[][] visited, int i, int j) {
        if (grid[i][j] == 0) return 0;
        visited[i][j] = true;
        int left = 0, right = 0, up = 0, down = 0;
        if (i-1 >= 0 && !visited[i-1][j]) left = dfs(grid, visited, i-1, j);
        if (i+1 < grid.length && !visited[i+1][j]) right = dfs(grid, visited, i+1, j);
        if (j-1 >= 0 && !visited[i][j-1]) up = dfs(grid, visited, i, j-1);
        if (j+1 < grid[0].length && !visited[i][j+1]) down = dfs(grid, visited, i, j+1);
        return left+right+up+down+1;
    }
}
