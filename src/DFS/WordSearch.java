package DFS;

/**
79. Word Search: https://leetcode.com/problems/word-search/
Medium

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
dfs，可以写得更简洁一些。因为无重复计算，所以不用pruning
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board[0] == null || board[0].length == 0 || word == null || word.length() == 0) {
            throw new IllegalArgumentException();
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean result = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                result |= dfs(board, visited, i, j, 0, word);
            }
        }
        return result;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int i, int j, int idx, String word) {
        if (idx == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || word.charAt(idx) != board[i][j]) {
            return false;
        }

        visited[i][j] = true;
        boolean result = dfs(board, visited, i + 1, j, idx + 1, word) ||
                dfs(board, visited, i - 1, j, idx + 1, word) ||
                dfs(board, visited, i, j + 1, idx + 1, word) ||
                dfs(board, visited, i, j - 1, idx + 1, word);

        visited[i][j] = false;

        return result;
    }
}
