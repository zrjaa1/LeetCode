package Stack;

import java.util.Stack;

/**
 * 735. Asteroid Collision: https://leetcode.com/problems/asteroid-collision/
 * Medium
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 *
 *
 * Example 1:
 *
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 * Example 2:
 *
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * Example 3:
 *
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 *
 * Constraints:
 *
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */

/**
 * Stack 对称嵌套
 * Note： case [-2,-1,1,2] would not explode, as only right -> <- left would explode, not the opposite side.
 */
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            int stone = asteroids[i];
            if (!stack.isEmpty() && stone < 0 && stack.peek() > 0) { // collision happens: the stack peek is right and incoming is left
                while (!stack.isEmpty() && stack.peek() > 0) { // if the stack is left, then stop
                    if (Math.abs(stone) > Math.abs(stack.peek())) {
                        stack.pop();
                    } else if (Math.abs(stone) == Math.abs(stack.peek())) {
                        stone = 0;
                        stack.pop();
                        break;
                    } else { // Math.abs(stone) < Math.abs(stack.peek())
                        stone = 0;
                        break;
                    }
                }
                if (stone != 0) {
                    stack.push(stone);
                }
            } else { // no collision
                stack.push(stone);
            }
        }

        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }

        return res;
    }

    public static void main(String[] args) {
        AsteroidCollision tester = new AsteroidCollision();
        int[] asteroids = {8, -8};
        int[] res = tester.asteroidCollision(asteroids);
        System.out.println(res);
    }
}
