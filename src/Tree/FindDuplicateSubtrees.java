package Tree;

import java.util.*;
/**
 * 652. Find Duplicate Subtrees： https://leetcode.com/problems/find-duplicate-subtrees/
 * Medium
 *
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 * Example 2:
 *
 *
 * Input: root = [2,1,1]
 * Output: [[1]]
 * Example 3:
 *
 *
 * Input: root = [2,2,2,3,null,3,null]
 * Output: [[2,3],[3]]
 *
 *
 * Constraints:
 *
 * The number of the nodes in the tree will be in the range [1, 10^4]
 * -200 <= Node.val <= 200
 */

/**
 * Use the serialization to detect the duplicates.
 */

public class FindDuplicateSubtrees {
//    public class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//        TreeNode(int x) { val = x; }
//    }
    /**
     * O(n^2) solution, as hashing calculate is O(n)
     */
    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        postorder(root, new HashMap<>(), res);
        return res;
    }

    public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) return "#";
        String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        if (map.get(serial) == 2) res.add(cur);
        return serial;
    }

    /**
     * O(n) solution, optimized from solution 1. Use an Serialization String -> Id mapping so that hash function takes CONSTANT time.
     */

    int curId;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> serialToId = new HashMap<>();
        Map<Integer, Integer> idToCount = new HashMap<>();
        postOrder(root, serialToId, idToCount, res);
        return res;
    }

    // serialize the current subtree, record it and return the current subtree id
    private int postOrder(TreeNode cur, Map<String, Integer> serialToId, Map<Integer, Integer> idToCount, List<TreeNode> res) {
        if (cur == null) {
            return -1;
        }
        int leftId = postOrder(cur.left, serialToId, idToCount, res);
        int rightId = postOrder(cur.right, serialToId, idToCount, res);
        String serialize = leftId + "," + rightId + "," + cur.val; // use cur.val not curId here, as we'd like to detect the duplicate subtrees
        Integer prevId = serialToId.get(serialize);
        if (prevId != null) {
            int prevCount = idToCount.get(prevId);
            if (prevCount == 1) {
                res.add(cur);
            }
            idToCount.put(prevId, prevCount + 1);
            return prevId;
        } else {
            serialToId.put(serialize, curId);
            idToCount.put(curId, 1);
            return curId++;
        }
    }
}
