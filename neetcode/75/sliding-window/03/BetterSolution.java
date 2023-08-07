public class BetterSolution {

    public int characterReplacement(String s, int k) {
        int answer = 1 + Math.min(s.length() - 1, k);
        int[] freqMap = new int[26];

        int start = 0;
        for (int end = 1; end <= s.length(); end++) {
            freqMap[s.charAt(end - 1) - 'A']++;
            int highestFreq = max(freqMap);
            int kRemaining = k - (end - start - highestFreq);
            while (kRemaining < 0) {
                freqMap[s.charAt(start) - 'A']--;
                start++;
                highestFreq = max(freqMap);
                kRemaining = k - (end - start - highestFreq);
            }
            if (end - start > answer) {
                answer = end - start;
            }
        }

        return answer;
    }

    private int max(int[] values) {
        int maxValue = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
            }
        }
        return maxValue;
    }
}