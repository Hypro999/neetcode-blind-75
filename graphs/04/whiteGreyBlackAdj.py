from enum import Enum


class Color(Enum):
    WHITE = 1
    GREY = 2
    BLACK = 3


class Solution:
    def canFinish(self, numCourses: int, prerequisites: list[list[int]]) -> bool:
        adj_matrix = [[False] * numCourses for _ in range(numCourses)]
        colors = [Color.WHITE] * numCourses
        for src, dst in prerequisites:
            adj_matrix[src][dst] = True
        for src in range(len(adj_matrix)):
            if self.whiteGreyBlack(adj_matrix, colors, src):
                return False
        return True

    def whiteGreyBlack(
        self, adj_matrix: list[list[bool]], colors: list[Color], src: int
    ) -> bool:
        """Return true if a cycle was detected, false otherwise."""
        if colors[src] == Color.GREY:
            # We've found a loop.
            return True
        if colors[src] == Color.BLACK:
            # We've already traversed it, don't waste time repeating this path.
            return False
        colors[src] = Color.GREY
        for dst in range(len(adj_matrix)):
            if adj_matrix[src][dst]:
                if self.whiteGreyBlack(adj_matrix, colors, dst):
                    return True
        colors[src] = Color.BLACK
        return False
