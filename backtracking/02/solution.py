class Solution:

    def __init__(self):
        self.board = None
        self.word = None
        self.num_rows = 0
        self.num_cols = 0
        self.directions = [(-1, 0), (0, -1), (0, 1), (1, 0)]
        self.seen = None

    def exist(self, board: list[list[str]], word: str) -> bool:
        self.board = board
        self.word = word
        self.num_rows = len(board)
        self.num_cols = len(board[0])
        self.seen = [[False] * self.num_cols for _ in range(self.num_rows)]
        for i in range(self.num_rows):
            for j in range(self.num_cols):
                if self.dfs(i, j, 0):
                    return True
        return False

    def dfs(self, i: int, j: int, k: int) -> bool:
        if self.board[i][j] != self.word[k] or self.seen[i][j]:
            return False
        k += 1
        if k == len(self.word):
            return True
        self.seen[i][j] = True
        for delta_i, delta_j in self.directions:
            next_i, next_j = i + delta_i, j + delta_j
            if (
                next_i < 0
                or next_i >= self.num_rows
                or next_j < 0
                or next_j >= self.num_cols
            ):
                continue
            if self.dfs(next_i, next_j, k):
                return True
        self.seen[i][j] = False
        return False
