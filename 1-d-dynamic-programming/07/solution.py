class Solution:
    def coinChange(self, coins: list[int], amount: int) -> int:
        dp = [0]
        for target in range(1, amount + 1):
            min_num_coins = float("inf")
            for coin in coins:
                if target - coin < 0:
                    continue
                num_coins = dp[target - coin] + 1
                if num_coins < min_num_coins:
                    min_num_coins = num_coins
            dp.append(min_num_coins)
        if dp[-1] == float("inf"):
            return -1
        return dp[-1]
