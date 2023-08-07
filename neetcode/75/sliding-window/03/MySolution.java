import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySolution {

    public int characterReplacement(String s, int k) {

        // O(N) time to build an index.
        HashMap<Character, List<Integer>> positions = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            List<Integer> chPositions = positions.get(ch);
            if (chPositions == null) {
                chPositions = new ArrayList<>();
                positions.put(ch, chPositions);
            }
            chPositions.add(i);
        }

        // O(N) time to use the index to determine the best answer.
        // In this part, no element in the original string is visted more than twice.
        // Once to enter the window. Once to leave it.
        int answer = 1;
        for (List<Integer> chPositions : positions.values()) {
            answer = Math.max(answer, characterReplacement(s, k, chPositions));
        }

        return answer;
    }

    private int characterReplacement(String s, int k, List<Integer> chPositions) {
        int start = 0;
        int end = 1;
        int kRemaining = k;
        int longest = 1 + Math.min(k, s.length() - 1);
        // ^ This initial value is the lowest we can ever expect.
        int[] jumpCosts = new int[chPositions.size()];

        while (end < chPositions.size()) {
            int jumpCost = chPositions.get(end) - chPositions.get(end - 1) - 1;
            jumpCosts[end - 1] = jumpCost;

            kRemaining -= jumpCost;
            while (kRemaining < 0) {
                kRemaining += jumpCosts[start];
                start++;
            }

            int len = chPositions.get(end) - chPositions.get(start) + 1
                    + Math.max(
                            Math.min(
                                    kRemaining,
                                    chPositions.get(start)
                                            + (s.length() - 1 - chPositions.get(end))),
                            0);
            if (len > longest) {
                longest = len;
            }

            end++;
        }
        return longest;
    }
}