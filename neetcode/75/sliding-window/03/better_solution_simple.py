class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        start = 0
        longest = 0
        freq_map = {}  # Using an int[26] would be more efficient, but less readable.
        for end, ch in enumerate(s):
            freq_map[ch] = freq_map.get(ch, 0) + 1
            if self.must_shrink(start, end, k, freq_map):
                freq_map[s[start]] = freq_map[s[start]] - 1
                start += 1
            if end - start + 1 > longest:
                longest = end - start + 1
        return longest

    def must_shrink(self, start, end, k, freq_map):
        most_frequent = max(freq_map.values())
        return most_frequent + k <= end - start
