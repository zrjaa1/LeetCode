package Others;

/**
528. Random Pick with Weight
Medium

Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input:
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input:
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.

Accepted
20,884
Submissions
48,853
 */

/**
举例：将[3,14,7,9] -> [3, 17, 24, 33] 然后产生一个[0,33]的随机数，binary search找到这个随机数所处的区间然后返回index
 */
public class RandomPickWithWeight {
    int[] weightSum;
    public RandomPickWithWeight(int[] w) {
        if (w == null || w.length == 0) return;
        for (int i = 1; i < w.length; i++) {
            w[i] += w[i-1];
        }
        weightSum = w;
    }

    public int pickIndex() {
        int n = weightSum.length;
        double random = Math.random()*weightSum[n-1];
        int l = 0, r = n-1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (random < weightSum[mid] && (mid == 0 || random >= weightSum[mid-1])) return mid;
            else if (random >= weightSum[mid]) l = mid+1;
            else r = mid-1;
        }
        return l;
    }
}
