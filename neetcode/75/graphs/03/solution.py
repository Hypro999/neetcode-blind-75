PACIFIC_REACHABLE = 0b01
ATLANTIC_REACHABLE = 0b10
BOTH_REACHABLE = 0b11


class Solution:
    __slots__ = ("rows", "cols", "heights", "reachability", "seen")

    def pacificAtlantic(self, heights: list[list[int]]) -> list[list[int]]:
        self.rows = len(heights)
        self.cols = len(heights[0])
        self.heights = heights
        self.reachability = [[0] * self.cols for _ in range(self.rows)]

        # top right corner.
        self.dfsSet(0, self.cols - 1, BOTH_REACHABLE, -1)

        # bottom left corner.
        self.dfsSet(self.rows - 1, 0, BOTH_REACHABLE, -1)

        # top edge
        for c in range(0, self.cols - 1, 1):
            self.dfsSet(0, c, PACIFIC_REACHABLE, -1)

        # left edge
        for r in range(0, self.rows - 1, 1):
            self.dfsSet(r, 0, PACIFIC_REACHABLE, -1)

        # right edge.
        for r in range(1, self.rows, 1):
            self.dfsSet(r, self.cols - 1, ATLANTIC_REACHABLE, -1)

        # bottom edge.
        for c in range(1, self.cols, 1):
            self.dfsSet(self.rows - 1, c - 1, ATLANTIC_REACHABLE, -1)

        results = []
        for r in range(self.rows):
            for c in range(self.cols):
                if self.reachability[r][c] == BOTH_REACHABLE:
                    results.append([r, c])
        return results

    def dfsSet(self, r: int, c: int, val: int, prev: int) -> None:
        if r < 0 or r >= self.rows or c < 0 or c >= self.cols:
            return
        if self.reachability[r][c] & val == val:
            return
        if self.heights[r][c] < prev:
            return
        self.reachability[r][c] |= val
        self.dfsSet(r - 1, c, val, self.heights[r][c])
        self.dfsSet(r + 1, c, val, self.heights[r][c])
        self.dfsSet(r, c - 1, val, self.heights[r][c])
        self.dfsSet(r, c + 1, val, self.heights[r][c])
