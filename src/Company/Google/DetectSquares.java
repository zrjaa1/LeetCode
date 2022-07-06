package Company.Google;

import java.util.ArrayList;
import java.util.List;

/**
 * 2013. Detect Squares: https://leetcode.com/problems/detect-squares/
 * Medium
 *
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 *
 * Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
 * Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
 * An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
 *
 * Implement the DetectSquares class:
 *
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * Output
 * [null, null, null, null, 1, 0, null, 2]
 *
 * Explanation
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // return 1. You can choose:
 *                                //   - The first, second, and third points
 * detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
 * detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
 * detectSquares.count([11, 10]); // return 2. You can choose:
 *                                //   - The first, second, and third points
 *                                //   - The first, third, and fourth points
 *
 *
 * Constraints:
 *
 * point.length == 2
 * 0 <= x, y <= 1000
 * At most 3000 calls in total will be made to add and count.
 */

/**
 * given p1, for each possible p3(its diagonal point), count the p2 and p4 combinations.
 * O(T) = O(n) where n is the number of points, O(S) = O(n)
 */
public class DetectSquares {
    int[][] count;
    List<int[]> points;

    public DetectSquares() {
        this.count = new int[1001][1001];
        this.points = new ArrayList<>();
    }

    public void add(int[] point) {
        count[point[0]][point[1]]++;
        points.add(point);
    }

    public int count(int[] point) {
        int res = 0;
        int p1x = point[0], p1y = point[1];
        for (int[] diagonal : points) {
            int p3x = diagonal[0], p3y = diagonal[1];
            if (p1x == p3x || p1y == p3y || Math.abs(p1x - p3x) != Math.abs(p1y - p3y)) { // empty square or not a square
                continue;
            }

            // 1. p2 and p4 found here will be skipped in the loop as they cannot be p3(diagonal of p1)
            // 2. we don't consider the count of p3 here as we will iterate other p3s in the loop as well.
            res += count[p3x][p1y] * count[p1x][p3y];
        }

        return res;
    }
}
