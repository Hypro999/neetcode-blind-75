import java.util.HashMap;

public class Solution {
    public int climbStairs(int n) {
        HashMap<Integer, Integer> ways = new HashMap<>();
        ways.put(1, 1);
        ways.put(2, 2);
        for (int i = 3; i <= n; i++) {
            ways.put(i, ways.get(i - 1) + ways.get(i - 2));
        }
        return ways.get(n);
    }
}
