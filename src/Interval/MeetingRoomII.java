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

    /**
     * Follow up: 找到有overlap的时间段，返回最长的那个
     * 思路：
     *
     * 我目前的想法是用一个minHeap来获取当前最小的endTime，和一个maxHeap来获取当前最大的endTime：
     *
     * 1. 对于每个新的meeting，首先看minHeap中的peek是否 <= meeting.start，如果true的话，那么minHeap.poll()直到minHeap的peek和当前meeting的时间有overlap，或者minHeap为空为止。
     *    如果存在overlap，那么说明存在以meeting.startTime开始的overlap，进入第2步。如果minHeap已经为空还没找到overlap，那么直接将新的meeting放入minHeap和maxHeap，continue进行下一个meeting的遍历。
     * 2. 在已经确保有overlap，且以meeting.startTime开始的情况下，计算最大可能的overlap：
     *    overlap的长度为 Math.min(meeting.endTime, maxHeap.peek().endTime) - meeting.startTime。重复这个过程记录overlap最大的时间段即可。
     *
     * 之前想在minHeap.poll()的同时也要maxHeap.remove()同一meeting，后面感觉让其留在maxHeap中，也不会影响结果，因为需要删去的都是当前已经结束的meeting，肯定不会造成overlap。maxHeap.remove()是O（n）反而增加了整体的时间复杂度。
     * 整体的时间复杂度应为O(nlogk), n是input的size，k为最大房间数量
     */

    public static void main(String[] args) {
        MeetingRoomII tester = new MeetingRoomII();
        int[][] intervals = new int[][] {{0, 30}, {5, 10}, {15, 20}};
        int res = tester.minMeetingRoomsSol2(intervals);
        System.out.println(res);
    }
}
