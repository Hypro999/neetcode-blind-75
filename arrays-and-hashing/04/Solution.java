import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            String signature = getSignature(s);
            List<String> group = groups.get(signature);
            if (group == null) {
                group = new ArrayList<String>();
                groups.put(signature, group);
            }
            group.add(s);
        }
        return new ArrayList<>(groups.values());
    }

    String getSignature(String in) {
        Integer[] signature = new Integer[26];
        for (int i = 0; i < 26; i++) {
            signature[i] = 0;
        }
        for (int i = 0; i < in.length(); i++) {
            signature[in.charAt(i) - 'a']++;
        }
        return Arrays.asList(signature).stream()
                .map(x -> String.valueOf(x))
                .collect(Collectors.joining(","));
    }
}
