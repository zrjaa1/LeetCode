package Company.Google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1610. Maximum Number of Visible Points: https://leetcode.com/problems/maximum-number-of-visible-points/
 * Hard
 *
 * You are given an array points, an integer angle, and your location, where location = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.
 *
 * Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate. In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise. Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].
 *
 *
 * You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate east direction from your position is in your field of view.
 *
 * There can be multiple points at one coordinate. There may be points at your location, and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.
 *
 * Return the maximum number of points you can see.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 * Output: 3
 * Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
 * Example 2:
 *
 * Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 * Output: 4
 * Explanation: All points can be made visible in your field of view, including the one at your location.
 * Example 3:
 *
 *
 * Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
 * Output: 1
 * Explanation: You can only see one of the two points, as shown above.
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 105
 * points[i].length == 2
 * location.length == 2
 * 0 <= angle < 360
 * 0 <= posx, posy, xi, yi <= 100
 */

/**
 * 这道题的思路比较清晰，即计算出所有点与原点的角度，放入angle list中，sort，然后用sliding window来计算最多有多少个点在window中。Corner case比较多，需要注意的有：
 * 1. 位于原点的点，不放入angle list中sort，而是分开统计
 * 2。可以在angle list中append 原来角度 + 2PI后的角度，这样可以解决负角度的问题
 * 3. max的初始值为0而不为1，否则在angle list中没有element时，会出错。
 */

public class MaximumOfNumberOfVisiblePoints {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int sameLocation = 0; // 用来记录位于原点的点的数量
        for (int i = 0; i < points.size(); i++) {
            int x = points.get(i).get(0);
            int y = points.get(i).get(1);
            if (x == location.get(0) && y == location.get(1)) { // 分开统计
                sameLocation++;
            } else {
                double pointAngle = Math.atan2((double)(y - location.get(1)), (double)(x - location.get(0)));
                angles.add(pointAngle);
            }
        }

        Collections.sort(angles);
        int size = angles.size();
        for (int i = 0; i < size; i++) {
            angles.add(angles.get(i) + 2 * Math.PI); // 为每个angle + 2pi来完美解决负角度的问题
        }

        int max = 0;
        for (int i = 0; i < size; i++) { // 循环到原来的size即可，后面多循环虽然不会出错，但浪费时间
            double baseAngle = angles.get(i);
            double targetAngle =baseAngle + ((double)(angle) / 180D * Math.PI);
            int index = binarySearch(angles, targetAngle);
            int numOfPoints = index - i + 1;
            max = Math.max(max, numOfPoints);
        }
        return max + sameLocation;
    }

    private int binarySearch(List<Double> angles, double targetAngle) {
        int left = 0, right = angles.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (angles.get(mid) <= targetAngle) { // go right
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}
