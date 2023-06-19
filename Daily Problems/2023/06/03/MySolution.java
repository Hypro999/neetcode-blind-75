import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MySolution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> heirarchy = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<Integer> subordinates = heirarchy.get(manager[i]);
            if (subordinates == null) {
                subordinates = new ArrayList<>();
                heirarchy.put(manager[i], subordinates);
            }
            subordinates.add(i);
        }

        int totalTimeNeeded = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(headID);
        while (!q.isEmpty()) {
            int employeeId = q.remove();
            List<Integer> subordinates = heirarchy.get(employeeId);
            if (subordinates == null) {
                if (informTime[employeeId] > totalTimeNeeded) {
                    totalTimeNeeded = informTime[employeeId];
                }
                continue;
            }
            for (Integer subordinateId : subordinates) {
                q.add(subordinateId);
                informTime[subordinateId] += informTime[employeeId];
            }
        }

        return totalTimeNeeded;
    }
}
