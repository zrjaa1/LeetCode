package FindK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 373. Find K Pairs with Smallest Sums: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * Medium
 *
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 105
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1 and nums2 both are sorted in ascending order.
 * 1 <= k <= 104
 */

/**
 * 1. Use similar thought in searching k minimal pairs in a 2D array. Instead of move right or move down, this questions move 2 pointers in 2 sorted arrays.
 * 2. Learn how to override hashCode() and equals(Object o) methods in a customized class
 */
public class FindKPairsWithSmallestSums {
    class Cell {
        int i;
        int j;
        int val;

        public Cell(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }

        @Override
        public int hashCode() {
            return this.i * 31 + this.j;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Cell) {
                Cell c = (Cell) o;
                return this.i == c.i && this.j == c.j;
            } else {
                return false;
            }
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>(k);
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0) {
            return res;
        }

        int m = nums1.length, n = nums2.length;
        Set<Cell> visited = new HashSet<>();
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(m + n,
                (a, b) -> a.val - b.val);

        Cell initialCell = new Cell(0, 0, nums1[0] + nums2[0]);
        minHeap.offer(initialCell);
        visited.add(initialCell);

        while (k-- > 0) {
            if (minHeap.isEmpty()) {
                break;
            }

            Cell cur = minHeap.poll();
            int i = cur.i, j = cur.j;
            res.add(Arrays.asList(nums1[i], nums2[j]));

            if (i + 1 < m ) { // move index of nums1
                Cell c1 = new Cell(i + 1, j, nums1[i + 1] + nums2[j]);
                if (!visited.contains(c1)) {
                    minHeap.offer(c1);
                    visited.add(c1);
                }
            }
            if (j + 1 < n ) { // move index of nums2
                Cell c2 = new Cell(i, j + 1, nums1[i] + nums2[j + 1]);
                if (!visited.contains(c2)) {
                    minHeap.offer(c2);
                    visited.add(c2);
                }
            }
        }
        return res;
    }

    /**
     * This solution is WRONG!!, as it misses some pairs. Demo to see why.
     */
    public List<List<Integer>> kSmallestPairsTwoPointers(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>(k);
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0) {
            return res;
        }

        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        res.add(Arrays.asList(nums1[i], nums2[j]));
        while(i < m && j < n) {
            if (res.size() == k) {
                break;
            }

            if (canMove(m, n, i, j) && nums1[i] + nums2[j + 1] <= nums1[i + 1] + nums2[j]) {
                j++;
            } else if (canMove(m, n, i, j) && nums1[i] + nums2[j + 1] > nums1[i + 1] + nums2[j]) {
                i++;
            } else if (i + 1 >= m) { // move j
                j++;
            } else { // move i
                i++;
            }

            res.add(Arrays.asList(nums1[i], nums2[j]));
        }
        return res;
    }

    private boolean canMove(int m, int n, int i, int j) {
        return i + 1 < m && j + 1 < n;
    }

    /**
     * Use int[] in minHeap to store the index in nums1 and nums2. However, this causes Memory out of limit on Leetcode
     */
    public List<List<Integer>> kSmallestPairsOutOfMemory(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>(k);
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0) {
            return res;
        }

        int m = nums1.length, n = nums2.length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(m + n,
                (a, b) -> nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]); // storing the index of nums1[i], nums2[j]

        minHeap.offer(new int[] {0, 0});
        visited[0][0] = true;

        while (k-- > 0) {
            if (minHeap.isEmpty()) {
                break;
            }

            int[] cur = minHeap.poll();
            int i = cur[0], j = cur[1];
            res.add(Arrays.asList(nums1[i], nums2[j]));

            if (i + 1 < m && !visited[i + 1][j]) { // move index of nums1
                minHeap.offer(new int[] {i + 1, j});
                visited[i + 1][j] = true;
            }
            if (j + 1 < n && !visited[i][j + 1]) { // move index of nums2
                minHeap.offer(new int[] {i, j + 1});
                visited[i][j + 1] = true;
            }
        }

        return res;
    }
}
