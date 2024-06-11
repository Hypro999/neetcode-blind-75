import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int longestSubstring = 0;
        int start = 0;
        Set<Character> seen = new HashSet<Character>();
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            while (seen.contains(ch) && start <= end) {
                seen.remove(s.charAt(start));
                start++;
            }
            seen.add(ch);
            if (seen.size() > longestSubstring) {
                longestSubstring = seen.size();
            }
        }
        return longestSubstring;
    }
}
