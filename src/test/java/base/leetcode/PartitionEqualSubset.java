package base.leetcode;

import java.util.Arrays;

public class PartitionEqualSubset {

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }
        int k = sum / 2 + 1;
        boolean[][] dp = new boolean[nums.length][k];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];
            for (int j = 0; j < k; j++) {
                if (dp[i-1][j]) {
                    dp[i][j] = true;
                    int newVal = val + j;
                    if (newVal < k) {
                        dp[i][newVal] = true;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(dp[nums.length-1]);
        return dp[nums.length-1][sum / 2];
    }

    public static void main(String[] args) {
        int[] x = new int[] {2, 4, 1, 2, 3};
        PartitionEqualSubset ps = new PartitionEqualSubset();
        System.out.println(Arrays.toString(res));
    }


}
