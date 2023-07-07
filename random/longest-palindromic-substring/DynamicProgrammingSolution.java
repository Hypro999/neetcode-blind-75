public class DynamicProgrammingSolution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int longestStart = 0; // inclusive.
        int longestEnd = 0; // inclusive.
        boolean[][] isPalindrome = new boolean[n][n];
        // ^ We will use only the upper triangle.

        // Initialize all palindromes of size 1 and 2.
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i] = true;
            if (s.charAt(i) == s.charAt(i + 1)) {
                isPalindrome[i][i + 1] = true;
                longestStart = i;
                longestEnd = i + 1;
            }
        }
        isPalindrome[n - 1][n - 1] = true;

        // Traverse by diagonal.
        for (int diagonal = 2; diagonal < n; diagonal++) {
            for (int i = 0, j = diagonal; j < n; i++, j++) {
                if (isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    isPalindrome[i][j] = true;
                    longestStart = i;
                    longestEnd = j;
                }
            }
        }

        return s.substring(longestStart, longestEnd + 1);
    }
}
