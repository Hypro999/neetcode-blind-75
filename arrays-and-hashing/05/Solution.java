import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            int frequency = frequencyMap.getOrDefault(num, 0);
            frequencyMap.put(num, frequency + 1);
        }

        List<Entry<Integer, Integer>> frequencyPairs = new ArrayList<>(frequencyMap.entrySet());
        frequencyPairs.sort((e1, e2) -> e2.getValue() - e1.getValue());

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = frequencyPairs.get(i).getKey();
        }

        return result;
    }
}
