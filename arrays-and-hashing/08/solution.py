class Solution:

    def longestConsecutive(self, nums: list[int]) -> int:
        dp = {}
        for num in nums:
            dp[num] = None

        def check(num):
            if dp[num] is not None:
                return dp[num]
            answer = 1
            if num + 1 in dp:
                answer += check(num + 1)
            dp[num] = answer
            return answer

        best_answer = 0
        for num in nums:
            best_answer = max(best_answer, check(num))

        return best_answer
