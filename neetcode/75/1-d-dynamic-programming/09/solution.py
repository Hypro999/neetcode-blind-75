class TrieNode:
    def __init__(self, exists: bool = False) -> None:
        self.exists = exists
        self.children = [None] * 26


class Solution:
    def wordBreak(self, s: str, wordDict: list[str]) -> bool:
        dp = [False] * len(s)
        wordTrie = self.toTrie(wordDict)
        for i in range(len(s) - 1, -1, -1):
            self.checkSuffixAndUpdate(s, i, wordTrie, dp)
        print(dp)
        return dp[0]

    def toTrie(self, wordDict: list[str]) -> TrieNode:
        head = TrieNode()
        for word in wordDict:
            cur = head
            for c in word:
                key = self.to_key(c)
                if cur.children[key] is None:
                    cur.children[key] = TrieNode()
                cur = cur.children[key]
            cur.exists = True
        return head

    def checkSuffixAndUpdate(
        self, s: str, start: int, wordTrie: TrieNode, dp: list[bool]
    ) -> None:
        cur = wordTrie
        for i in range(start, len(s)):
            cur = cur.children[self.to_key(s[i])]
            if cur is None:
                break
            if cur.exists:
                if i + 1 == len(s) or dp[i + 1]:
                    dp[start] = True
                    return

    def to_key(self, c: str) -> int:
        return ord(c) - ord("a")
