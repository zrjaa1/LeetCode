package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return null;
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                HashSet<Integer> curSolution = new HashSet<Integer>();
                curSolution.add(0-nums[i]-nums[j]);
                for (int k = j+1; k < nums.length; k++) {
                    if (curSolution.contains(nums[k])) {
                    	ArrayList<Integer> temp = new ArrayList<Integer> (Arrays.asList(nums[i], nums[j], nums[k]));
                        res.add(temp);
                    }
                }
            }
        }
        return res;
    }
}
