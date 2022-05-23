package SlidingWindow;

/**
 * 134. Gas Station: https://leetcode.com/problems/gas-station
 * Medium
 *
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
 *
 *
 *
 * Example 1:
 *
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * Example 2:
 *
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 *
 * Constraints:
 *
 * n == gas.length == cost.length
 * 1 <= n <= 105
 * 0 <= gas[i], cost[i] <= 104
 */

/**
 * Sol1: Intuitive way, one pass. (recommended, as it is easier to explain)
 * Here is my understanding:
 *
 *   1. The problem stated that if there is a valid starting point, then it is unique, this is important.
 *   2. The intuition of the solution is trying to find the first starting point that could help us gain gas, since we made sure that curr_tank is always >= 0 starting from this point.
 *   3. Let's say the solution is N1 and we have N2 after N1, assume that starting from N2, we could also gain gas till point with index 0. The N2 won't be better than N1 because from N1 -> N2, the curr_tank >= 0, which means we are at least not losing any gas. And since we have a unique starting point, we can ensure that the first point that keeps curr_tank >= 0, which is N1, is the solution.
 * Sol2: Sliding window (not easy to understand, memorize it if necessary)
 */
public class GasStation {
    // Sol1, O(T) = O(n), O(S) = O(1)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) {
            return -1;
        }

        int sum = 0, curTank = 0;
        int res = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            if (curTank < 0) { // use it as the new starting point
                curTank = 0;
                res = i;
            }
            curTank += gas[i] - cost[i];
        }
        return sum >= 0 ? res : -1;
    }

    /** Sol2 Sliding window
     *  It's hard for me to understand why we choose to start-- when the circle is invalid, but start++ does not work, so just memorize it for now
     */
    public int canCompleteCircuitSol2(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) {
            return -1;
        }

        int mid = gas.length/2;
        int sum = gas[mid] - cost[mid];
        int start = mid, end = mid;
        while (calculateDistance(start, end, gas.length) < gas.length) { // we are trying to build up a circle that is valid
            if (sum < 0) { // invalid circle
                start--;
                if (start < 0) {
                    start = gas.length - 1;
                }
                sum += gas[start] - cost[start];
            } else { // valid circle
                end++;
                if (end >= gas.length) {
                    end = 0;
                }
                sum += gas[end] - cost[end];
            }
        }
        return sum >= 0 ? start % gas.length : -1;
    }

    private int calculateDistance(int start, int end, int len) {
        if (start <= end) {
            return end - start + 1;
        } else { // start > end
            return end + len - start + 1;
        }
    }
}
