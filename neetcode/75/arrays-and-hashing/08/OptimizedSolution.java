import java.util.HashSet;
import java.util.Set;

/** This is more optimized because it takes less memory. */
public class OptimizedSolution {

    public int longestConsecutive(int[] nums) {
        final int n = nums.length;

        Set<Integer> existingNumbers = new HashSet<>();
        for (int i = 0; i < n; i++) {
            existingNumbers.add(nums[i]);
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

    private int determineSequenceLength(int target, Set<Integer> existingNumbers) {
        int sequenceLength = 1;
        existingNumbers.remove(target);

        int cursor = target + 1;
        while (existingNumbers.contains(cursor)) {
            existingNumbers.remove(cursor);
            sequenceLength++;
            cursor++;
        }

        cursor = target - 1;
        while (existingNumbers.contains(cursor)) {
            existingNumbers.remove(cursor);
            sequenceLength++;
            cursor--;
        }

        return sequenceLength;
    }
}