package Tree;

/**
 * 96. Unique Binary Search Trees: https://leetcode.com/problems/unique-binary-search-trees/
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 5
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 19
 */

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return dfs(1, n, new int[n][n]);
    }

    private int dfs(int left, int right, int[][] mem) {
        if (left > right) {
            return 0;
        }

        if (left == right) {
            return 1;
        }

        if (mem[left - 1][right - 1] != 0) {
            return mem[left - 1][right - 1];
        }

        int res = 0;
        for (int i = left; i <= right; i++) {
            int numLeft = dfs(left, i - 1, mem);
            int numRight = dfs(i + 1, right, mem);
            int num = numLeft * numRight;
            if (numLeft == 0 && numRight != 0) {
                num = numRight;
            } else if (numRight == 0 && numLeft != 0) {
                num = numLeft;
            }
            res += num;
        }
        mem[left - 1][right - 1] = res;
        return res;
    }
}
