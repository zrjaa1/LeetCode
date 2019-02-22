package BinaryTree;

//�������Ҫ˼����binary search����Ҫע��������ս�����ܳ������߹���ÿһ���ڵ��С�

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
