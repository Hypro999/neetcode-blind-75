# Avoid duplicates by applying logic to shrink the array of distinct integers down right-hand-side paths.
# Also, avoid pushing references to self onto the stack.
class Solution:
    def combinationSum(self, candidates: list[int], target: int) -> list[list[int]]:
        solutions = []

        def recr(start: int, target: int, acc: list[int]):
            if target < 0:
                return
            if target == 0:
                solutions.append(acc[:])
                return
            for i, candidate in enumerate(candidates[start:], start=start):
                acc.append(candidate)
                recr(i, target - candidate, acc)
                acc.pop()

        recr(0, target, [])

        return solutions
