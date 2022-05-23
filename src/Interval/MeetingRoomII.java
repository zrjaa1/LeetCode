package Interval;
import Util.Interval;

import java.util.*;

/**
253. Meeting Rooms II: https://leetcode.com/problems/meeting-rooms-ii/
Medium

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1

 */

/**
 * Both solutions are having the same logic
 * 1. Sol1: Greedy 的思想，先按照 start time 排序；然后用一个minHeap 来记录room 当前的 end time，如果无法放下，则创建一个新的 room；如果能放下，则放到当前 end time 最短的那里。
 * 2. Sol2: 把start和end都存起来，sort然后遍历。需要注意start和end时间相同时，要把end放在前面
 **/

public class MeetingRoomII {
    // sol1: minHeap, easier to understand and memorize. Record all the end times. O(n * logk)
    public int minMeetingRoomsSol1(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1.start, i2.start));
        Queue<Integer> minHeap = new PriorityQueue<>((s1, s2) -> s1 - s2 < 0 ? -1 : 1);
        minHeap.offer(intervals[0].end); // Record all the end times.
        int res = 1;
        int n = intervals.length;
        for (int i = 1; i < n; i++) {
            if (intervals[i].start >= minHeap.peek()) { // we can re-use the previous room
                minHeap.poll();
            }
            else {
                res++; // we have to create a new room
            }
            minHeap.offer(intervals[i].end);
        }
        return res;
    }

    class Endpoint {
        int time;
        boolean isStart;

        public Endpoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }

    /**
     * Sol2: Sort based on all endpoints (left and right), O(nlogn)
     * @param intervals
     * @return
     */
    public int minMeetingRoomsSol2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int res = 0, max = 0;
        List<Endpoint> list = new ArrayList<>(intervals.length);
        for (int i = 0; i < intervals.length; i++) {
            list.add(new Endpoint(intervals[i][0], true));
            list.add(new Endpoint(intervals[i][1], false));
        }
        Collections.sort(list, (a, b) -> { // if the time is same, we need the end time sorted before start time
            if (a.time != b.time) {
                return a.time - b.time;
            } else { //
                if (a.isStart || b.isStart) { // either a.isStart or b.isStart
                    return a.isStart ? 1 : -1;
                } else {
                    return -1;
                }
            }
        });

        for (int i = 0; i < list.size(); i++) {
            Endpoint endpoint = list.get(i);
            if (endpoint.isStart) {
                res++;
            } else {
                res--;
            }
            max = Math.max(max, res);
        }

        return max;
    }

    public static void main(String[] args) {
        MeetingRoomII tester = new MeetingRoomII();
        int[][] intervals = new int[][] {{0, 30}, {5, 10}, {15, 20}};
        int res = tester.minMeetingRoomsSol2(intervals);
        System.out.println(res);
    }
}
