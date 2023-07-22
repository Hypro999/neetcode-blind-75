import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// This solution is O(n^2) for the average case and O(n^3) for the worst case - when every value
// in the array is 0.
class MyFailedSolution {
    public List<List<Integer>> threeSum(int[] nums) {
        final int n = nums.length;
        Set<Double> seenTriplets = new HashSet<>(); // Store hashes for triplets we've already seen.
        List<List<Integer>> triplets = new ArrayList<>();

        Map<Integer, Set<Integer>> numsMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            Set<Integer> indicies = numsMap.get(num);
            if (indicies == null) {
                indicies = new HashSet<Integer>();
                numsMap.put(num, indicies);
            }
            indicies.add(i);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int pairSum = nums[i] + nums[j];
                int target = -pairSum;
                Set<Integer> targetIndicies = numsMap.get(target);

                if (targetIndicies == null) {
                    continue;
                }

                for (int k : targetIndicies) {
                    if (k == i || k == j) {
                        continue;
                    }
                    List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                    // Sort the triplet for the hash function to work. The hash function is
                    // dependent on the order of elements.
                    triplet.sort((a, b) -> a - b);
                    double tripletHash = hash(triplet);
                    if (!seenTriplets.contains(tripletHash)) {
                        seenTriplets.add(tripletHash);
                        triplets.add(triplet);
                    }
                }
            }
        }

        return triplets;
    }

    // In my original solution, I couldn't get a hash function that would always output a unique
    // value for 3 integers, so I looked it up online and found the "Cantor pairing function".
    private double hash(List<Integer> ints) {
        int a = ints.get(0);
        int b = ints.get(1);
        int c = ints.get(2);
        double hab = 0.5 * (a + b) * (a + b + 1) + b;
        double habc = 0.5 * (hab + c) * (hab + c + 1) + c;
        return habc;
    }
}
