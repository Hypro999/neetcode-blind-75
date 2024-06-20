class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end


class Solution:

    # Note, the order is important. When sorting, we want to prefer meetings ending
    # over meetings starting because (0,8), (8,10) is not considered an overlap.
    MEETING_END = 0
    MEETING_START = 1

    def minMeetingRooms(self, intervals: list[Interval]) -> int:
        events = []
        for interval in intervals:
            events.append((interval.start, self.MEETING_START))
            events.append((interval.end, self.MEETING_END))
        events.sort()

        counter = 0
        max_counter = 0
        for _, event_type in events:
            if event_type == self.MEETING_START:
                counter += 1
                if counter > max_counter:
                    max_counter = counter
            else:
                counter -= 1
        return max_counter
