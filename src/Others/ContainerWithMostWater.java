package Others;
/*
11. Container With Most Water
Medium

2887

412

Favorite

Share
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.





The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.



Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 */

/*
利用反证法证明：在左边右边确定的情况下，最大值一定出在移动左、右中更小的那一边的可能性中
1. 假设[a10, a20]是最大的区域，我们需要证明在[a10, a21]时，我们会移动的是21而不是a10，不然的话就会丢解（同理，[a9,a20]的情况下也一定会移动a9）
2. 如果我们移动的是a10,那么说明a10 < a21, 那样的话，最大的区域就会是[a10, a21]而不是[a10, a20] (想不清楚可画图）
3. 因此，我们可以证明在假设[a10, a20]区域下，a10 > a21, 我们肯定会移动a21，因此不会丢解。
Given a1,a2,a3.....an as input array. Lets assume a10 and a20 are the max area situation. We need to prove that a10 can be reached by left pointer and during the time left pointer stays at a10, a20 can be reached by right pointer. That is to say, the core problem is to prove: when left pointer is at a10 and right pointer is at a21, the next move must be right pointer to a20.

Since we are always moving the pointer with the smaller value, i.e. if a10 > a21, we should move pointer at a21 to a20, as we hope. Why a10 >a21? Because if a21>a10, then area of a10 and a20 must be less than area of a10 and a21. Because the area of a10 and a21 is at least height[a10] * (21-10) while the area of a10 and a20 is at most height[a10] * (20-10). So there is a contradiction of assumption a10 and a20 has the max area. So, a10 must be greater than a21, then next move a21 has to be move to a20. The max cases must be reached.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) return 0;
        int l = 0, r = height.length-1;
        int max = 0;
        while (l < r) {
            max = Math.max(max, (r-l)*Math.min(height[l], height[r]));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return max;
    }
}
