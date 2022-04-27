package BDFS;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import BDFS.NestedListWeightSum.NestedInteger;

/**
 * 364. Nested List Weight Sum II: https://leetcode.com/problems/nested-list-weight-sum-ii/
 * Medium
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth. Let maxDepth be the maximum depth of any integer.
 *
 * The weight of an integer is maxDepth - (the depth of the integer) + 1.
 *
 * Return the sum of each integer in nestedList multiplied by its weight.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 8
 * Explanation: Four 1's with a weight of 1, one 2 with a weight of 2.
 * 1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
 * Example 2:
 *
 *
 * Input: nestedList = [1,[4,[6]]]
 * Output: 17
 * Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1.
 * 1*3 + 4*2 + 6*1 = 17
 *
 *
 * Constraints:
 *
 * 1 <= nestedList.length <= 50
 * The values of the integers in the nested list is in the range [-100, 100].
 * The maximum depth of any integer is less than or equal to 50.
 */
public class NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }

        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger nestedInteger: nestedList) {
            queue.offer(nestedInteger);
        }

        int res = 0, prevSum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            while (size-- > 0) {
                NestedInteger cur = queue.poll();
                if (cur.isInteger()) {
                    levelSum += cur.getInteger();
                } else {
                    for (NestedInteger next: cur.getList()) {
                        queue.offer(next);
                    }
                }
            }
            res += prevSum;
            res += levelSum;
            prevSum += levelSum;
        }

        return res;
    }
}
