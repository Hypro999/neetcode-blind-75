import heapq


class MedianFinder:

    def __init__(self):
        self.left = []  # max heap as a min heap of inverted numbers
        self.right = []  # min heap

    def addNum(self, num: int) -> None:
        if len(self.left) == 0:
            self.left.append(-num)
            return

        # Put the number into the correct heap
        left_val = -self.left[0]
        if num < left_val:
            heapq.heappush(self.left, -num)
        else:
            heapq.heappush(self.right, num)

        # Rebalance heaps as needed
        if len(self.left) > len(self.right) + 1:
            transfer_val = -heapq.heappop(self.left)
            heapq.heappush(self.right, transfer_val)
        elif len(self.right) > len(self.left):
            transfer_val = heapq.heappop(self.right)
            heapq.heappush(self.left, -transfer_val)

    def findMedian(self) -> float:
        if len(self.left) == len(self.right):
            return (-self.left[0] + self.right[0]) / 2
        return -self.left[0]
