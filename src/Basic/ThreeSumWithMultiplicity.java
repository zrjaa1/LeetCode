package Basic;
import java.util.*;

/*
923. 3Sum With Multiplicity
Medium

161

25

Favorite

Share
Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.



Example 1:

Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation:
Enumerating by the values (A[i], A[j], A[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
Example 2:

Input: A = [1,1,2,2,2,2], target = 5
Output: 12
Explanation:
A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.


Note:

3 <= A.length <= 3000
0 <= A[i] <= 100
0 <= target <= 300
 */

/*
找到等于target的i、j、k的值后，需要先判断A[j] == A[k]，如果是的话，说明中间的一段都是相同的值，那么可能性就有Cn（2）种
否则，说明中间被隔开了，j,k只能在各自的部分取不同的index，这时用lDup和rDup来统计左边和右边的重复的个数，res += lDup*rDup
注意res先用long来存储，否则中间lDup*rDup可能溢出（lDup和rDup可以为int没问题，只要存储结果的地方是long就行）
 */
public class ThreeSumWithMultiplicity {
    public int threeSumMulti(int[] A, int target) {
        if (A == null || A.length <= 2) return 0;
        Arrays.sort(A);
        long res = 0;
        int MOD = 1000000007;
        for (int i = 0; i < A.length - 2; i++) {
            int l = i+1;
            int r = A.length-1;
            while (l < r) {
                if (A[i] + A[l] + A[r] == target) {
                    if (A[l] == A[r]) {
                        res += (r-l+1)*(r-l)/2;
                        l = r;
                    } else {
                        int lDup = 1;
                        int rDup = 1;
                        while (l+1 < r && A[r] == A[r-1]) {
                            rDup++;
                            r--;
                        }
                        while (l+1 < r && A[l] == A[l+1]) {
                            lDup++;
                            l++;
                        }
                        l++;
                        r--;
                        res += lDup*rDup;
                    }
                    res %= MOD;
                } else if (A[i] + A[l] + A[r] < target) l++;
                else r--;
            }
        }
        return (int)res;
    }

    public static void main(String[] args) {
        ThreeSumWithMultiplicity tester = new ThreeSumWithMultiplicity();
        int[] nums = {1,1,2,2,3,3,4,4,5,5};
        int target = 8;
        tester.threeSumMulti(nums, target);
    }
}
