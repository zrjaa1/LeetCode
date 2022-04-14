package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 272. Closest Binary Search Tree Value II
 * Hard
 *
 * Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target. You may return the answer in any order.
 *
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,5,1,3], target = 3.714286, k = 2
 * Output: [4,3]
 * Example 2:
 *
 * Input: root = [1], target = 0.000000, k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104.
 * 0 <= Node.val <= 109
 * -109 <= target <= 109
 */
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

     // initialization
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

 // sol2 re-write in 2022: Use the thought of BST 2 sum
    public List<Integer> closestKValue(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }

        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();

        // initialization
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val >= target) {
                rightStack.push(cur);
                cur = cur.left;
            } else {
                leftStack.push(cur);
                cur = cur.right;
            }
        }

        // find k closest elements
        while (k-- > 0) {
            TreeNode left;
            TreeNode right;
            if (!leftStack.empty() && !rightStack.empty()) {
                left = leftStack.peek();
                right = rightStack.peek();
                if (Math.abs(left.val - target) <= Math.abs(right.val - target)) {
                    res.add(left.val);
                    leftMinus(leftStack);
                } else {
                    res.add(right.val);
                    rightPlus(rightStack);
                }
            } else if (leftStack.empty()) {
                right = rightStack.peek();
                res.add(right.val);
                rightPlus(rightStack);
            } else if (rightStack.empty()) {
                left = leftStack.peek();
                res.add(left.val);
                leftMinus(leftStack);
            } else {
                break;
            }
        }

        return res;
    }

    private void leftMinus(Stack<TreeNode> stack) {
        TreeNode cur = stack.pop();
        cur = cur.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
    }

    private void rightPlus(Stack<TreeNode> stack) {
        TreeNode cur = stack.pop();
        cur = cur.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }
}
