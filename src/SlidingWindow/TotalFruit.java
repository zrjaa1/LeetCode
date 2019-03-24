package SlidingWindow;
import java.util.*;
/*
904. Fruit Into Baskets
Medium

359

516

Favorite

Share
In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?



Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.


Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length
 */

/*
sliding window
 */
public class TotalFruit {
    public int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0) return 0;
        int n = tree.length;
        if (n <= 2) return n;
        int slow = 0;
        int fast = 0;
        int res = Integer.MIN_VALUE;
        // key = type of fruit, value = frequency;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (fast < n) {
            int cur = tree[fast];
            if (!map.containsKey(cur)) {
                map.put(cur, 1);
            } else {
                map.put(cur, map.get(cur)+1);
            }
            while (map.size() > 2) {
                int temp = tree[slow++];
                int freq = map.get(temp);
                if (freq == 1) map.remove(temp);
                else map.put(temp, freq-1);
            }
            res = Math.max(res, fast-slow+1);
            fast++;
        }
        return res;
    }
}
