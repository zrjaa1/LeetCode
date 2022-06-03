package DP;

import java.util.HashMap;

/**
 * 818. Race Car: https://leetcode.com/problems/race-car/
 * Hard
 *
 * Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions. Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):
 *
 * When you get an instruction 'A', your car does the following:
 * position += speed
 * speed *= 2
 * When you get an instruction 'R', your car does the following:
 * If your speed is positive then speed = -1
 * otherwise speed = 1
 * Your position stays the same.
 * For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to 1 --> 2 --> 4 --> -1.
 *
 * Given a target position target, return the length of the shortest sequence of instructions to get there.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 3
 * Output: 2
 * Explanation:
 * The shortest instruction sequence is "AA".
 * Your position goes from 0 --> 1 --> 3.
 * Example 2:
 *
 * Input: target = 6
 * Output: 5
 * Explanation:
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0 --> 1 --> 3 --> 7 --> 7 --> 6.
 */
public class RaceCar {
    /**
     * DP, Optimal solution. BFS not optimal
     */
    public int racecar(int target) {
        int[] dp = new int[target + 1];

        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MAX_VALUE;

            int m = 1, j = 1;

            for (; j < i; j = (1 << ++m) - 1) { //
                for (int q = 0, p = 0; p < j; p = (1 << ++q) - 1) {
                    dp[i] = Math.min(dp[i], m + 1 + q + 1 + dp[i - (j - p)]);   // case 1: j < i, for each j < i, find any p < j and issue one reverse at j then another reverse at p. The final state is in
                                                                                // position == j - p. It takes m steps to reach j, 1 step to reverse, and q step to reach p, and then 1 step to reverse.
                }
            }

            dp[i] = Math.min(dp[i], m + (i == j ? 0 : 1 + dp[j - i])); // case 2: j == i and case 3: j > i(if we ever go beyond i, we will just reverse, there is no reason to go further at this point)
        }

        return dp[target];
    }

    /**
     * DFS, NOT WORKING as the stack overflow. The correct way is to use BFS instead. But BFS is also not optimal
     */
    class Wrapper {
        int cur;
        int speed;
        public Wrapper(int cur, int speed) {
            this.cur = cur;
            this.speed = speed;
        }

        @Override
        public int hashCode() {
            return cur * 10^5 + speed;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Wrapper) {
                Wrapper that = (Wrapper) o;
                return that.cur == this.cur && that.speed == this.speed;
            } else {
                return false;
            }
        }
    }
    public int racecarDFS(int target) {
        return dfs(target, 0, 1, new HashMap<>());
    }

    private int dfs(int target, int cur, int speed, HashMap<Wrapper, Integer> map) {
        if (cur == target) {
            return 0;
        }

        Wrapper visited = new Wrapper(cur, speed);
        Integer step = map.get(visited);
        if (step != null) {
            return step;
        }

        int stepA = cur + speed > 0 ? dfs(target, cur + speed, speed * 2, map) : Integer.MAX_VALUE;
        int stepR = dfs(target, cur, speed > 0 ? -1 : 1, map);

        map.put(visited, Math.min(stepA, stepR) + 1);
        return Math.min(stepA, stepR) + 1;
    }

    public static void main(String[] args) {
        RaceCar tester = new RaceCar();
        int target = 3;
        int res = tester.racecar(target);
        System.out.println(res);
    }
}
