import java.util.Arrays;

public class MySecondSolution {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);

        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                int k = i - coin;
                if (k < 0 || dp[k] == -1) {
                    // No solution for this amount.
                    continue;
                }
                if (dp[i] == -1) {
                    // Need to initialize.
                    dp[i] = dp[k] + 1;
                } else {
                    dp[i] = Math.min(dp[i], dp[k] + 1);
                }
            }
        }

        return dp[amount];
    }
}
