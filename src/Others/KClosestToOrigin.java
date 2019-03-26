package Others;
import java.util.*;

/*
973. K Closest Points to Origin
Medium

240

25

Favorite

Share
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
*/

/*
sol1: sort
sol2: minHeap/maxHeap
sol3: quicksort
 */
public class KClosestToOrigin {
    /* sol2
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0 || K > points.length) return null;
        int[][] res = new int[K][2];
        Queue<Integer[]> minHeap = new PriorityQueue<>(K, (s1, s2) -> (long)s1[0]*s1[0] + (long)s1[1]*s1[1] - (long)s2[0]*s2[0] - (long) s2[1]
        *s2[1] < 0 ? -1 : 1);
        for (int i = 0; i < points.length; i++) {
            minHeap.offer(new Integer[] {points[i][0], points[i][1]});
        }
        for (int i = 0; i < K; i++) {
            Integer[] temp = minHeap.poll();
            res[i][0] = temp[0];
            res[i][1] = temp[1];
        }
        return res;
    }
    */

    // sol3
    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
