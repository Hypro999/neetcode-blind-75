from dataclasses import dataclass
from typing import Self


def to_key(s: str) -> int:
    if s is None:
        return None
    return ord(s) - ord("a")


@dataclass
class TrieNode:
    value: str
    children: list[Self]

    def __init__(self, value: str=None) -> None:
        self.value = value
        self.children = [None for _ in range(to_key("z") + 1)]


def build_trie(words: list[str]) -> TrieNode:
    head = TrieNode()
    for word in words:
        cursor = head
        for ch in word:
            key = to_key(ch)
            child = cursor.children[key]
            if child is None:
                child = TrieNode()
                cursor.children[key] = child
            cursor = child
        cursor.value = word
    return head


def dfs(
    board: list[list[str]],
    r: int,
    c: int,
    seen: list[list[bool]],
    trie: TrieNode,
    words_found: set[str],
):
    m = len(board)
    n = len(board[0])
    if r < 0 or r >= m or c < 0 or c >= n or seen[r][c]:
        return
    key = to_key(board[r][c])
    child = trie.children[key] 
    if child is None:
        return
    if child.value is not None:
        # Potential optimization: now remove the word from the trie and prune the trie.
        words_found.add(child.value)
    seen[r][c] = True
    dfs(board, r + 1, c, seen, child, words_found)
    dfs(board, r - 1, c, seen, child, words_found)
    dfs(board, r, c + 1, seen, child, words_found)
    dfs(board, r, c - 1, seen, child, words_found)
    seen[r][c] = False


class Solution:
    def findWords(self, board: list[list[str]], words: list[str]) -> list[str]:
        m = len(board)
        n = len(board[0])
        seen = [[False for _ in range(n)] for _ in range(m)]
        head = build_trie(words)
        words_found = set()
        for r in range(m):
            for c in range(n):
                dfs(board, r, c, seen, head, words_found)
        return list(words_found)