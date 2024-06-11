class Solution:
    def maxProduct(self, nums: list[int]) -> int:
        solution = nums[0]
        prev_min = nums[0]
        prev_max = nums[0]
        for num in nums[1:]:
            if num > 0:
                _min = min(num, prev_min * num)
                _max = max(num, prev_max * num)
                if _max > solution:
                    solution = _max
            elif num < 0:
                _min = min(num, prev_max * num)
                _max = max(num, prev_min * num)
            else:
                _min = 0
                _max = 0
            if _max > solution:
                solution = _max
            prev_min = _min
            prev_max = _max
        return solution
