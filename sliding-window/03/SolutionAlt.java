import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionAlt {

    public static void main(String[] args) {
        System.out.println(new SolutionAlt().characterReplacement("ABAB", 0) == 1);
        System.out.println(new SolutionAlt().characterReplacement("ABAB", 1) == 3);
        System.out.println(new SolutionAlt().characterReplacement("ABAB", 2) == 4);
        System.out.println(new SolutionAlt().characterReplacement("AABABBA", 1) == 4);
        System.out.println(new SolutionAlt().characterReplacement("AAAA", 2) == 4);
        System.out.println(new SolutionAlt().characterReplacement("ABCDE", 1) == 2);
        System.out.println(new SolutionAlt().characterReplacement("ABBB", 2) == 4);
        System.out.println(new SolutionAlt().characterReplacement("BAAAB", 2) == 5);
        System.out.println(new SolutionAlt().characterReplacement("AABCCCBAABB", 4) == 7);
    }

    public int characterReplacement(String s, int k) {
        Map<Character, List<Integer>> jumpListMap = buildJumpListMap(s);

        // O(n) since each index will be visted at most twice:
        int maxVal = 0;
        for (List<Integer> jumpList : jumpListMap.values()) {
            int val = getLongestReplaceableRepeatingFor(jumpList, s.length(), k);
            if (val > maxVal) {
                maxVal = val;
            }
        }
        return maxVal;
    }

    // O(n):
    Map<Character, List<Integer>> buildJumpListMap(String s) {
        int n = s.length();
        Map<Character, List<Integer>> jumpListMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            List<Integer> jumpList = jumpListMap.get(ch);
            if (jumpList == null) {
                jumpList = new ArrayList<>();
                jumpListMap.put(ch, jumpList);
            }
            jumpList.add(i);
        }
        return jumpListMap;
    }

    int getLongestReplaceableRepeatingFor(List<Integer> jumpList, int n, int k) {
        int m = jumpList.size();
        int bandwidth = k;

        int windowStart = 0;
        int windowEnd = 1;
        int longestReplaceableRepeatingSequence = 1;

        int replaceableRepeatingSequence = 1;
        for (; windowEnd < m; windowEnd++) {
            int consumptionRequirement = jumpList.get(windowEnd) - jumpList.get(windowEnd - 1) - 1;

            // If we can't expand without exceeding the bandwidth then try to maximize how far the
            // current window can reach with the remaining bandwidth before advancing the window.
            if (bandwidth < consumptionRequirement) {
                int extraJuice =
                        juiceAvailableFromRemainingBandwidth(
                                jumpList, bandwidth, windowStart, windowEnd, n);
                replaceableRepeatingSequence = replaceableRepeatingSequence + extraJuice;
                if (replaceableRepeatingSequence > longestReplaceableRepeatingSequence) {
                    longestReplaceableRepeatingSequence = replaceableRepeatingSequence;
                }
            }

            // Consume and then pay the price if needed.
            bandwidth = bandwidth - consumptionRequirement;
            while (bandwidth < 0) {
                int freeableBandwidth =
                        jumpList.get(windowStart + 1) - jumpList.get(windowStart) - 1;
                bandwidth = bandwidth + freeableBandwidth;
                windowStart++;
            }

            replaceableRepeatingSequence = jumpList.get(windowEnd) - jumpList.get(windowStart) + 1;
            if (replaceableRepeatingSequence > longestReplaceableRepeatingSequence) {
                longestReplaceableRepeatingSequence = replaceableRepeatingSequence;
            }
        }

        int extraJuice =
                juiceAvailableFromRemainingBandwidth(
                        jumpList, bandwidth, windowStart, windowEnd, n);
        replaceableRepeatingSequence = replaceableRepeatingSequence + extraJuice;
        if (replaceableRepeatingSequence > longestReplaceableRepeatingSequence) {
            longestReplaceableRepeatingSequence = replaceableRepeatingSequence;
        }

        return longestReplaceableRepeatingSequence;
    }

    // Try to consume before the window and after the window.
    int juiceAvailableFromRemainingBandwidth(
            List<Integer> jumpList, int bandwidth, int windowStart, int windowEnd, int n) {
        int remainingBandwidth = bandwidth;
        int juiceAvailable = 0;

        int consumptionRequirement = Math.min(jumpList.get(windowStart), bandwidth);
        if (consumptionRequirement <= remainingBandwidth) {
            remainingBandwidth = remainingBandwidth - consumptionRequirement;
            juiceAvailable += consumptionRequirement;
        }

        consumptionRequirement = Math.min(n - 1 - jumpList.get(windowEnd - 1), bandwidth);
        if (consumptionRequirement <= remainingBandwidth) {
            remainingBandwidth = remainingBandwidth - consumptionRequirement;
            juiceAvailable += consumptionRequirement;
        }

        return juiceAvailable;
    }
}
