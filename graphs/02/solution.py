from collections import deque
from typing import Optional


class Node:
    def __init__(self, val=0, neighbors=None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []


class Solution:
    def cloneGraph(self, head: Optional[Node]) -> Optional[Node]:
        if head is None:
            return None

        copies: dict[int, Node] = {}
        seen: set[int] = set()
        traversal_queue = deque()

        def get_or_create_neighbor_copy(val: int) -> Node:
            neighbor_copy = copies.get(val, None)
            if neighbor_copy is None:
                neighbor_copy = Node(val, [])
                copies[val] = neighbor_copy
            return neighbor_copy

        head_copy = get_or_create_neighbor_copy(head.val)
        seen.add(head.val)
        traversal_queue.append((head, head_copy))
        while len(traversal_queue) > 0:
            top, top_copy = traversal_queue.popleft()
            for neighbor in top.neighbors:
                neighbor_copy = get_or_create_neighbor_copy(neighbor.val)
                top_copy.neighbors.append(neighbor_copy)
                if neighbor.val not in seen:
                    seen.add(neighbor.val)
                    traversal_queue.append((neighbor, neighbor_copy))

        return head_copy
