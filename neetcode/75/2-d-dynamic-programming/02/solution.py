class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        dp = []
        for i, c1 in enumerate(text1):
            row = []
            dp.append(row)
            for j, c2 in enumerate(text2):
                if c1 == c2:
                    row.append(self.safe_read(dp, i - 1, j - 1) + 1)
                else:
                    up = self.safe_read(dp, i - 1, j)
                    left = self.safe_read(dp, i, j - 1)
                    row.append(max(up, left))
        return dp[-1][-1]

    def safe_read(self, dp, i, j):
        if i < 0 or j < 0:
            return 0
        return dp[i][j]
