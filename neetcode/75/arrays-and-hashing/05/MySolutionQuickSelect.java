import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

class MySolutionQuickSelect {

    public int[] topKFrequent(int[] nums, int k) {
        List<Entry<Integer, Integer>> frequencyPairs = generateFrequencyPairs(nums);
        int kthLargestFrequency = quickSelect(frequencyPairs, k);
        // Here, we will rely on the input constraint "it is guaranteed that the answer is unique".
        // This means that there will never be too many elements with the same frequency as the
        // kthLargestFrequency.
        return frequencyPairs.stream()
                .filter(pair -> pair.getValue() >= kthLargestFrequency)
                .mapToInt(pair -> pair.getKey())
                .toArray();
    }

    private List<Entry<Integer, Integer>> generateFrequencyPairs(int[] nums) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            int frequency = frequencyMap.getOrDefault(num, 0);
            frequencyMap.put(num, frequency + 1);
        }
        return new ArrayList<>(frequencyMap.entrySet());
    }

    /**
     * @return The frequency of the kth largest element.
     *         (must ensure: 0 <= k < frequencyPairs.size()).
     */
    private int quickSelect(List<Entry<Integer, Integer>> frequencyPairs, int k) {
        final int n = frequencyPairs.size();
        final int targetIndex = n - k;
        int lowerBound = 0;
        int upperBound = n - 1;
        int pivotIndex = -1;
        while (pivotIndex != targetIndex && upperBound > -1 && lowerBound < n) {
            pivotIndex = partition(frequencyPairs, lowerBound, upperBound);
            if (pivotIndex > targetIndex) {
                upperBound = pivotIndex - 1;
            } else if (pivotIndex < targetIndex) {
                lowerBound = pivotIndex + 1;
            }
        }
        return frequencyPairs.get(targetIndex).getValue();
    }

    /**
     * @param lowerBound
     *            the min index to include (inclusive).
     * @param upperBound
     *            the max index to include (inclusive).
     * @return The index the pivot element went into.
     */
    private int partition(
            List<Entry<Integer, Integer>> frequencyPairs, int lowerBound, int upperBound) {

        int newPivotIndex = lowerBound;
        int currentPivotIndex = upperBound;
        Entry<Integer, Integer> pivotValue = frequencyPairs.get(currentPivotIndex);
        for (int i = lowerBound; i < currentPivotIndex; i++) {
            Entry<Integer, Integer> currentValue = frequencyPairs.get(i);
            if (currentValue.getValue() >= pivotValue.getValue()) {
                continue;
            }
            Entry<Integer, Integer> temp = frequencyPairs.get(newPivotIndex);
            frequencyPairs.set(newPivotIndex, currentValue);
            frequencyPairs.set(i, temp);
            newPivotIndex++;
        }
        Entry<Integer, Integer> temp = frequencyPairs.get(newPivotIndex);
        frequencyPairs.set(newPivotIndex, pivotValue);
        frequencyPairs.set(currentPivotIndex, temp);
        return newPivotIndex;
    }
}