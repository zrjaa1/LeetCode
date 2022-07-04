package DP;

/**
 * 552. Student Attendance Record II: https://leetcode.com/problems/student-attendance-record-ii/
 * Hard
 *
 * 1261
 *
 * 217
 *
 * Add to List
 *
 * Share
 * An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
 *
 * 'A': Absent.
 * 'L': Late.
 * 'P': Present.
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 *
 * The student was absent ('A') for strictly fewer than 2 days total.
 * The student was never late ('L') for 3 or more consecutive days.
 * Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 8
 * Explanation: There are 8 records with length 2 that are eligible for an award:
 * "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
 * Example 2:
 *
 * Input: n = 1
 * Output: 3
 * Example 3:
 *
 * Input: n = 10101
 * Output: 183236316
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 */
public class StudentAttendanceRecordII {
    /**
     * My solution, could be optimized
     */
    long M = 1000000007;
    public int checkRecord(int n) {
        if (n == 1) {
            return 3;
        }
        // initialization
        long[][][] dp = new long[n][2][3];
        dp[n - 1][0][0] = 1; // P
        dp[n - 1][0][1] = 1; // L
        dp[n - 1][1][0] = 1; // A
        dp[n - 2][0][0] = 2; // PP, LP
        dp[n - 2][0][1] = 1; // PL
        dp[n - 2][0][2] = 1; // LL
        dp[n - 2][1][0] = 3; // AP, PA, LA
        dp[n - 2][1][1] = 1; // AL;

        for (int i = n - 3; i >= 0; i--) {
            dp[i][0][0] = (dp[i + 1][0][0] + dp[i + 1][0][1] + dp[i + 1][0][2]) % M;    // LP, PP
            dp[i][0][1] = dp[i + 1][0][0];                      // PL
            dp[i][0][2] = dp[i + 1][0][1];                      // LL
            dp[i][1][0] = (dp[i + 1][0][0] +  dp[i + 1][0][1] + dp[i + 1][0][2] + dp[i + 1][1][0] + dp[i + 1][1][1] + dp[i + 1][1][2]) % M;  // PP, PA, AP, LP, LA
            dp[i][1][1] = dp[i + 1][1][0];    // AL, PL
            dp[i][1][2] = dp[i + 1][1][1];                      // LL
        }

        long res = 0;
        for (long[] resultInt : dp[0]) {
            for (long result : resultInt) {
                res += result;
            }
        }

        return (int)(res % M);
    }

    /**
     * Best to draw a state machine. Solution from leetcode, optimized version from mine.
     */
    public int checkRecordII(int n) {
        long a0l0 = 1, a0l1 = 0, a0l2 = 0, a1l0 = 0, a1l1 = 0, a1l2 = 0;
        for (int i = 0; i <= n; i++) {
            long a0l0_ = (a0l0 + a0l1 + a0l2) % M;
            a0l2 = a0l1;
            a0l1 = a0l0;
            a0l0 = a0l0_;
            long a1l0_ = (a0l0 + a1l0 + a1l1 + a1l2) % M;
            a1l2 = a1l1;
            a1l1 = a1l0;
            a1l0 = a1l0_;
        }
        return (int) a1l0;
    }
}
