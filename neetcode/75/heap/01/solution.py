import heapq


class MedianFinder:
    def __init__(self) -> None:
        self.head: int = None
        self.left: list[int] = []  # max heap as a min heap of inverted numbers
        self.right: list[int] = []  # min heap

    def addNum(self, num: int) -> None:
        if self.head is None:
            self.head = num
            return
        if num < self.head:
            if len(self.left) > len(self.right):
                heapq.heappush(self.right, self.head)
                self.head = -heapq.heappushpop(self.left, -num)
            else:
                heapq.heappush(self.left, -num)
        else:
            if len(self.right) > len(self.left):
                heapq.heappush(self.left, -self.head)
                self.head = heapq.heappushpop(self.right, num)
            else:
                heapq.heappush(self.right, num)

    def findMedian(self) -> float:
        if len(self.left) == len(self.right):
            return self.head
        if len(self.left) > len(self.right):
            top = -self.left[0]
        else:
            top = self.right[0]
        return float(self.head + top) / 2
