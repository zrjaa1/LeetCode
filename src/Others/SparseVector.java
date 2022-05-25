package Others;

/**
 * 1570. Dot Product of Two Sparse Vectors: https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 * Medium
 *
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 *
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 *
 * Follow up: What if only one of the vectors is sparse?
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * Example 2:
 *
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * Example 3:
 *
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */

import java.util.LinkedList;

/**
 * 1. Sol1: Use index pair
 * 2. Sol2: Use HashMap<Index, Value>
 */
public class SparseVector {
    /**
     * My solution: Used linked list with index recorded
     */
    class Vector {
        int idx;
        int val;

        public Vector(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    LinkedList<Vector> vectors;

    SparseVector(int[] nums) {
        vectors = new LinkedList<Vector>();
        int idx = 0;
        for (int num : nums) {
            if (num != 0) {
                vectors.add(new Vector(idx, num));
            }
            idx++;
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int res = 0;
        int cur1 = 0, cur2 = 0;
        while (cur1 < vectors.size() && cur2 < vec.vectors.size()) {
            Vector vec1 = vectors.get(cur1);
            Vector vec2 = vec.vectors.get(cur2);
            if (vec1.idx == vec2.idx) {
                res += vec1.val * vec2.val;
                cur1++;
                cur2++;
            } else if (vec1.idx < vec2.idx) {
                cur1++;
            } else {
                cur2++;
            }
        }

        return res;
    }
}
