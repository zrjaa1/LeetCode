package Company.Google;

import java.util.*;

/**
 * 1882. Process Tasks Using Servers: https://leetcode.com/problems/process-tasks-using-servers/
 * Medium
 *
 * You are given two 0-indexed integer arrays servers and tasks of lengths n and m respectively. servers[i] is the weight of the ith server, and tasks[j] is the time needed to process the jth task in seconds.
 *
 * Tasks are assigned to the servers using a task queue. Initially, all servers are free, and the queue is empty.
 *
 * At second j, the jth task is inserted into the queue (starting with the 0th task being inserted at second 0). As long as there are free servers and the queue is not empty, the task in the front of the queue will be assigned to a free server with the smallest weight, and in case of a tie, it is assigned to a free server with the smallest index.
 *
 * If there are no free servers and the queue is not empty, we wait until a server becomes free and immediately assign the next task. If multiple servers become free at the same time, then multiple tasks from the queue will be assigned in order of insertion following the weight and index priorities above.
 *
 * A server that is assigned task j at second t will be free again at second t + tasks[j].
 *
 * Build an array ans of length m, where ans[j] is the index of the server the jth task will be assigned to.
 *
 * Return the array ans.
 *
 *
 *
 * Example 1:
 *
 * Input: servers = [3,3,2], tasks = [1,2,3,2,1,2]
 * Output: [2,2,0,2,1,2]
 * Explanation: Events in chronological order go as follows:
 * - At second 0, task 0 is added and processed using server 2 until second 1.
 * - At second 1, server 2 becomes free. Task 1 is added and processed using server 2 until second 3.
 * - At second 2, task 2 is added and processed using server 0 until second 5.
 * - At second 3, server 2 becomes free. Task 3 is added and processed using server 2 until second 5.
 * - At second 4, task 4 is added and processed using server 1 until second 5.
 * - At second 5, all servers become free. Task 5 is added and processed using server 2 until second 7.
 * Example 2:
 *
 * Input: servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
 * Output: [1,4,1,4,1,3,2]
 * Explanation: Events in chronological order go as follows:
 * - At second 0, task 0 is added and processed using server 1 until second 2.
 * - At second 1, task 1 is added and processed using server 4 until second 2.
 * - At second 2, servers 1 and 4 become free. Task 2 is added and processed using server 1 until second 4.
 * - At second 3, task 3 is added and processed using server 4 until second 7.
 * - At second 4, server 1 becomes free. Task 4 is added and processed using server 1 until second 9.
 * - At second 5, task 5 is added and processed using server 3 until second 7.
 * - At second 6, task 6 is added and processed using server 2 until second 7.
 *
 *
 * Constraints:
 *
 * servers.length == n
 * tasks.length == m
 * 1 <= n, m <= 2 * 105
 * 1 <= servers[i], tasks[j] <= 2 * 105
 *
 */

/**
 * Two Heaps, one for available and one for used.
 * 1. When a new task comes in and some tasks in used queue are finished, put that server back into available queue first
 * then pick one from the top
 * 2. When no new task, use the first one that in the used heap.
 */
public class ProcessTasksUsingServers {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> (a[0] != b[0]) ? (a[0] - b[0]) : (a[1] - b[1]));
        PriorityQueue<int[]> usedQueue = new PriorityQueue<>((a, b)->(a[2] != b[2]) ? (a[2] - b[2]) : ((a[0] != b[0]) ? (a[0] - b[0]) : (a[1] - b[1]))); // if the end tiem ties, we prioritize using server index as well.
        int n = servers.length;
        int m = tasks.length;
        //O(n)
        for (int i = 0; i < n; i++) {
            freeServers.add(new int[] {servers[i], i, 0});
        }
        int[] res = new int[m];
        //O(m * Logn)
        for (int i = 0; i < m; i++) {
            int t = tasks[i];
            while (!usedQueue.isEmpty() && usedQueue.peek()[2] <= i) { // When a new task comes in and some tasks in used queue are finished, put that server back into available queue first
                freeServers.offer(usedQueue.poll());
            }

            if (freeServers.isEmpty()) { // wait for used servers
                int[] cur = usedQueue.poll();
                res[i] = cur[1];
                cur[2] += t; // next end time + t
                usedQueue.offer(cur);
            } else { // use the first one in the available queue
                int[] cur = freeServers.poll();
                res[i] = cur[1];
                cur[2] = i + t; // cur time + t
                usedQueue.offer(cur);
            }
        }
        return res;
    }
}
