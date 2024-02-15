class Solution:

    def countSubstrings(self, s: str) -> int:
        if len(s) < 2:
            return len(s)

        # Set up the recursive subproblem cache with the base cases (strings of length 1 and 2).
        # Note: if we wanted to save on space, we could condense this to just the upper-triangular matrix.
        dp = [[False] * len(s) for _ in range(len(s))]
        for i in range(len(s)):
            dp[i][i] = True
        palindrome_count = len(s)
        for end in range(1, len(s)):
            start = end - 1
            if s[start] == s[end]:
                palindrome_count += 1
                dp[start][end] = True

        # Solve the recursive subproblems.
        for start_offset in range(2, len(s)):
            for end in range(start_offset, len(s)):
                start = end - start_offset
                if s[start] == s[end] and dp[start + 1][end - 1]:
                    palindrome_count += 1
                    dp[start][end] = True

        return palindrome_count
