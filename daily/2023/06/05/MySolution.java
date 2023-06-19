class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        double divisor = coordinates[1][0] - coordinates[0][0];
        if (divisor == 0) {
            return checkVertical(coordinates);
        }
        double targetSlope = (coordinates[1][1] - coordinates[0][1]) / divisor;
        for (int i = 2; i < coordinates.length; i++) {
            double slope = (coordinates[i][1] - coordinates[0][1])
                    / (double) (coordinates[i][0] - coordinates[0][0]);
            if (slope != targetSlope) {
                return false;
            }
        }
        return true;
    }

    private boolean checkVertical(int[][] coordinates) {
        for (int i = 1; i < coordinates.length; i++) {
            if (coordinates[i][0] - coordinates[i - 1][0] != 0) {
                return false;
            }
        }
        return true;
    }
}