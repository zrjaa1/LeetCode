package BDFS;
import PreDefinedClass.TreeNode;
import java.util.List;
import java.util.*;

/* 102
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */

/* Thoughts
 While loop outside, for loop (queue.size -> # of tree nodes in each level) times inside
 */
public class BinaryTreeLevelOrderTraverse {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int size = 0;
        TreeNode cur = root;
        while (q.size()!=0) {
            size = q.size();
            List<Integer> row = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                cur = q.poll();
                row.add(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(row);
        }
        return res;
    }
}
