import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();

        Arrays.sort(nums);

        int lastSeen = nums[0] - 1;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == lastSeen) {
                continue;
            }
            lastSeen = nums[i];
            System.out.println(i);
            twoSum(nums, i, triplets);
        }

        return triplets;
    }

    private void twoSum(int[] nums, int startExclusive, List<List<Integer>> triplets) {
        int target = -nums[startExclusive];
        if (startExclusive + 1 >= nums.length) {
            return;
        }
        int i = startExclusive + 1;
        int lastSeenI = nums[i] - 1;
        int j = nums.length - 1;
        int lastSeenJ = nums[j] + 1;
        while (i < j) {
            if (nums[i] == lastSeenI) {
                i++;
                continue;
            }
            if (nums[j] == lastSeenJ) {
                j--;
                continue;
            }
            int sum = nums[i] + nums[j];
            if (sum < target) {
                lastSeenI = nums[i];
                i++;
                continue;
            }
            if (sum > target) {
                lastSeenJ = nums[j];
                j--;
                continue;
            }
            triplets.add(Arrays.asList(nums[startExclusive], nums[i], nums[j]));
            lastSeenI = nums[i];
            lastSeenJ = nums[j];
            i++;
            j--;
        }
    }
}