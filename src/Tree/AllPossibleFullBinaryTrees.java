package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 894. All Possible Full Binary Trees: https://leetcode.com/problems/all-possible-full-binary-trees/
 * Medium
 *
 * Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.
 *
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * Example 2:
 *
 * Input: n = 3
 * Output: [[0,0,0]]
 *
 */
public class AllPossibleFullBinaryTrees {
    Map<Integer, List<TreeNode>> mem = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        // corner case
        if (n % 2 == 0 || n < 1) {
            return new ArrayList<TreeNode>();
        }
        if (!mem.containsKey(n)) {
            List<TreeNode> result = new ArrayList<>();
            if (n == 1) {
                result.add(new TreeNode(0));
            } else if (n % 2 == 1) {
                for (int i = 0; i < n; i++) {
                    List<TreeNode> lefts = allPossibleFBT(n);
                    List<TreeNode> rights = allPossibleFBT(n - 1 - i);
                    for (TreeNode left: lefts) {
                        for (TreeNode right: rights) {
                            TreeNode root = new TreeNode(0);
                            root.left = left;
                            root.right = right;
                            result.add(root);
                        }
                    }
                }
            }
            mem.put(n, result);
        }
        return mem.get(n);
    }
}
