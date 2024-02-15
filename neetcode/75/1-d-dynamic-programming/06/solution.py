class Solution:

    def numDecodings(self, s: str) -> int:
        if len(s) == 0 or s[0] == "0":
            return 0
        if len(s) == 1:
            return 1

        n2 = 1  # n2: Number of ways to construct string 2 positions behind.
        n1 = 1  # n1: Number of ways to construct string 1 position behind.

        prev_c = s[0]
        for c in s[1:]:
            if c == "0":
                if not (prev_c == "1" or prev_c == "2"):
                    return 0
                cur = n2
            else:
                cur = n1
                if prev_c == "1" or (prev_c == "2" and int(c) in range(0, 7)):
                    cur += n2
            n2, n1 = n1, cur
            prev_c = c

        return n1
