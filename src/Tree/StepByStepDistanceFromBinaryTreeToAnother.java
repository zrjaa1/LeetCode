package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 2096. Step-By-Step Directions From a Binary Tree Node to Another: https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
 * Medium
 *
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 * Example 2:
 *
 *
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 2 <= n <= 105
 * 1 <= Node.val <= n
 * All the values in the tree are unique.
 * 1 <= startValue, destValue <= n
 * startValue != destValue
 */

/**
 * My Solution: RECOMMENDED 1. find the lowest common ancesor 2. find the paths: root -> start, root -> end 3. replace the start to "U"
 * Alternative Solution: (not implemented)
 * 1. Build directions for both start and destination from the root.
 * 2. Remove common prefix path. We remove "L", and now start direction is "LRRL", and destination - "RR"
 * 3. Replace all steps in the start direction to "U" and add destination direction.
 */
public class StepByStepDistanceFromBinaryTreeToAnother {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        List<String> res = new ArrayList<>(); // res.get(0): root -> startValue; res.get(1): root -> destValue
        TreeNode ancestor = lowestCommonAncestor(root, startValue, destValue);
        dfs(ancestor, startValue, new StringBuilder(), res);
        dfs(ancestor, destValue, new StringBuilder(), res);

        if (res.size() != 2) {
            throw new IllegalArgumentException("one of values does not exist in the tree");
        }

        String resStart = res.get(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resStart.length(); i++) {
            sb.append('U');
        }

        return sb.toString() + res.get(1);
    }

    private void dfs(TreeNode cur, int value, StringBuilder sb, List<String> res) {
        if (cur == null) {
            return;
        }

        if (cur.val == value) {
            res.add(sb.toString());
            return;
        }

        int len = sb.length();

        sb.append('L');
        dfs(cur.left, value, sb, res);
        sb.setLength(len);

        sb.append('R');
        dfs(cur.right, value, sb, res);
        sb.setLength(len);
    }

    // return a TreeNode that contains either p or q. return null if not containing any of them.
    private TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
}
