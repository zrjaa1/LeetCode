package DP;

/*
130. Surrounded Regions
Medium

667

397

Favorite

Share
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.

Accepted
136,067
Submissions
611,869
 */

/*
Thoughts: 先检查边界的O，用dfs的方式把与它自己和所有相连的O变成Y，再遍历整个matrix，把仍然是O的变成X，把Y变回O
注意：这里检查边界的时候用了螺旋打印的方式，但螺旋打印不能handle只有一个元素的情况，所以稍作修改让第一次for loop从头到尾遍历而不是到尾-1
 */
public class NumberOfRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        int m = board.length, n = board[0].length;

        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 'X') continue;
            dfs(0,j,m,n,board);
        }
        for (int j = 1; j < board.length; j++) {
            if (board[j][n-1] == 'X') continue;
            dfs(j,n-1,m,n,board);
        }
        for (int j = 0; j < board[0].length-1; j++) {
            if (board[m-1][n-1-j] == 'X') continue;
            dfs(m-1,n-1-j,m,n,board);
        }
        for (int j = 0; j < board.length-1; j++) {
            if (board[m-1-j][0] == 'X') continue;
            dfs(m-1-j,0,m,n,board);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'Y') board[i][j] = 'O';
            }
        }
    }

    private void dfs(int i, int j, int m, int n, char[][] board) {
        board[i][j] = 'Y';
        if (i+1 < m && board[i+1][j] == 'O') dfs(i+1, j, m, n, board);
        if (i-1 >= 0 && board[i-1][j] == 'O') dfs(i-1, j, m, n, board);
        if (j+1 < n && board[i][j+1] == 'O') dfs(i, j+1, m, n, board);
        if (j-1 >= 0 && board[i][j-1] == 'O') dfs(i, j-1, m, n, board);
    }
}
