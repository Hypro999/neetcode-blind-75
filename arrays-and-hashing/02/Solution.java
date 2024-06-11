class Solution {

    public boolean isAnagram(String s, String t) {
        int[] sSignature = getSignature(s);
        int[] tSignature = getSignature(t);
        for (int i = 0; i < 26; i++) {
            if (sSignature[i] != tSignature[i]) {
                return false;
            }
        }
        return true;
    }

    int[] getSignature(String in) {
        int[] signature = new int[26];
        for (int i = 0; i < in.length(); i++) {
            signature[in.charAt(i) - 'a']++;
        }
        return signature;
    }
}
