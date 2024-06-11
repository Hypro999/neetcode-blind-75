class UnionFindSolution {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int numConnectedComponents = n;

        int[] parent = new int[n];
        int[] rank = new int[n]; // optional: Union by Rank optimization.

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    numConnectedComponents -= union(i, j, parent, rank);
                }
            }
        }

        return numConnectedComponents;
    }

    private int union(int n1, int n2, int[] parent, int[] rank) {
        int p1 = findParent(n1, parent);
        int p2 = findParent(n2, parent);

        if (p1 == p2) {
            return 0;
        }

        if (rank[p2] > rank[p1]) {
            parent[p1] = p2;
            rank[p2] += rank[p1];
        } else {
            parent[p2] = p1;
            rank[p1] += rank[p2];
        }

        return 1;
    }

    private int findParent(int child, int[] parent) {
        while (child != parent[child]) {
            parent[child] = parent[parent[child]]; // optional: Path Compression optimization.
            child = parent[child];
        }
        return child;
    }
}
