package Company.Uber;

import java.util.*;

/**
 * // Code a system that can schedule meetings in a predefined set of conference rooms.
 * // It should have a method like scheduleMeeting(timeRange) which returns any available room at that time and reserves it or an error if no rooms are available.
 *
 * // 3 rooms, n
 * // schedule  =  [
 * //   (5,7), 0
 * //   (7,9), 0
 * //   (8,12), 1
 * //   (10,11), 0
 * //   (10,11), 2
 * //   (10,15),
 * //   (13,14),
 * //   (14,16)
 * //   (11,12),
 * // ]
 * // scheduleMeeting(schedule, 3)
 *
 * Need to clarify: Any priority
 */

public class ScheduleMeetings {
    static class TimeRange {
        int start;
        int end;

        public TimeRange(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class Wrapper {
        int end;
        int roomNum;

        public Wrapper(int end, int roomNum) {
            this.end = end;
            this.roomNum = roomNum;
        }
    }

    // O(T) = O(n * logn) + (n * logk) + (k * n) = O(nlogn + kn )
    // O(S) = O(k) for availablity list and minHeap
    public List<Integer> scheduleMeeting(List<TimeRange> schedules, int numOfRooms) {
        // TODO : Corner case

        List<Integer> res = new ArrayList<>();
        Collections.sort(schedules, (a, b) -> a.start - b.start); // n*logn, n is schedules.size();
        PriorityQueue<Wrapper> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end); // O(S) = O(k)
        minHeap.offer(new Wrapper(schedules.get(0).end, 0));
        res.add(0);
        List<Integer> available = new LinkedList<>();

        for (int i = 1; i < schedules.size(); i++) { // O(n * logk) + O(k * n) amortized
            int startTime = schedules.get(i).start;
            if (startTime < minHeap.peek().end) {
                if (minHeap.size() >= numOfRooms) {
                    throw new IllegalArgumentException("Not avaialble");
                } else {
                    while (!minHeap.isEmpty() && startTime < minHeap.peek().end) { // O(k)
                        updateHeapAndAvailability(minHeap, available); // O(n)
                    }
                    available.remove(0);
                    res.add(available.get(0));
                }
            } else { // re-use the peek room: 1. pop out from minHeap
                Wrapper prevRoom = minHeap.poll(); // O(logk)
                minHeap.offer(new Wrapper(schedules.get(i).end, prevRoom.roomNum));
                res.add(prevRoom.roomNum);
            }
        }

        return res;
    }

    private void updateHeapAndAvailability(PriorityQueue<Wrapper> minHeap, List<Integer> available) {
        Wrapper prevRoom = minHeap.poll();
        available.add(prevRoom.roomNum);
    }

    // Solution 2: Instead of using an available list, we can actually put everything as long as we have a good initialization of minHeap.
    // O(T) = O(n * logk + n * logn), the for loop runs n times, and each time it takes O(logk) to poll() and offer() in worst case. The sort takes O(nlogn)
    // O(S) = O(k) for the minHeap

    // Note that this class is used in case that the input is given with order(we need to clarify this，但很可能不需要)
    class TimeRangeWithIndex {
        int start;
        int end;
        int index;
        public TimeRangeWithIndex(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }
    public List<Integer> scheduleMeetingII(List<TimeRange> schedules, int numOfRooms) {
        PriorityQueue<Wrapper> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end); // O(S) = O(k)
        for (int i = 0; i < numOfRooms; i++) { // O(k) for heapify
            minHeap.offer(new Wrapper(0, i)); // Assume we have k rooms occupied with endTime set to 0
        }

        List<TimeRangeWithIndex> schedulesWithIndex = new ArrayList<>();
        for (int i = 0; i < schedules.size(); i++) {
            TimeRange cur = schedules.get(i);
            schedulesWithIndex.add(new TimeRangeWithIndex(cur.start, cur.end, i));
        }
        Collections.sort(schedulesWithIndex, (a, b) -> a.start - b.start); // n*logn, n is schedules.size();

        List<Integer> res = new ArrayList<>(schedules.size());
        for (int i = 0; i < schedules.size(); i++) {
            res.add(-1);
        }
        for (int i = 0; i < schedulesWithIndex.size(); i++) {
            int startTime = schedulesWithIndex.get(i).start;
            if (startTime < minHeap.peek().end) {
                throw new IllegalArgumentException("No Available Room");
            } else {
                Wrapper prevRoom = minHeap.poll();
                minHeap.offer(new Wrapper(schedulesWithIndex.get(i).end, prevRoom.roomNum));
                res.set(schedulesWithIndex.get(i).index, prevRoom.roomNum);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ScheduleMeetings tester = new ScheduleMeetings();
        List<TimeRange> timeRanges = new ArrayList<>();
        timeRanges.add(new TimeRange(5, 7));
        timeRanges.add(new TimeRange(7, 9));
        timeRanges.add(new TimeRange(8, 12));
        timeRanges.add(new TimeRange(9, 12));
        timeRanges.add(new TimeRange(10, 11));
        timeRanges.add(new TimeRange(9, 10));
        // timeRanges.add(new TimeRange(8, 10)); // adding one more TimeRange causes exception as 3 rooms are occupied with (8, 12) (9, 12) (9,10)

        List<Integer> res = tester.scheduleMeetingII(timeRanges, 3);
        System.out.println(res);
    }
}
