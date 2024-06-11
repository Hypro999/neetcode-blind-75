import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> complements = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer complementIndex = complements.get(complement);
            if (complementIndex != null) {
                return new int[] { complementIndex, i };
            }
            complements.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }
}