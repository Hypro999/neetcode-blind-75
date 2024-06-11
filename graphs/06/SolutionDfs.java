public class SolutionDfs {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] seen = new boolean[n];
        int numConnectedComponents = 0;
        for (int i = 0; i < n; i++) {
            if (seen[i]) {
                continue;
            }
            dfs(i, isConnected, seen);
            numConnectedComponents++;
        }
        return numConnectedComponents;
    }

    private void dfs(int i, int[][] isConnected, boolean[] seen) {
        seen[i] = true;
        for (int j = 0; j < seen.length; j++) {
            if (seen[j] || isConnected[i][j] == 0) {
                continue;
            }
            dfs(j, isConnected, seen);
        }
    }
}
