class Solution:

    # Basically, loop detection in an undirected graph. Followed by a quick
    # connected-ness check.
    def validTree(self, n: int, edges: list[list[int]]) -> bool:
        adj = self.to_adjacency_matrix(n, edges)

        WHITE = 0
        GREY = 1
        BLACK = 2
        colors = [WHITE] * n

        def contains_cycle(i, parent):
            if colors[i] == GREY:
                return True
            if colors[i] == BLACK:
                return False
            colors[i] = GREY
            for j in range(n):
                if not adj[i][j] or j == parent:
                    continue
                if contains_cycle(j, i):
                    return True
            colors[i] = BLACK
            return False

        # If the graph were connected, then all nodes would have
        # been visted and colored black.
        if contains_cycle(0, None):
            return False

        # If any node is not colored black, then the graph isn't
        # connected.
        for color in colors:
            if color is not BLACK:
                return False

        return True

    def to_adjacency_matrix(self, num_verticies, edges):
        adj = [[False] * num_verticies for _ in range(num_verticies)]
        for src, dst in edges:
            adj[src][dst] = True
            adj[dst][src] = True
        return adj
