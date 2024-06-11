class Solution {
    public String minWindow(String s, String t) {
        if (s == "" || t == "") {
            return "";
        }

        int[] tFreqMap = new int[26 * 2];
        for (int i = 0; i < t.length(); i++) {
            updateFreq(tFreqMap, t.charAt(i), 1);
        }

        int[] sFreqMap = new int[26 * 2];
        int start = 0; // inclusive.
        int end = 0; // exclusive.
        int minStart = -1; // inclusive.
        int minEnd = -1; // exclusive.
        int minLength = Integer.MAX_VALUE;
        final int m = s.length();

        while (end < m) {
            // Expectation: We don't have a match.

            while (end < m && !contains(sFreqMap, tFreqMap)) {
                updateFreq(sFreqMap, s.charAt(end), 1);
                end++;
            }

            // Expectation: We might have a match.

            while (contains(sFreqMap, tFreqMap)) {
                if (end - start < minLength) {
                    minStart = start;
                    minEnd = end;
                    minLength = end - start;
                }
                updateFreq(sFreqMap, s.charAt(start), -1);
                start++;
            }

            // At this point, it's possible that start == end, in which case we will never
            // have a match so long as t != "".
        }

        if (minLength == Integer.MAX_VALUE) {
            // We never found any match.
            return "";
        }
        return s.substring(minStart, minEnd);
    }

    private void updateFreq(int[] freqMap, char ch, int amt) {
        if (ch >= 'a' && ch <= 'z') {
            freqMap[26 + ch - 'a'] += amt;
        } else {
            freqMap[ch - 'A'] += amt;
        }
    }

    private boolean contains(int[] superMap, int[] subMap) {
        for (int i = 0; i < superMap.length; i++) {
            if (superMap[i] < subMap[i]) {
                return false;
            }
        }
        return true;
    }
}
