class Solution:
    def eraseOverlapIntervals(self, intervals: list[list[int]]) -> int:
        erased = 0
        intervals.sort(key=lambda interval: (interval[1], len(interval)))
        prev_interval = intervals[0]
        for i in range(1, len(intervals)):
            interval = intervals[i]
            if self.overlap(prev_interval, interval):
                erased += 1
            else:
                prev_interval = interval
        return erased

    def overlap(self, a, b):
        return a[1] > b[0] and a[0] < b[1]
