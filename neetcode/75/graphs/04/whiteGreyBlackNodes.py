from dataclasses import dataclass, field
from enum import Enum
from typing import Self


class Color(Enum):
    WHITE = 1
    GREY = 2
    BLACK = 3


@dataclass(slots=True)
class Node:
    value: int
    color: Color = Color.WHITE
    neighbors: list[Self] = field(default_factory=list)

    def __hash__(self) -> int:
        return self.value


# For undirected graphs:
# Keep track of the parent node (as a parameter to whiteGreyBlack)
# and just don't recurse down the parent path (treat it as if it were Color.BLACK).
def whiteGreyBlack(node: Node) -> bool:
    if node.color == Color.GREY:
        # We've found a loop.
        return True
    if node.color == Color.BLACK:
        # We've already traversed it, don't waste time repeating this path.
        return False
    node.color = Color.GREY
    for dst in node.neighbors:
        if whiteGreyBlack(dst):
            return True
    node.color = Color.BLACK
    return False


class Solution:

    def canFinish(self, numCourses: int, prerequisites: list[list[int]]) -> bool:
        node_map = [Node(i) for i in range(numCourses)]
        for src, dst in prerequisites:
            node_map[src].neighbors.append(node_map[dst])
        for node in node_map:
            if node.color == Color.WHITE:  # Minor optimization to avoid function call.
                if whiteGreyBlack(node):
                    return False
        return True
