class Solution {
    public int findCircleNum(int[][] isConnected) {
        int islandCount = 0;
        int n = isConnected.length;
        boolean[] isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (isVisited[i]) {
                continue;
            }
            islandCount++;
            traverseIsland(i, n, isConnected, isVisited);
        }
        return islandCount;
    }

    private void traverseIsland(int i, int n, int[][] isConnected, boolean[] isVisited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(i);
        isVisited[i] = true;
        while (!stack.isEmpty()) {
            int top = stack.pop();
            for (int j = 0; j < n; j++) {
                if (isVisited[j] || isConnected[top][j] == 0) {
                    continue;
                }
                stack.push(j);
                isVisited[j] = true;
            }
        }
    }
}
