class Solution {

    public void setZeroes(int[][] matrix) {
        final int ROWS = matrix.length;
        final int COLS = matrix[0].length;

        // It's possible to do this in O(1) memory by reusing the first row
        // and first column of the matrix (plus and extra cell for the (0, 0)
        // row indicator). But that's really not readable so let's start with
        // the O(M + N) space solution until the need arises to go overkill.
        boolean[] rowsToZero = new boolean[ROWS];
        boolean[] colsToZero = new boolean[COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (matrix[i][j] == 0) {
                    rowsToZero[i] = true;
                    colsToZero[j] = true;
                }
            }
        }
        for (int i = 0; i < ROWS; i++) {
            if (rowsToZero[i]) {
                clearRow(matrix, i, COLS);
            }
        }
        for (int j = 0; j < COLS; j++) {
            if (colsToZero[j]) {
                clearCol(matrix, j, ROWS);
            }
        }
    }

    private void clearRow(int[][] matrix, int i, int COLS) {
        for (int j = 0; j < COLS; j++) {
            matrix[i][j] = 0;
        }
    }

    private void clearCol(int[][] matrix, int j, int ROWS) {
        for (int i = 0; i < ROWS; i++) {
            matrix[i][j] = 0;
        }
    }
}
