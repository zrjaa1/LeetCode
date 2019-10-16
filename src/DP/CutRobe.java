package DP;

/*
dp[i] means for the robe of length i+1, what's the maximum product of length of exactly one cut.
每次切或不切，用Math.max(j, dp[j-1])来抉择，而第二层for loop的存在确保了一定会有第一次的切割
第二层可以优化，用数学的方法，当x<4时，不切更大；当要切的时候，用总长/2即使最优的切法（只切一次的情况下 - 正好符合了我们dp[]的定义）
 */
public class CutRobe {
    public int cutRobe(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j <= i/2; j++) {
                int curValue = Math.max(j, dp[j-1]) * Math.max(i-j, dp[i-j-1]);
                curMax = Math.max(curValue, curMax);
            }
            dp[i-1] = curMax;
        }
        return dp[n-1];
    }


}
