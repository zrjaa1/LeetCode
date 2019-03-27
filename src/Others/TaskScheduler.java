package Others;
import java.util.*;

/*
621. Task Scheduler
Medium

1477

247

Favorite

Share
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */

/*
这道题最关键的点，是要keep每个task的frequency，并且能够从大到小排序
Heap能够存<Character, Integer>，可以wrapp成一个class，但最方便的是直接利用Map.Entry这个class（并且有q.addAll(map.entrySet())这样好用的API）
然后用一个list，来暂存每次拿出来的这些entry，把freq-1，然后再从list中放回到Heap里，这样就实现了每次的更新
时间复杂度：O(k）heapify放到Heap里，k是task的type，即有多少个entry，O（klogk)拿出来，再O（k)放回去，重复m次，m是单个task出现的最高frequency
          所以最后的时间复杂度应该是O（m*klogk)
空间复杂度：O（k) hashmap, O(k) heap, O(k） list
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        if (n == 0) return tasks.length;
        // Key - task type, Value - frequency
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) map.put(tasks[i], map.getOrDefault(tasks[i], 0)+1);
        // max heap, sort by frequency
        Queue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((s1, s2) -> s2.getValue() - s1.getValue() < 0 ? -1 : 1);
        q.addAll(map.entrySet());

        int count = 0;
        while (q.size() != 0) {
            int i = n+1; // each n+1 slots, we want to see exact type of task for <= 1 time.
            List<Map.Entry<Character, Integer>> list = new ArrayList<>();
            while (i>0 && q.size()!= 0) {
                Map.Entry<Character, Integer> top = q.poll();
                top.setValue(top.getValue()-1);
                list.add(top);
                i--;
                count++;
            }
            for (Map.Entry<Character, Integer> entry : list) {
                if (entry != null && entry.getValue() > 0) q.offer(entry);
            }
            if (q.isEmpty()) break;
            count += i; // add idle slots
        }
        return count;
    }
}