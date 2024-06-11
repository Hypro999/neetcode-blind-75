class Solution:
    def missingNumber(self, nums: list[int]) -> int:
        missing_num = len(nums)
        for i in range(len(nums)):
            missing_num ^= i
        for num in nums:
            missing_num ^= num
        # The two loops above can be merged (at the cost of readability).
        return missing_num
