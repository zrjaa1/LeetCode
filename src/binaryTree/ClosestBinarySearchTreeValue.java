package binaryTree;

//这道题主要思想是binary search，需要注意的是最终结果可能出现在走过的每一个节点中。

class ClosestBinarySearchTreeValue {
 public int closestValue(TreeNode root, double target) {
     if (root == null) return Integer.MIN_VALUE;
     
     int res = root.val;
     TreeNode cur = root;
     // like DFS
     while(cur != null) {
         if (Math.abs((double)res - target) > Math.abs((double)cur.val - target)) res = cur.val;
         if (cur.val <= (int)target) cur = cur.right;
         else cur = cur.left;
     }
     return res;
 }
}
