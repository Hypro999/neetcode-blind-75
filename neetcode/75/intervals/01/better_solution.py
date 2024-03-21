from bisect import bisect_left, bisect_right

class Solution:
    def insert(self, intervals: list[list[int]], newInterval: list[int]) -> list[list[int]]:
        n = len(intervals)
        intStart, intEnd = newInterval

        # find by binary search the index of interval that is ended exactly after intStart
        posL = bisect_left(intervals, intStart, key=lambda it: it[1])
        if posL < n and intervals[posL][0] < intStart:
            intStart = intervals[posL][0] # correct new interval's start if merge required

        # find by binary search the index of interval that is started exactly after intEnd
        posR = bisect_right(intervals, intEnd, key=lambda it: it[0], lo=posL)
        if posR <= n and posR > 0 and intervals[posR - 1][1] > intEnd:
            intEnd = intervals[posR - 1][1] # correct new interval's end if merge required

        return intervals[:posL] + [[intStart, intEnd]] + intervals[posR:]
