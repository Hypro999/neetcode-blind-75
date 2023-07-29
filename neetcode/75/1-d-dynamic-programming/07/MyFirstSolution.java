import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyFirstSolution {

    public int coinChange(int[] coins, int amount) {

        // Assumption 1: Coins has only unique values.
        // Assumption 2: 0 is not a part of coins.
        Arrays.sort(coins);

        // Set up a cache.
        Map<Integer, Integer> amountToMinCoins = new HashMap<>();
        amountToMinCoins.put(0, 0);
        for (int i = 0; i < coins.length; i++) {
            amountToMinCoins.put(coins[i], 1);
        }

        // Recursively find the best solution.
        var sol = coinChangeRecursive(coins, amount, amountToMinCoins);
        System.out.println(amountToMinCoins.get(372));
        return sol;
    }

    public int coinChangeRecursive(
            int[] coins,
            int amount,
            Map<Integer, Integer> amountToMinCoins) {

        if (amount < 0) {
            return -1;
        }

        Integer cachedValue = amountToMinCoins.get(amount);
        if (cachedValue != null) {
            return cachedValue;
        }

        int minCoins = -1; // Start with the tentative assumption that there is no solution.

        // Look at the highest denomenation available and move downwards.
        for (int j = coins.length - 1; j >= 0; j--) {
            int subAmount = amount - coins[j];
            int subMinCoins = coinChangeRecursive(coins, subAmount, amountToMinCoins);

            if (subMinCoins == -1) {
                // Dead end, no possible answer.
                continue;
            }

            int numCoins = subMinCoins + 1;
            if (minCoins == -1 || numCoins < minCoins) {
                // Initialize value || We found a better solution.
                minCoins = numCoins;
            }
        }

        // Cache and return the solution.
        amountToMinCoins.put(amount, minCoins);
        return minCoins;
    }
}
