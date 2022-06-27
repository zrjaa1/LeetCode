package Company.Google;

import java.util.*;

/**
 * 539. Minimum Time Difference: https://leetcode.com/problems/minimum-time-difference/
 * Medium
 *
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 *
 *
 * Example 1:
 *
 * Input: timePoints = ["23:59","00:00"]
 * Output: 1
 * Example 2:
 *
 * Input: timePoints = ["00:00","23:59","00:00"]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 2 <= timePoints.length <= 2 * 104
 * timePoints[i] is in the format "HH:MM".
 */

/**
 * sort to find the first and last in the array. Compare neighbor difference and first-last difference.
 */
public class MinimumTimeDifference {
    /**
     * Sol1: Sort using minHeap. O(nlogn)
     */
    public int findMinDifference(List<String> timePoints) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (String timePoint : timePoints) { // O(n)
            minHeap.offer(timeToInt(timePoint));
        }

        int first = minHeap.poll();
        int prev = first;
        int res = Integer.MAX_VALUE;
        while (!minHeap.isEmpty()) {
            int cur = minHeap.poll();
            res = Math.min(res, Math.abs(cur - prev));
            prev = cur;
        }

        res = Math.min(res, Math.abs(prev - first - 1440)); // key: to calculate the difference between last and first
        return res;
    }

    private int timeToInt(String time) {
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3, 5));
        return hours * 60 + minutes;
    }

    /**
     * Sol2: Sort using bucket. O(n + 1440) = O(n)
     */
    public int findMinDifference2(List<String> timePoints) {
        boolean[] mark = new boolean[24 * 60];
        for (String time : timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (mark[h * 60 + m]) return 0;
            mark[h * 60 + m] = true;
        }

        int prev = 0, min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (mark[i]) {
                if (first != Integer.MAX_VALUE) {
                    min = Math.min(min, i - prev);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }

        min = Math.min(min, (24 * 60 - last + first)); // key: to calculate the difference between last and first

        return min;
    }
}
