from dataclasses import dataclass
from typing import Self


@dataclass
class TreeNode:
    val: int
    left: Self
    right: Self

    def __init__(self, val: int) -> None:
        self.val = val


class Solution:

    # Solve using a simple iterative in-order traversal.
    def kthSmallest(self, root: TreeNode, k: int) -> int:
        leftseen = [root]
        lookahead = root.left
        while len(leftseen) > 0:
            if lookahead is None:
                lookahead = leftseen.pop()
                k -= 1
                if k == 0:
                    return lookahead.val
                lookahead = lookahead.right
            if lookahead is not None:
                leftseen.append(lookahead)
                lookahead = lookahead.left
        return -1
