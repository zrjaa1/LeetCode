package BDFS;

import BinaryTree.TreeNode;
import java.util.List;
import java.util.*;

/* 314
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
 */

/* Thoughts
    use a hashmap to store <index, List<TreeNode>>, record the min max index at the same time. Then loop once to get the result list
    Warning: My solution is not completely correct since the solution want the sequence like BFS but I actually work as DFS way. However, the method should be the same
 */

class VerticalOrderTraverse {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;

        // ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>()
        HashMap<Integer, ArrayList<TreeNode>> map = new HashMap<>();
        int[] index = new int[2];
        index = dfsTraverse(root, 0, map, index[0], index[1]); // 0 based
        for (int i = index[0]; i <= index[1]; i++) {
            ArrayList<TreeNode> row = map.get(i);
            if (row != null) {
                ArrayList<Integer> rowList = new ArrayList<>();
                for (int j = 0; j < row.size(); j++) {
                    rowList.add(row.get(j).val);
                }
                res.add(rowList);
            }
        }
        return res;
    }

    private int[] dfsTraverse (TreeNode root, int index, HashMap<Integer, ArrayList<TreeNode>> res, int min, int max) {
        int[] array = new int[2];
        if (root == null) return new int[] {min, max};

        if (res.get(index) == null) {
            ArrayList<TreeNode> row = new ArrayList<>();
            row.add(root);
            res.put(index, row);
        } else {
            res.get(index).add(root);
        }
        // go left
        if (root.left != null) {
            min = (index-1 < min) ? index - 1: min;
            array = dfsTraverse(root.left, index - 1, res, min, max);
            min = (min < array[0]) ? min : array[0];
            max = (max > array[1]) ? max : array[1];
        }
        // go right
        if (root.right != null) {
            max = (index+1 > max) ? index+1 : max;
            array = dfsTraverse(root.right, index +1, res, min, max);
            min = (min < array[0]) ? min : array[0];
            max = (max > array[1]) ? max : array[1];
        }
        return new int[] {min, max};
    }
}
