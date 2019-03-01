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
Sol 2: BFS,
 */
public class JumpGameII {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        if (n == 1) return 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int res = 0;
        while (q.size() != 0) {
            int size = q.peek();
            res++;
            while(size > 0) {
                int cur = q.poll();
                if (nums[cur] + cur >= n-1) return res;
                q.offer(cur + size);
                size--;
            }
        }
        return res;
    }

    public static void main() {
        JumpGameII tester = new JumpGameII();
        int[] nums = {2, 3, 1, 1, 4};
        tester.jump(nums);
    }
}
