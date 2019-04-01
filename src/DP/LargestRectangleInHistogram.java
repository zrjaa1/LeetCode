package DP;
import java.util.*;

/*
84. Largest Rectangle in Histogram
Hard

1728

46

Favorite

Share
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.




Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].




The largest rectangle is shown in the shaded area, which has area = 10 unit.



Example:

Input: [2,1,5,6,2,3]
Output: 10
 */

/*
这道题是一道很经典的题，
sol1. 最 primitive 的想法肯定是根据当前位置找左右边界，brute force O(n^3)
sol2. 进一步观察，可以发现，答案被最小值限制。以最小值的高度为限制，答案有三种情况：
        1. 最小值*最大宽度
        2. 最小值左边的最大值
        3. 最小值右边的最大值
        解释：因为最小值处于中间，所以左、右两边不能相互连接，相当于被割断开了。于是我们可以用 recursion 来做
        O(n^2)
sol3. 第二种解法的延伸，因为找到每个区间内的 minimal height 是O(n^2)，我们可以用一个 segment tree 来优化达到O(n*logn)
      （segment tree 被广泛用于找到区间内的最大/小值，根节点表示A[0, n-1]的最值，叶子节点表示单个A[i]的值，中间节点表示区间
sol4. stack 的方法
      （可以直接看Leetcode 官方的solution，过程描述得很清楚）
      由第一种解法我们想到希望改进找到左边界和右边界的时间复杂度。在这里我们巧妙地应用一个 stakc 来达到这个目的
      从左到右，我们一直 push index，直到我们遇到一个数比它左边的数小，所以它左边那个数是 local maximum，我们记为 index = i-1,
      这时，我们就可以认为 i-1之前的高度都可以往右伸展直到 i（不包括 i）因为之前的高度都是慢慢爬升的。那左边界在哪里呢？
      左边界在 j where height[j]<= height[i]，因为这样的 j 的右边界不会是i-1, 起码是i.利用这些边界和高度，我们可以不断 pop stack计算出
      所有[j+1, i-1]区间内的可能面积。并且可以确定的是：height[j] <= height[i]
      这之后，我们再把 i 放入 stack，继续上述的过程，直到将 height 内的所有元素都放过为止。for loop 结束之后，我们期望看到的是一个
      升序的高度序列，因为在上述过程中height[j] <= height[i], 而一旦出现降序的元素，则又会进行一次上述的处理。而此时还在 stack 中的元素，
      全部都是曾经出现过的 local maximum 的右边一个i，和它所对应的 j 值。

      这时我们需要考虑一下边界问题，以使用 stack 的逻辑来看，我们希望 height 的最右边(n)是无限高的，而 height 的最左边(-1)是无线矮的。
      这样才能够挡住。因此，我们在一开始建立 stack 的时候，放一个-1进去，当我们的边界中包含-1时，我们就知道是到了最左边了，不能再往左了，
      而我们计算 area 的时候左边界 index 为-1也正好能够被包含进算式里面。
      在所有元素都放进 stack 之后，stack 变成一个升序序列，而最右边（n）是他们共同的右边界。把所有的元素一边 pop，一边以 index 为左边界，
      n 为右边界，就把这些 area 也遍历了。
      所有计算 area 过程中出现的最大值即为问题的解。
 */
public class LargestRectangleInHistogram {
    // sol4
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        // store index
        Stack<Integer> s = new Stack<>();
        int res = 0;
        s.push(-1);
        for (int i = 0; i < n; i++) {
            // keep pushing until local max is meet
            if (i == 0 || heights[i] > heights[i-1]) s.push(i);
            else {
                while (s.peek() != -1 && heights[s.peek()] > heights[i]) {
                    int left = s.pop();
                    int area = heights[left] * (i-s.peek()-1);
                    res = Math.max(area, res);
                }
                s.push(i);
            }
        }
        while (s.peek() != -1) {
            int right = s.pop();
            int area = heights[right] * (n-1-s.peek());
            res = Math.max(area,res);
        }
        return res;
    }
}
