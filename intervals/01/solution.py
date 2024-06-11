class Solution:
    def insert(self, intervals: list[list[int]], new_interval: list[int]) -> list[list[int]]:
        START = 0
        END = 1

        def strictly_before(a, b):
            return a[END] < b[START]

        solution = []
        start = new_interval[START]
        end = new_interval[END]
        strictly_after_flag = False
        for interval in intervals:
            if strictly_after_flag:
                solution.append(interval)
                continue
            if strictly_before(new_interval, interval):
                strictly_after_flag = True
                solution.append([start, end])
                solution.append(interval)
                continue
            if strictly_before(interval, new_interval):
                solution.append(interval)
                continue
            start = min(start, interval[START])
            end = max(end, interval[END])
        if not strictly_after_flag:
            solution.append([start, end])

        return solution