class Solution {

    // Ugly-ass code but it works.
    public List<Integer> spiralOrder(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int rightLimit = numCols;
        int bottomLimit = numRows;
        int leftLimit = -1;
        int topLimit = 0;

        int i = 0;
        int j = 0;
        List<Integer> solution = new ArrayList<>();
        int layerCounter = Math.min(numRows, numCols) - 1;
        if (layerCounter == 0) {
            layerCounter = 1;
        }
        while (layerCounter > 0) {
            // Right
            if (j == rightLimit) {
                break;
            }
            while (j < rightLimit) {
                System.out.printf("right %d%n", matrix[i][j]);
                solution.add(matrix[i][j]);
                j++;
            }
            j--;
            i++;
            rightLimit--;

            // Down
            if (i == bottomLimit) {
                break;
            }
            while (i < bottomLimit) {
                System.out.printf("down %d%n", matrix[i][j]);
                solution.add(matrix[i][j]);
                i++;
            }
            i--;
            j--;
            bottomLimit--;

            // Left
            if (j == leftLimit) {
                break;
            }
            while (j > leftLimit) {
                System.out.printf("left %d%n", matrix[i][j]);
                solution.add(matrix[i][j]);
                j--;
            }
            j++;
            i--;
            leftLimit++;

            // Up
            if (i == topLimit) {
                break;
            }
            while (i > topLimit) {
                System.out.printf("up %d%n", matrix[i][j]);
                solution.add(matrix[i][j]);
                i--;
            }
            i++;
            j++;
            topLimit++;

            layerCounter--;
        }

        return solution;
    }
}