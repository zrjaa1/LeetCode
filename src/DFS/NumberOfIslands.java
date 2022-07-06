package DFS;

/*
200. Number of Islands
Medium

2269

83

Favorite

Share
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
Accepted
314,298
Submissions
775,234
 */

/**
Thoughts:
 1. 可以用visited来记录走过的地方，看一共有多少个island。DFS return true or false
 2. 进一步空间上的优化：遇到1就以bfs/dfs的方式把它周围的1都变成0，count++, inplace操作
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j, m, n, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, int m, int n, char[][] grid) {
        grid[i][j] = '0';
        if (i+1 < m && grid[i+1][j] == '1') dfs(i+1, j, m, n, grid);
        if (i-1 > -1 && grid[i-1][j] == '1') dfs(i-1, j, m, n, grid);
        if (j+1 < n && grid[i][j+1] == '1') dfs(i, j+1, m, n, grid);
        if (j-1 > -1 && grid[i][j-1] == '1') dfs(i, j-1, m, n, grid);
    }
}
