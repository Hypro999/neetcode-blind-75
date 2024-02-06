# Idea: break the loop and make it 2 different lines:
# The first line does not contain the first house but may contain the second house.
# The second line is vice versa.
# Then chose the most promising line.
class Solution:
    def rob(self, nums: list[int]) -> int:
        if len(nums) == 1:
            return nums[0]
        x, y = 0, 0
        for num in nums[:-1]:
            x, y = y, max(x + num, y)
        a1 = y
        x, y = 0, 0
        for num in nums[1:]:
            x, y = y, max(x + num, y)
        return max(a1, y)