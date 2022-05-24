package DP;

import java.util.LinkedList;
import java.util.List;

/**
 * 1762. Buildings With an Ocean View: https://leetcode.com/problems/buildings-with-an-ocean-view/
 * Medium
 *
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.
 *
 * The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 *
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: heights = [4,2,3,1]
 * Output: [0,2,3]
 * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 * Example 2:
 *
 * Input: heights = [4,3,2,1]
 * Output: [0,1,2,3]
 * Explanation: All the buildings have an ocean view.
 * Example 3:
 *
 * Input: heights = [1,3,2,4]
 * Output: [3]
 * Explanation: Only building 3 has an ocean view.
 *
 *
 * Constraints:
 *
 * 1 <= heights.length <= 105
 * 1 <= heights[i] <= 109
 */

public class BuildingWithAnOceanView {
    public int[] findBuildings(int[] heights) {
        List<Integer> res = new LinkedList<>();
        int max = heights[heights.length - 1];
        res.add(heights.length - 1); // 注意这里要直接加进去，因为loop从倒数第二个building开始
        for (int i = heights.length - 2; i >= 0; i--) {
            if (heights[i] > max) {
                res.add(0, i);
            }
            max = Math.max(max, heights[i]);
        }

        return res.stream().mapToInt(i -> i).toArray();
    }
}
