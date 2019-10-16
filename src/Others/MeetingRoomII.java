package Others;
import java.util.*;

/*
253. Meeting Rooms II
Medium

1185

20

Favorite

Share
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1

 */

/*
Greedy 的思想，先按照 start time 排序；然后用一个minHeap 来记录room 当前的 end time，如果无法放下，则创建一个新的 room；
如果能放下，则放到当前 end time 最短的那里。
 */

class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
}

public class MeetingRoomII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1.start, i2.start));
        Queue<Integer> minHeap = new PriorityQueue<>((s1, s2) -> s1 - s2 < 0 ? -1 : 1);
        minHeap.offer(intervals[0].end);
        int res = 1;
        int n = intervals.length;
        for (int i = 1; i < n; i++) {
            // when we need to create a new room
            if (intervals[i].start >= minHeap.peek()) minHeap.poll();
            else res++;
            minHeap.offer(intervals[i].end);
        }
        return res;
    }
}
