package Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 218. The Skyline Problem: https://leetcode.com/problems/the-skyline-problem/
 * Hard
 *
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 *
 *
 * Example 1:
 *
 *
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 * Example 2:
 *
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 *
 *
 * Constraints:
 *
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings is sorted by lefti in non-decreasing order.
 */
public class Skyline {
    class Endpoint implements Comparable<Endpoint> {
        int time;
        int height;
        boolean isStart;

        public Endpoint(int time, int height, boolean isStart) {
            this.time = time;
            this.height = height;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Endpoint that) { // TODO: Comparator
            if (this.time != that.time) {
                return this.time - that.time;
            } else if (this.isStart && that.isStart) {
                return that.height - this.height;
            } else if (!this.isStart && !that.isStart) {
                return this.height - that.height;
            } else {
                return this.isStart ? -1 : 1;
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new LinkedList<>();
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
            return res;
        }

        List<Endpoint> intervals = new ArrayList<>(buildings.length * 2);
        for (int[] building: buildings) {
            intervals.add(new Endpoint(building[0], building[2], true));
            intervals.add(new Endpoint(building[1], building[2], false));
        }
        Collections.sort(intervals);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (Endpoint endpoint: intervals) {
            if (endpoint.isStart) { // start endpoint
                if (maxHeap.isEmpty() || maxHeap.peek() < endpoint.height) {
                    res.add(Arrays.asList(endpoint.time, endpoint.height));
                }
                maxHeap.offer(endpoint.height);
            } else { // end endpoint
                maxHeap.remove(endpoint.height);
                if (maxHeap.isEmpty() || endpoint.height > maxHeap.peek()) {
                    res.add(Arrays.asList(endpoint.time, maxHeap.isEmpty() ? 0 : maxHeap.peek()));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Skyline tester = new Skyline();
        int[][] buildings = new int[][] {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> res = tester.getSkyline(buildings);
        System.out.println(res);
    }
}
