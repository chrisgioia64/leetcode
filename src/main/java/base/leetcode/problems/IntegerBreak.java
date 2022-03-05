package base.leetcode.problems;


public class IntegerBreak {

    public int integerBreak(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        } else if (n == 4) {
            return 4;
        }
        int[] dp = new int[n+1];
        for (int i = 1; i <= 4; i++) {
            dp[i] = i;
        }
        for (int i = 5; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, dp[j] * dp[i - j]);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        IntegerBreak ib = new IntegerBreak();
        System.out.println(ib.integerBreak(10));
    }

}
