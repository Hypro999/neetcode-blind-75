class Solution:

    def __init__(self):
        self.MAX_INT = 10**5
        self.mat = 0
        self.num_rows = 0
        self.num_cols = 0

    def updateMatrix(self, mat: list[list[int]]) -> list[list[int]]:
        self.mat = mat
        self.num_rows = len(mat)
        self.num_cols = len(mat[0])
        distances = [[self.MAX_INT] * self.num_cols for _ in range(self.num_rows)]

        # Find the closest distance looking either left or above.
        for row in range(self.num_rows):
            for col in range(self.num_cols):
                if mat[row][col] == 0:
                    distances[row][col] = 0
                    continue
                distances[row][col] = (
                    min(
                        self.safeIndex(distances, row - 1, col),
                        self.safeIndex(distances, row, col - 1),
                    )
                    + 1
                )

        # Find the closest distance looking either right or below.
        # Sometimes, the answer below might have a good solution that incorporates
        # the answer from its left as found during the previous solution.
        # Note: The best solution will always be monotonically headed either N, E, S, W,
        # NE, NW, SE, or SW. It would be pointless to go both left and right when we
        # could just avoid the extra hops.
        for row in range(self.num_rows - 1, -1, -1):
            for col in range(self.num_cols - 1, -1, -1):
                if mat[row][col] == 0:
                    continue
                distances[row][col] = min(
                    distances[row][col],
                    self.safeIndex(distances, row + 1, col) + 1,
                    self.safeIndex(distances, row, col + 1) + 1,
                )
        return distances

    def safeIndex(self, mat, row, col):
        if row < 0 or row >= self.num_rows or col < 0 or col >= self.num_cols:
            return self.MAX_INT
        return mat[row][col]
