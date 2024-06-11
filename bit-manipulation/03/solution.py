class Solution:
    def reverseBits(self, n: int) -> int:
        solution = 0
        for i in range(32):
            solution = (solution << 1) | (n & 1)
            n >>= 1
        return solution