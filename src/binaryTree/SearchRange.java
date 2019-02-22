package binaryTree;
import java.util.List;
import java.util.ArrayList;

// *LintCode* #11
/*
 * Given a binary search tree and a range [k1, k2], return all elements in the given range.

Example
If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].

    20
   /  \
  8   22
 / \
4   12
 */

// *Thoughts*
// go left or right, possibly pruning.
// Time Complexity: Best case O(logn) (for a target), worst case O(n)
// Space Complexity: depends on the number of nodes in the range
public class SearchRange {
	/**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        if (root == null) return new ArrayList<Integer>() {};
        
        TreeNode cur = root;
        ArrayList res = new ArrayList<Integer>();
        if (cur.val > k1) searchRange(cur.left, k1, k2, res);
        if (cur.val >= k1 && cur.val <= k2) res.add(cur.val);
        if (cur.val < k2) searchRange(cur.right, k1, k2, res);
        return res;
    }
    
    private void searchRange(TreeNode root, int k1, int k2, ArrayList res) {
        if (root == null) return;
        
        TreeNode cur = root;
        if (cur.val > k1) searchRange(cur.left, k1, k2, res);
        if (cur.val >= k1 && cur.val <= k2) res.add(cur.val);
        if (cur.val < k2) searchRange(cur.right, k1, k2, res);
    }
}
