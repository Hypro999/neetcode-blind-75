class Solution:

    def pacificAtlantic(self, heights: list[list[int]]) -> list[list[int]]:
        ROWS = len(heights)
        COLS = len(heights[0])

        PACIFIC = 0b01
        ATLANTIC = 0b10
        BOTH = 0b11

        # this will also double as "seen"
        reachable = [[0] * COLS for _ in range(ROWS)]

        directions = [(-1, 0), (0, -1), (1, 0), (0, 1)]

        def in_bounds(i, j):
            return i > -1 and i < ROWS and j > -1 and j < COLS

        def fill(i, j, val):
            reachable[i][j] |= val
            for ii, jj in directions:
                y, x = i + ii, j + jj
                if (
                    (not in_bounds(y, x))
                    or (reachable[y][x] & val == val)
                    or (heights[i][j] > heights[y][x])
                ):
                    continue
                fill(y, x, val)

        # backtrack from the top and bottom
        for col in range(0, COLS):
            fill(0, col, PACIFIC)
            fill(ROWS - 1, col, ATLANTIC)

        # Backtrack from the top and bottom.
        # We cover the top right and bottom left corners twice. This can be avoided.
        # But we want super readable code here. So, as long as the time complexity
        # isn't impacted, whatever goes goes.
        for row in range(0, ROWS):
            fill(row, 0, PACIFIC)
            fill(row, COLS - 1, ATLANTIC)

        return [
            [i, j] for j in range(COLS) for i in range(ROWS) if reachable[i][j] == BOTH
        ]
