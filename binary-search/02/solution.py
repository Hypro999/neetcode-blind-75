import bisect


class Solution:
    def search(self, nums: list[int], target: int) -> int:
        # Un-rotated arrays are a bit of a special case.
        if nums[0] <= nums[-1]:
            i = bisect.bisect_left(nums, target)
            if i >= len(nums) or nums[i] != target:
                return -1
            return i

        lo = 0
        hi = len(nums)
        while lo < hi:
            mid = (lo + hi) // 2
            if nums[mid] >= nums[0]:
                # We are in the upper part
                if target < nums[0] or target > nums[mid]:
                    # Move towards the lower part or the remaining unexplored section
                    # of the upper part.
                    lo = mid + 1
                else:
                    hi = mid
            else:
                # We are in the lower part
                if target > nums[-1] or target <= nums[mid]:
                    # Move towards the upper part or the remaining unexplored section
                    # of the lower part.
                    hi = mid
                else:
                    lo = mid + 1
        if lo >= len(nums) or nums[lo] != target:
            return -1
        return lo
