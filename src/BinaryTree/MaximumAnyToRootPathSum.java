package BinaryTree;

public class MaximumAnyToRootPathSum {
    public int maximumAnyToRootPathSum(TreeNode root) {
        if (root == null) return 0;
        int left = maximumAnyToRootPathSum(root.left);
        int right = maximumAnyToRootPathSum(root.right);
        int max = Math.max(left, right);
        return max > 0 ? max + root.val : root.val;
    }
}
