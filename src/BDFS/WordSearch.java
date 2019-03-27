package BDFS;

/*
79. Word Search
Medium

1552

73

Favorite

Share
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */

/*
dfs，可以写得更简洁一些
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || word == null) return false;
        if (word.length() == 0) return true;
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean res = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    res |= findPath(board, visited, word, i, j, 1);
                    if (res == true) return res;
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean findPath(char[][] board, boolean[][] visited, String word, int i, int j, int index) {
        if (index >= word.length()) return true;
        int m = board.length, n = board[0].length;
        boolean res = false;
        // left, right, up, down
        if (i-1 >= 0 && board[i-1][j] == word.charAt(index) && visited[i-1][j] == false)         {
            visited[i-1][j] = true;
            res |= findPath(board, visited, word, i-1, j, index+1);
            if (res == true) return res;
            visited[i-1][j] = false;
        }
        if (i+1 < m && board[i+1][j] == word.charAt(index) && visited[i+1][j] == false)         {
            visited[i+1][j] = true;
            res |= findPath(board, visited, word, i+1, j, index+1);
            if (res == true) return res;
            visited[i+1][j] = false;
        }
        if (j-1 >= 0 && board[i][j-1] == word.charAt(index) && visited[i][j-1] == false)         {
            visited[i][j-1] = true;
            res |= findPath(board, visited, word, i, j-1, index+1);
            if (res == true) return res;
            visited[i][j-1] = false;
        }
        if (j+1 < n && board[i][j+1] == word.charAt(index) && visited[i][j+1] == false)         {
            visited[i][j+1] = true;
            res |= findPath(board, visited, word, i, j+1, index+1);
            if (res == true) return res;
            visited[i][j+1] = false;
        }
        return res;
    }
}
