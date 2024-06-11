import java.util.function.Function;

class Solution {
    public boolean isPalindrome(String s) {
        int i = -1;
        int j = s.length();
        i = advance(s, i, x -> x + 1);
        j = advance(s, j, x -> x - 1);

        while (i < j) {
            char char_i = Character.toLowerCase(s.charAt(i));
            char char_j = Character.toLowerCase(s.charAt(j));
            if (char_i != char_j) {
                return false;
            }
            i = advance(s, i, x -> x + 1);
            j = advance(s, j, x -> x - 1);
        }

        return true;
    }

    private int advance(String s, int i, Function<Integer, Integer> updateAction) {
        i = updateAction.apply(i);
        while (isInBounds(s, i)) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                return i;
            }
            i = updateAction.apply(i);
        }
        return i;
    }

    private boolean isInBounds(String s, int i) {
        return i < s.length() && i >= 0;
    }
}
