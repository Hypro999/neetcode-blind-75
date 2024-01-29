class Solution:
    def numIslands(self, grid: list[list[str]]) -> int:
        count = 0
        rows = len(grid)
        cols = len(grid[0])

        # Destructive operation:
        def dfsConsume(r: int, c: int) -> None:
            if r < 0 or c < 0 or r >= rows or c >= cols or (grid[r][c] == "0"):
                return
            grid[r][c] = "0"
            dfsConsume(r - 1, c)
            dfsConsume(r + 1, c)
            dfsConsume(r, c - 1)
            dfsConsume(r, c + 1)

        for r in range(0, rows):
            for c in range(0, cols):
                if (grid[r][c] == "1"):
                    count += 1
                    dfsConsume(r, c)

        return count