package playground;

import java.util.LinkedList;
import java.util.Queue;

public class MySolution {
    public int maximumDetonation(int[][] bombs) {
        int maxChainLength = 0;
        for (int i = 0; i < bombs.length; i++) {
            int chainLength = computeChainLength(i, bombs);
            if (chainLength > maxChainLength) {
                maxChainLength = chainLength;
            }
        }
        return maxChainLength;
    }

    private int computeChainLength(int i, int[][] bombs) {
        int n = bombs.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        q.add(i);
        visited[i] = true;
        int chainLength = 1;
        while (!q.isEmpty()) {
            int j = q.remove();
            visited[j] = true;
            for (int k = 0; k < bombs.length; k++) {
                if (!visited[k] && inRange(bombs[k], bombs[j])) {
                    q.add(k);
                    visited[k] = true;
                    chainLength++;
                }
            }
        }

        return chainLength;
    }

    private boolean inRange(int[] target, int[] source) {
        double distance = Math.sqrt(
                Math.pow(source[0] - target[0], 2) + Math.pow(source[1] - target[1], 2));
        return distance < source[2];
    }
}
