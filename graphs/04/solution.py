class Node:
    __slots__ = "value", "neighbors"

    def __init__(self, value) -> None:
        self.value: int = value
        self.neighbors: list[Node] = []


# Assumption: If A requires B, and B requires C, then [A, C] is not in prerequisites.
# Prerequisites should have only [A, B] and [B, C].
class Solution:
    def canFinish(self, numCourses: int, prerequisites: list[list[int]]) -> bool:
        seen = [False for _ in range(numCourses)]
        courses = [Node(courseId) for courseId in range(numCourses)]
        for y, x in prerequisites:
            courses[y].neighbors.append(courses[x])

        def hasLoops(node: Node) -> bool:
            if node is None or courses[node.value] is None:
                return False
            if seen[node.value]:
                return True
            seen[node.value] = True
            _hasLoops = False
            for neighbor in node.neighbors:
                if hasLoops(neighbor):
                    _hasLoops = True
                    break
            seen[node.value] = False
            courses[node.value] = None
            return _hasLoops

        for i in range(numCourses):
            node = courses[i]
            if hasLoops(node):
                return False
        return True
