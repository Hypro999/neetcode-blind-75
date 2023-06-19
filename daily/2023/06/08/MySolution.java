class MySolution {
    public int countNegatives(int[][] grid) {
        int count = 0;
        final int n = grid.length;
        final int m = grid[0].length;

        int i = n - 1;
        int j = 0;
        while (i > -1) {
            while (j < m && grid[i][j] > -1) {
                j++;
            }
            if (j == m) {
                // This is just a heuristic and does not affect the runtime complexity.
                return count;
            }
            count += m - j;
            i--;
        }

        return count;
    }
}