class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end


class Solution:
    def canAttendMeetings(self, intervals: list[Interval]) -> bool:
        if len(intervals) == 0:
            return True
        intervals.sort(key=lambda x: x.end)
        prev_interval = intervals[0]
        for i in range(1, len(intervals)):
            interval = intervals[i]
            if interval.start < prev_interval.end:
                return False
            prev_interval = interval
        return True
