package DP;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * My Solution: sort based on endTime, using dp. 关键点在于：在有多个endTime相同时，保证index最大的那个有最大的profit。这是由dp的顺序和逻辑决定的
 */
public class MaximumProfitInJobScheduling {
    class Endpoint {
        int start;
        int end;
        int profit;
        public Endpoint(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;
        Queue<Endpoint> minHeap = new PriorityQueue<>((e1, e2) -> e1.end - e2.end);
        int maxTime = 0;
        for (int i = 0; i < len; i++) {
            minHeap.offer(new Endpoint(startTime[i], endTime[i], profit[i]));
            maxTime = Math.max(maxTime, endTime[i]);
        }

        int[][] dp = new int[len][2];   // dp[i][0] - max profit, dp[i][1] - endTime, where i is the ith endpoint
                                        // 该数列有如下特性：1. sorted by dp[i][1] - endTime, 即dp[2][1] >= dp[1][1]，由minHeap保证了这一点
                                        // 2. sorted by dp[i][0] - profit, 即dp[2][0] >= dp[1][0]，由我们dp取max的逻辑保证了这一点
        for (int i = 0; i < len; i++) {
            Endpoint cur = minHeap.poll();
            int index = findFirstAvailableTime(dp, cur.start, i);
            int candidate1 = (index == -1 ? 0 : dp[index][0]) + cur.profit;
            int candidate2 = i - 1 >= 0 ? dp[i - 1][0] : 0;
            dp[i][0] = Math.max(candidate1, candidate2);
            dp[i][1] = cur.end;
        }
        return dp[len - 1][0];
    }

    private int findFirstAvailableTime(int[][] dp, int startTime, int curIndex) {
        if (startTime == 0) {
            return -1;
        }
        for (int i = curIndex - 1; i >= 0; i--) {
            if (startTime >= dp[i][1]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MaximumProfitInJobScheduling tester = new MaximumProfitInJobScheduling();
        int[] startTime = {4, 2, 4, 8, 2};
        int[] endTime = {5, 5, 5, 10, 8};
        int[] profit = {1, 2, 8, 10, 4};
        int res = tester.jobScheduling(startTime, endTime, profit);
        System.out.println(res);
    }
}
