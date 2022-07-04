package DFS;

/*
377
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        Wrapper res = new Wrapper(0);
        if (nums == null || nums.length == 0 || target <= 0) return res.val;
        dfs(nums, target, res);
        return res.val;
    }

    // index - how many numbers has been used
    private void dfs (int[] nums, int left, Wrapper res) {
        if (left == 0) {
            res.val++;
            return;
        }
        if (left < 0) return;

        for (int i = 0; i < nums.length; i++) {
            dfs(nums, left-nums[i], res);
        }
    }

    public static void main(String[] args) {
        CombinationSumIV tester = new CombinationSumIV();
        int nums[] = {1,2,3};
        int target = 32;
        tester.combinationSum4(nums, target);
    }
}

class Wrapper {
    int val;
    Wrapper(int i) {
        val = i;
    }
}