package Company;

import java.util.*;
public class MinimumDistance {
    int minimumDistance(int numRows, int numColumns, List<List<Integer>> area)
    {
        if (area == null || area.size() == 0 || area.get(0) == null || area.get(0).size() == 0 || numRows != area.size() || numColumns != area.get(0).size()) return -1;
        int res = Integer.MAX_VALUE;
        int[][] dp = new int[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 1;
        int[] resIndex = new int[2];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (area.get(i).get(j) == 9) {
                    resIndex[0] = i;
                    resIndex[1] = j;
                    continue;
                } else if (area.get(i).get(j) == 0) continue;
                // update right
                if (j+1 < numColumns) {
                    int cur = area.get(i).get(j+1);
                    if (cur == 1 && dp[i][j+1] > dp[i][j]+1) dp[i][j+1] = dp[i][j]+1;
                }
                // update down
                if (i+1 < numRows) {
                    int cur = area.get(i + 1).get(j);
                    if (cur == 1 && dp[i + 1][j] > dp[i][j]+1) dp[i+1][j] = dp[i][j]+1;
                }
                // update left
                if (j-1 > 0) {
                    int cur = area.get(i).get(j-1);
                    if (cur == 1 && dp[i][j-1] > dp[i][j]+1) dp[i][j-1] = dp[i][j]+1;
                }
                // update up
                if (i-1 > 0) {
                    int cur = area.get(i-1).get(j);
                    if (cur == 1 && dp[i-1][j] > dp[i][j]+1) dp[i-1][j] = dp[i][j]+1;
                }
            }
        }
        if (resIndex[0]+1 < numRows) res = Math.min(dp[resIndex[0]+1][resIndex[1]], res);
        if (resIndex[0]-1 > -1) res = Math.min(dp[resIndex[0]-1][resIndex[1]], res);
        if (resIndex[1]+1 < numColumns) res = Math.min(dp[resIndex[0]][resIndex[1]+1], res);
        if (resIndex[0]-1 > -1) res = Math.min(dp[resIndex[0]][resIndex[1]-1], res);
        return res == Integer.MAX_VALUE ? -1: res;

    }

}
