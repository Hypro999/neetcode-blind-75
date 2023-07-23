import java.util.ArrayList;
import java.util.List;

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        final int n = position.length;

        List<Integer[]> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pairs.add(new Integer[] { position[i], speed[i] });
        }
        pairs.sort((p1, p2) -> p2[0] - p1[0]);

        double lastTimeToReachTarget = -1;
        int numFleets = n; // Begin with assumption that all cars are their own fleet.
        for (Integer[] pair : pairs) {
            int carPosition = pair[0];
            int carSpeed = pair[1];

            double timeToReachTarget = (target - carPosition) / (double) carSpeed;

            if (timeToReachTarget <= lastTimeToReachTarget) {
                // Add to existing fleet.
                numFleets--;
            } else {
                // Establish a new fleet.
                lastTimeToReachTarget = timeToReachTarget;
            }
        }

        return numFleets;
    }
}