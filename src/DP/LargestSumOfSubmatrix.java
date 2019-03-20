package DP;

/*
Thoughts: 最优解（O(n^3)), 但只知道结果不知道出在哪里
          1.pre-processing：得到纵向cumulative 2D Matrix，
          2.然后for every上下行边界(O(n^2)) -> cumulative 的行相减压缩成一维array，
          3.求该array的largest sum of subarray.
 */
public class LargestSumOfSubmatrix {
}
