# The solution in solution.py is a prefix based O(N^2) time solution.
# https://cp-algorithms.com/sequences/longest_increasing_subsequence.html#solution-in-on-log-n-with-dynamic-programming-and-binary-search
from typing import Optional

def length_based_n_squared_solution(nums: list[int]) -> int:
    dp = [None for _ in nums]
    # dp[j] = smallest number for an increasing subsequence of length j + 1 that we've
    # found SO FAR (as of index i in nums).

    longest_increasing_subsequence_length = 0
    for i in range(0, len(nums)):
        for j in range(0, longest_increasing_subsequence_length + 1):
            # subsequence_length = j + 1
            # Check: can the current number we're looking at be a better candidate
            # for an increasing subsequence ending of length subsequence_length?
            if dp[j] is None or nums[i] < dp[j]:
                if j == 0 or dp[j - 1] < nums[i]:
                    if dp[j] is None:
                        longest_increasing_subsequence_length += 1
                    dp[j] = nums[i]
                break

    return longest_increasing_subsequence_length

# Tweak unoptimized_length_based_n_squared_solution to binary search over dp instead of
# linear search.
def length_based_log_n_solution(nums: list[int]) -> int:
    dp = [None for _ in nums]
    longest_increasing_subsequence = 0
    for i in range(len(nums)):
        j = findInsertIndex(dp, 0, longest_increasing_subsequence + 1, nums[i])
        if j is None:
            continue
        if dp[j] is None:
            longest_increasing_subsequence += 1
        dp[j] = nums[i]
    return longest_increasing_subsequence

def findInsertIndex(dp: list[int], lower: int, upper: int, target: int) -> Optional[int]:
    while upper >= lower:
        current = (lower + upper) // 2
        if target == dp[current]:
            break
        elif dp[current] is None or target < dp[current]:
            if current - 1 < lower or target > dp[current - 1]:
                return current
            upper = current - 1
        else:
            if current + 1 > upper:
                return current
            if dp[current + 1] is None or target < dp[current + 1]:
                return current + 1
            lower = current + 1
    return None

from bisect import bisect_left
def someone_elses_best_solution(nums: list[int]) -> int:
    lst = []
    for num in nums:
        i = bisect_left(lst, num)
        if i == len(lst):
            lst.append(num)
        else:
            lst[i] = num
    return len(lst)
