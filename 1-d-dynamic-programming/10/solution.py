class Solution:
    def lengthOfLIS(self, nums: list[int]) -> int:
        solution = 1
        dp = [1 for _ in range(len(nums))]
        # dp[i] = length of LIS ending at index i of nums (incl.).
        for i in range(1, len(nums)):
            subsolution = 1
            for j in range(0, i):
                if nums[i] > nums[j]:
                    subsolution = max(dp[j] + 1, subsolution)
            dp[i] = subsolution
            solution = max(subsolution, solution)
        return solution
