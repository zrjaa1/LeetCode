package BDFS;
import PreDefinedClass.TreeNode;
import java.util.*;

/* 103 Binary Tree Zigzag Level Order Traversal
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */

/* Thoughts
similar to 102 binaryTreeLevelOrderTraverse, use a flag to know which level it is thus change the sequence.
Warning: Make sure where to use flag. (Sol1: when you add into the result list, Sol2: when you put nodes in the queue)
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int size = 0;
        TreeNode cur = root;
        int flag = 0;
        while (q.size()!=0) {
            size = q.size();
            List<Integer> row = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                cur = q.poll();
                if (flag % 2 == 0) row.add(cur.val);
                else row.add(0, cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(row);
            flag = flag % 2 + 1;
        }
        return res;
    }
}


