package base.leetcode.problems;

import java.util.Arrays;

public class CombinationSum {

    private int count;
    private int[] nums;
    private int target;

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                int k = i - nums[j];
                if (k < 0) {
                    break;
                }
                count += dp[k];
            }
            dp[i] = count;
        }
        return dp[target];
    }

    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        int x = cs.combinationSum4(new int[] {1, 2, 3}, 4);
        System.out.println(x);
    }
}
