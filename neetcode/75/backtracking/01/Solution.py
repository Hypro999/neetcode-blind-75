class Solution:
    def combinationSum(self, candidates: list[int], target: int) -> list[list[int]]:
        solutions = set()
        self.candidates = candidates
        self.recursiveCombinationSum(target, [0] * 41, solutions)
        return [list(solution) for solution in solutions]

    def recursiveCombinationSum(self, target: int, freq_map: list[int], solutions: set[tuple[int]]) -> None:
        if target < 0:
            return
        if target == 0:
            solutions.add(self.to_tuple(freq_map))
            return
        for candidate in self.candidates:
            freq_map[candidate] += 1
            self.recursiveCombinationSum(target - candidate, freq_map, solutions)
            freq_map[candidate] -= 1

    def to_tuple(self, freq_map: int) -> tuple[int, ...]:
        li = []
        for i, f in enumerate(freq_map):
            while f > 0:
                li.append(i)
                f -= 1
        return tuple(li)  # inefficient?
