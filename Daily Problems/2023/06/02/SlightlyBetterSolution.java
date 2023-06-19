package playground;

import java.util.LinkedList;
import java.util.Queue;

// Based off of NeetCodeIO's solution.
// https://www.youtube.com/watch?v=8NPbAvVXKR4
// Same DFS solution I came up with, but now we just cache the distances.
public class SlightlyBetterSolution {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;

        double[][] distances = new double[n][n];
        for (int i = 0; i < n; i++) {
            distances[i][i] = 0;
            for (int j = i + 1; j < n; j++) {
                double distance = computeDistance(bombs[i], bombs[j]);
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }

        boolean[][] adjacency = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distances[i][j] <= bombs[i][2]) {
                    adjacency[i][j] = true;
                }
            }
        }

        int maxChainLength = 0;
        for (int i = 0; i < n; i++) {
            int chainLength = computeChainLength(i, adjacency);
            if (chainLength > maxChainLength) {
                maxChainLength = chainLength;
            }
        }
        return maxChainLength;
    }

    private double computeDistance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    private int computeChainLength(int i, boolean[][] adjacency) {
        int n = adjacency.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        q.add(i);
        visited[i] = true;
        int chainLength = 1;
        while (!q.isEmpty()) {
            int j = q.remove();
            visited[j] = true;
            for (int k = 0; k < n; k++) {
                if (!visited[k] && adjacency[j][k]) {
                    q.add(k);
                    visited[k] = true;
                    chainLength++;
                }
            }
        }

        return chainLength;
    }
}
