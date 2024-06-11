class Solution:
    def canJump(self, nums: List[int]) -> bool:
        if (len(nums) == 1):
            return True
        start = 0
        endable = 0
        while start <= endable:
            end = start + nums[start]
            if end > endable:
                endable = end
            if endable >= len(nums) - 1:
                return True
            start += 1
        return False
