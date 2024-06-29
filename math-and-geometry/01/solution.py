class Solution:
    def rotate(self, matrix: list[list[int]]) -> None:
        self.transpose(matrix)
        self.mirror_across_vertical(matrix)

    def transpose(self, matrix: list[list[int]]) -> None:
        n = len(matrix)
        for row_idx in range(0, n):
            for col_idx in range(row_idx, n):
                self.swap(matrix, (row_idx, col_idx), (col_idx, row_idx))

    def mirror_across_vertical(self, matrix: list[list[int]]) -> None:
        n = len(matrix)
        for row_idx in range(0, n):
            for col_idx in range(0, n // 2):
                self.swap(matrix, (row_idx, col_idx), (row_idx, n - 1 - col_idx))

    def swap(
        self, matrix: list[list[int]], src: tuple[int, int], dst: tuple[int, int]
    ) -> None:
        matrix[src[0]][src[1]], matrix[dst[0]][dst[1]] = (
            matrix[dst[0]][dst[1]],
            matrix[src[0]][src[1]],
        )
