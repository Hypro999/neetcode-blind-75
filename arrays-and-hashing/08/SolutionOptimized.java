import java.util.HashMap;
import java.util.Map;

public class SolutionOptimized {

    public int longestConsecutive(int[] nums) {
        final int n = nums.length;

        Map<Integer, Integer> existingNumbers = new HashMap<>();
        for (int i = 0; i < n; i++) {
            existingNumbers.put(nums[i], null);
        }

        // Find the longest sequence length;
        int longestSequenceLength = 0;
        for (int i = 0; i < n; i++) {
            int sequenceLength = determineSequenceLength(nums[i], existingNumbers);
            if (sequenceLength > longestSequenceLength) {
                longestSequenceLength = sequenceLength;
            }
        }

        return longestSequenceLength;
    }

    private int determineSequenceLength(int target, Map<Integer, Integer> existingNumbers) {

        // Base case: we find a value that's not in existingNumbers.
        if (!existingNumbers.containsKey(target)) {
            return 0;
        }

        Integer sequenceLength = existingNumbers.get(target);
        if (sequenceLength == null) {
            // Cache miss. Compute it.
            sequenceLength = determineSequenceLength(target + 1, existingNumbers) + 1;
            existingNumbers.put(target, sequenceLength);
        }

        return sequenceLength;
    }
}
