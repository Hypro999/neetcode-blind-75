class Solution:
    def findMin(self, nums: list[int]) -> int:
        lo = 0
        hi = len(nums)
        while lo < hi:
            mid = (lo + hi) // 2
            if nums[mid] >= nums[0]:
                # Upper part
                lo = mid + 1
            else:
                # Lower part
                hi = mid

        # Now, lo will either be on the left or right side of the divide.
        return min(nums[lo - 1], nums[lo % len(nums)], nums[(lo + 1) % len(nums)])
