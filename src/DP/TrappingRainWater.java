package DP;

/*
42. Trapping Rain Water
Hard

3164

58

Favorite

Share
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */

/*
Thoughts:
// so1: 正面思考
1. 首先想到，用LeftHeight和RightHeight来表示，从当前的点向两边看，看到的边界
2. 当前这个点能存的水，等于min(LeftHeight, RightHeight) - Height[i]，如果小于0，记为0
3. LeftHeight初始化为0，RightHeight初始化为从i=0，向右看能看到的最大值（也是height(0, n-1]的最大值）
4. 当Height[i] > LeftHeight时，表示我遇到了更高的山，可以更新LeftHeight
5. 当Height[i] == RightHeight时，表示我走过了我的rightHeight，应该更新RightHeight
6. Optimization： 用dp[i]来存储，从index = i这个点往右看的最大值。
7. Time： O（n) Space: O(n)

// sol2: 反面思考
1. 同样是用LeftHeight和rightHeight，两个指针在两边相向而行，LeftHeight = max(LeftHeight, height[left]), RightHeight = max(RightHeight, height[right])
2. 每次移动一个指针，哪边的边界小就移哪边，例如我们这次移动了左边，并且因为左边较小，所以要漏水也只能从左边漏，所以能trap的水量就只有LeftHeight-height[left]决定，不用考虑未来RightHeight会更大的问题
3. 一直移动到left > right 为止
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int res = 0, n = height.length;
        int[] dp = new int[n];
        dp[n-1] = 0;
        for (int i = n-2; i >= 0; i--) {
            dp[i] = Math.max(dp[i+1], height[i+1]);
        }
        int leftHeight = 0, rightHeight = dp[0];
        for (int i = 0; i < height.length; i++) {
            int limit = Math.min(leftHeight, rightHeight);
            if (height[i] < limit) res += limit - height[i];
            if (height[i] > leftHeight) leftHeight = height[i];
            if (height[i] == rightHeight) rightHeight = dp[i];
        }
        return res;
    }
}
