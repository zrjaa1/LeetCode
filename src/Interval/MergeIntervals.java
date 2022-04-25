package Interval;
import Util.Interval;

import java.util.*;
/**
56. Merge Intervals: https://leetcode.com/problems/merge-intervals/
Medium

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
Accepted
318,498
Submissions
907,708
 */

/**
这道题学习的点: 1. sort的写法 2. 可以在for loop中修改i的值
 */

public class MergeIntervals {
    // sol1: use a separate result list
    public int[][] mergeSol1(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new LinkedList<>();
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] <= prev[1]) { // merge
                prev[1] = Math.max(cur[1], prev[1]);
            } else { // store prev in the res list
                res.add(prev);
                prev = cur;
            }
        }
        res.add(prev);
        return res.toArray(new int[res.size()][]);
    }

    // sol2: in place operation
    public List<Interval> mergeSol2(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
        for (int i = 0; i < intervals.size()-1; i++) {
            Interval cur = intervals.get(i);
            Interval next = intervals.get(i+1);
            if (next.start <= cur.end) {
                if (cur.end < next.end) cur.end = next.end;
                intervals.remove(i+1);
                i--;
            }
        }
        return intervals;
    }
}
