class Solution:
    def rob(self, nums: list[int]) -> int:
        dp = [
            0,
            0,
        ]  # Technically, we don't need an array, just the 2 values. But I'm feeling lazy.
        for i, num in enumerate(nums, 2):
            dp.append(max(dp[i - 2] + num, dp[i - 1]))
        return dp[len(nums) + 1]
