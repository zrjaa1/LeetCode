package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//original thoughts: find the smallest bigger one, and find the biggest smaller one. 
//Time Complexity: O(n) + O(n) + O(k) = O(n)
//Space Complexity: O(n) (for bst list) + O(k) (for res list) = O(n)
/*
class ClosestBinarySearchTreeValueII {
 private void inOrderTraverse (TreeNode root, ArrayList<TreeNode> res) {
     if (root == null) return;
     
     inOrderTraverse(root.left, res);
     res.add(root);
     inOrderTraverse(root.right, res);
     return;
 }
 
 public List<Integer> closestKValues(TreeNode root, double target, int k) {
     if (root == null || k == 0) return null;
     
     ArrayList<TreeNode> bst = new ArrayList<TreeNode>();
     inOrderTraverse(root, bst);
     ArrayList<Integer> res = new ArrayList<Integer>();
     
     if (bst.size() == 1) {
         res.add(root.val);
         return res;
     }
     
     int left = 0;
     while (left < bst.size()-1 && bst.get(left).val < target) {
         left++;
     }
     
     int right = left;
     left--;
     
     while (res.size() < k) {
         if (left < 0) {
             res.add(bst.get(right++).val);
         } else if (right > bst.size() - 1) {
             res.add(bst.get(left--).val);
         } else {
             if (Math.abs(bst.get(left).val - target) < Math.abs(bst.get(right).val - target)) {
                 res.add(bst.get(left--).val);
             }
             else {
                 res.add(bst.get(right++).val);
             }
         }
     }
     return res;
 }
}
*/

// sol2: Use the thought of BST 2 sum 
class ClosestBinarySearchTreeValueII{
 private TreeNode inorderBST(Stack<TreeNode> s, boolean left) {
     if (s.empty()) return null;
     
     TreeNode res = s.pop();
     if (left) {
         if (res.left != null) {
             TreeNode cur = res.left;
             s.push(cur);
             while (cur.right != null) {
                 cur = cur.right;
                 s.push(cur);
             }
         }
     } else {
         if (res.right != null) {
             TreeNode cur = res.right;
             s.push(cur);
             while (cur.left != null) {
                 cur = cur.left;
                 s.push(cur);
             }
         }
     }
     return res;
 }
 
 public List<Integer> closestKValues(TreeNode root, double target, int k) {
     if (root == null || k == 0) return null;
     
     TreeNode cur = root;
     ArrayList<Integer> res = new ArrayList<Integer>();
     Stack<TreeNode> stackRight = new Stack<TreeNode>();
     Stack<TreeNode> stackLeft = new Stack<TreeNode>();
    
     while (cur != null) {
         if (cur.val >= target) {
             stackRight.push(cur);
             cur = cur.left;
         } else {
             stackLeft.push(cur);
             cur = cur.right;
         }
     }
     
     while (res.size() < k) {
         if (stackRight.empty()) {
             res.add(inorderBST(stackLeft, true).val);
         } else if (stackLeft.empty()) {
             res.add(inorderBST(stackRight, false).val);
         } else {
             if (Math.abs(stackRight.peek().val - target) < Math.abs(stackLeft.peek().val - target)) {
                 res.add(inorderBST(stackRight, false).val);
             } else {
                 res.add(inorderBST(stackLeft, true).val);
             }
         }
     }
     
     return res;
 }
}
