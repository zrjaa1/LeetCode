package DP;
import java.util.*;

/*
45. Jump Game II
Hard

959

45

Favorite

Share
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */

/*
Sol 1: DFS, 但时间过不了
Sol 2: BFS, 时间还是过不了
Sol 3: DP, 从右往左填
Sol 4: Greedy
 */
public class JumpGameII {
    // sol 2
    /*
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        if (n == 1) return 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int res = 0;
        while (q.size() != 0) {
            int size = q.size();
            res++;
            while(size-- > 0) {
                int cur = q.poll();
                for (int i = 1; i <= nums[cur]; i++) {
                    if (cur + i >= n - 1) return res;
                    q.offer(cur + i);
                }
            }
        }
        return res;
    }
    */

    // sol 3
    /*
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int[] dp = new int[n];
        dp[n-1] = 0;
        for (int i = n-2; i>=0; i--) {
            // 注意这里需要用max-1，因为后面的dp[i] = min+1可能导致溢出。
            int min = Integer.MAX_VALUE-1;
            for (int j = 1; j <= nums[i]; j++) {
                if (i+j > n-1) break;
                min = Math.min(dp[i+j], min);
            }
            dp[i] = min+1;
        }
        return dp[0];
    }
    */

    // sol 4
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int maxRange = 0;
        int cur = 0;
        int step = 0;
        while (maxRange < n-1) {
            int newMax = 0;
            for (int i = cur; i <= maxRange; i++) {
                newMax = Math.max(newMax, i+nums[i]);
            }
            cur = maxRange+1;
            maxRange = newMax;
            step++;
        }
        return step;
    }

    public static void main() {
        JumpGameII tester = new JumpGameII();
        int[] nums = {2, 3, 1, 1, 4};
        tester.jump(nums);
    }
}
