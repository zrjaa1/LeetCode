package OA;
import java.util.*;

/*
Visa OA. 找能买到的最多的本子数目
 */
public class BudgetShopping{
        public static int budgetShopping(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
            // Write your code here
            int[] dp = new int[n+1];
            dp[0] = 0;
            int max = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < bundleQuantities.size(); j++) {
                    if (i < bundleCosts.get(j)) continue;
                    if (max < dp[i - bundleCosts.get(j)] + bundleQuantities.get(j)) {
                        max = dp[i - bundleCosts.get(j)] + bundleQuantities.get(j);
                    }
                }
                dp[i] = max;
                max = 0;
            }
            return dp[n];
        }

        public static void main(String[] args) {
            BudgetShopping tester = new BudgetShopping();
            List<Integer> bundleQuantities = new LinkedList<>();
            List<Integer> bundleCosts = new LinkedList<>();
            bundleQuantities.add(10);
            bundleCosts.add(2);
            int result = tester.budgetShopping(4, bundleQuantities, bundleCosts);
            System.out.print(result);
        }
}
