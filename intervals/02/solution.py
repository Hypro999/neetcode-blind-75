class Solution:
    def merge(self, intervals: list[list[int]]) -> list[list[int]]:
        intervals.sort()
        merged_intervals = [intervals[0]]
        for interval in intervals[1:]:
            last_interval = merged_intervals[-1]
            if interval[0] <= last_interval[1]:
                if interval[1] > last_interval[1]:
                    last_interval[1] = interval[1]
            else:
                merged_intervals.append(interval)
        return merged_intervals
