class Solution:

    def longestPalindrome(self, s: str) -> str:
        longest_start = 0
        longest_end = 0
        longest_length = 1
        for i in range(1, len(s)):
            start, end = self._longestPalindrome(s, i, i)
            length = end - start + 1
            if length > longest_length:
                longest_start = start
                longest_end = end
                longest_length = length
        for i in range(1, len(s)):
            if s[i - 1] == s[i]:
                start, end = self._longestPalindrome(s, i - 1, i)
                length = end - start + 1
                if length > longest_length:
                    longest_start = start
                    longest_end = end
                    longest_length = length
        return s[longest_start : longest_end + 1]

    # precondition: s[left] == s[right] and both left and right are in bounds.
    def _longestPalindrome(self, s: str, left: int, right: int) -> tuple[int, int]:
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left = left - 1
            right = right + 1
        return left + 1, right - 1
