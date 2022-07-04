package Tree;

import java.util.HashSet;

/**
 * 1650. Lowest Common Ancestor of a Binary Tree III: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 * Medium
 *
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 */

/**
 * 1. 最简单的方式就是从一边看起，把所有的parent都放在一个set中，然后再找另一边，但worst case会很耗时
 * 2. 从两边同时出发，用两个set，可以average worst case
 */
public class LowestCommonAncestorInBinaryTreeIII {
    public Node lowestCommonAncestor(Node p, Node q) {
        return dfs(p, q, new HashSet<>(), new HashSet<>());
    }

    private Node dfs(Node p, Node q, HashSet<Node> pSet, HashSet<Node> qSet) {
        if (p == null && q == null) {
            return null;
        }

        if (p != null) {
            pSet.add(p);
        }
        if (q != null) {
            qSet.add(q);
        }

        if (pSet.contains(q)) {
            return q;
        }

        if (qSet.contains(p)) {
            return p;
        }

        return dfs(p == null ? p : p.parent, q == null ? q : q.parent, pSet, qSet);
    }
}
