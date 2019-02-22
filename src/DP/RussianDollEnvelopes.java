package DP;
import java.util.*;

/*
354. Russian Doll Envelopes
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */

/*
这道题用longest subsequence的逻辑:
1. 先按envelope[0]从小到大排，相等的按envelope[1]从大到小排（改写 comparator），这样使得 dp[i]里存的高度，一定是当前最优解
2. dp[i]的含义是，长度为i+1的 subsequence，最小的 ending height是多少
3. 在这里 BinarySearch 起的关键作用是：不仅做了 search。还返回了该插入的 index（返回值是-(index+1)),如果返回的是一个已经有值了的index，
   就说明我有了更小的height 可以达到同样的 subsequence。但如果 width 相同的情况下，是不能取代的，为了防止这种情况，我们用了1.中所描述的
   Sort 方法
第二种:
1. dp[i]表示以第 i+1个元素为 ending height 的最大 subsequence 长度
2. 双层 loop，第一层 loop 所有元素，第二层往回看，找可以包含条件下的最大的dp[j]，然后+1赋给当前的 dp[i]
3. 再走一次，返回最大的 dp[i]
4. note：仍然需要使用上述的 Sort 方法，这样确保了(6,7)不会包含(6,4)
 */
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o2[1]-o1[1];
                else
                    return o1[0]-o2[0];
            }
        });
        // 第一种 dp
        /*
        int[] dp = new int[envelopes.length];
        int len = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.BinarySearch(dp, 0, len, envelope[1]);
            if (index < 0) index = -(index+1);
            dp[index] = envelope[1];
            if (len == index) len++;
        }

        return len;
        */

        // 第二种 dp
        int len = envelopes.length;
        int dp[] = new int[len];
        dp[0] = 1;
        for (int i = 0; i < len; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1] && max < dp[j]+1) max = dp[j]+1;
            }
            dp[i] = max;
        }
        int result = 1;
        for (int i = 0; i < len; i++) {
            result = dp[i] > result ? dp[i] : result;
        }
        return result;
    }
}
