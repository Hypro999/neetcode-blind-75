import java.util.HashMap;
import java.util.Map;

public class MySolution {

    private class Details {
        boolean computed;

        int sequenceLength;

        Details() {
            this.computed = false;
            this.sequenceLength = 1;
        }
    }

    public int longestConsecutive(int[] nums) {
        final int n = nums.length;

        // Initialize sequence lengths.
        Map<Integer, Details> sequenceLengths = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Details details = sequenceLengths.get(nums[i]);
            if (details == null) {
                details = new Details();
                sequenceLengths.put(nums[i], details);
            }
        }

        // Find the longest sequence length.
        int longestSequenceLength = 0;
        for (int i = 0; i < n; i++) {
            int sequenceLength = determineSequenceLength(sequenceLengths, nums[i]);
            if (sequenceLength > longestSequenceLength) {
                longestSequenceLength = sequenceLength;
            }
        }

        return longestSequenceLength;
    }

    private int determineSequenceLength(Map<Integer, Details> sequenceLengths, int target) {
        Details details = sequenceLengths.get(target);

        // Base cases.
        if (details == null) {
            return 0;
        } else if (details.computed) {
            // DP / cache.
            return details.sequenceLength;
        }

        // Recursive case.
        int subSequenceLength = determineSequenceLength(sequenceLengths, target - 1);
        details.computed = true;
        details.sequenceLength += subSequenceLength;
        return details.sequenceLength;
    }
}