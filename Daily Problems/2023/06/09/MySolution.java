class MySolution {
    public char nextGreatestLetter(char[] letters, char target) {
        int lower = 0;
        int upper = letters.length - 1;
        int i = letters.length / 2;
        char result = letters[0];
        while (i >= lower && i <= upper) {
            if (letters[i] > target) {
                result = letters[i];
                upper = i - 1;
            } else {
                lower = i + 1;
            }
            i = (lower + upper) / 2;
        }
        return result;
    }
}