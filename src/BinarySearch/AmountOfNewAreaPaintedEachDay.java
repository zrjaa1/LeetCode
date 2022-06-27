package BinarySearch;

import java.util.*;

/**
 * 2158. Amount of New Area Painted Each Day: https://leetcode.com/problems/amount-of-new-area-painted-each-day/
 * Hard
 *
 * There is a long and thin painting that can be represented by a number line. You are given a 0-indexed 2D integer array paint of length n, where paint[i] = [starti, endi]. This means that on the ith day you need to paint the area between starti and endi.
 *
 * Painting the same area multiple times will create an uneven painting so you only want to paint each area of the painting at most once.
 *
 * Return an integer array worklog of length n, where worklog[i] is the amount of new area that you painted on the ith day.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: paint = [[1,4],[4,7],[5,8]]
 * Output: [3,3,1]
 * Explanation:
 * On day 0, paint everything between 1 and 4.
 * The amount of new area painted on day 0 is 4 - 1 = 3.
 * On day 1, paint everything between 4 and 7.
 * The amount of new area painted on day 1 is 7 - 4 = 3.
 * On day 2, paint everything between 7 and 8.
 * Everything between 5 and 7 was already painted on day 1.
 * The amount of new area painted on day 2 is 8 - 7 = 1.
 * Example 2:
 *
 *
 * Input: paint = [[1,4],[5,8],[4,7]]
 * Output: [3,3,1]
 * Explanation:
 * On day 0, paint everything between 1 and 4.
 * The amount of new area painted on day 0 is 4 - 1 = 3.
 * On day 1, paint everything between 5 and 8.
 * The amount of new area painted on day 1 is 8 - 5 = 3.
 * On day 2, paint everything between 4 and 5.
 * Everything between 5 and 7 was already painted on day 1.
 * The amount of new area painted on day 2 is 5 - 4 = 1.
 * Example 3:
 *
 *
 * Input: paint = [[1,5],[2,4]]
 * Output: [4,0]
 * Explanation:
 * On day 0, paint everything between 1 and 5.
 * The amount of new area painted on day 0 is 5 - 1 = 4.
 * On day 1, paint nothing because everything between 2 and 4 was already painted on day 0.
 * The amount of new area painted on day 1 is 0.
 */

/**
 * TreeMap + Merge. Not sure if merge is necessary, but I imagine it could simplify things.
 * O(n * logk) where k is the maximum number of intervals in the treeMap.
 */
public class AmountOfNewAreaPaintedEachDay {
    public int[] amountPainted(int[][] paint) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // sort based on start
        int[] res = new int[paint.length];
        for (int i = 0; i < paint.length; i++) {
            // determine first left
            int left = paint[i][0];
            int start = paint[i][0];
            int end = paint[i][1];
            Map.Entry<Integer, Integer> leftEntry = treeMap.floorEntry(paint[i][0]);
            if (leftEntry != null && leftEntry.getValue() >= paint[i][0]) {
                left = leftEntry.getValue();
                start = leftEntry.getKey();
                treeMap.remove(start); // so that we can merge later
            }

            Map.Entry<Integer, Integer> rightEntry = treeMap.ceilingEntry(left);
            while (rightEntry != null && rightEntry.getKey() <= paint[i][1]) { // as long as there is overlap
                int right = rightEntry.getKey();
                res[i] += right - left;
                left = rightEntry.getValue();
                treeMap.remove(right);
                rightEntry = treeMap.ceilingEntry(left);
            }

            if (paint[i][1] > left) { // in case that there are some parts left, for example, [1, 4], [6,7], [5, 8]
                res[i] += paint[i][1] - left;
            }

            // merge
            end = Math.max(end, left);
            treeMap.put(start, end);
        }

        return res;
    }
}
