class TwoPointerSolution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int start = 0;
        int end = 0;

        for (int center = 0; center < n; center++) {
            for (int i = center - 1, j = center + 1; i > -1 && j < n; i--, j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    break;
                }
                if (j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }

        // This time ""center" refers to the right side of a 2-char long center.
        for (int center = 1; center < n; center++) {
            if (s.charAt(center - 1) != s.charAt(center)) {
                continue;
            }
            if (end - start < 1) {
                start = center - 1;
                end = center;
            }
            for (int i = center - 2, j = center + 1; i > -1 && j < n; i--, j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    break;
                }
                if (j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }
}