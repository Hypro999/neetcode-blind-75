class Solution:
    def minWindow(self, s: str, t: str) -> str:
        t_freq_map = self.build_freq_map(t)
        window_freq_map = self.build_empty_freq_map()

        start = 0  # incl
        end = 0  # excl
        best_start = 0
        best_end = len(s) + 1  # This weird value indicates that no match was found.
        for ch in s:
            self.add_to_freq_map(window_freq_map, ch)
            end += 1

            while self.is_superset(window_freq_map, t_freq_map):
                if end - start < best_end - best_start:
                    best_start = start
                    best_end = end
                self.remove_from_freq_map(window_freq_map, s[start])
                start += 1

        if best_end == len(s) + 1:
            return ""

        return s[best_start:best_end]

    def build_empty_freq_map(self):
        return [0 for _ in range(26 * 2)]

    def build_freq_map(self, s):
        freq_map = self.build_empty_freq_map()
        for ch in s:
            self.add_to_freq_map(freq_map, ch)
        return freq_map

    def char_to_key(self, ch):
        if ord(ch) >= ord("a") and ord(ch) <= ord("z"):
            return ord(ch) - ord("a")
        return ord(ch) - ord("A") + 26

    def add_to_freq_map(self, freq_map, ch):
        key = self.char_to_key(ch)
        freq_map[key] += 1

    def remove_from_freq_map(self, freq_map, ch):
        key = self.char_to_key(ch)
        freq_map[key] -= 1

    def is_superset(self, window_freq_map, t_freq_map):
        for v1, v2 in zip(window_freq_map, t_freq_map):
            if v1 < v2:
                return False
        return True
