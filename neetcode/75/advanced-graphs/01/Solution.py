import unittest
from dataclasses import dataclass, field
from functools import reduce
from typing import Optional, Self


@dataclass(slots=True)
class Node:
    value: str
    rank: int = -1
    children: set[Self] = field(default_factory=set)

    def __str__(self) -> str:
        return self.value

    def __hash__(self) -> int:
        return ord(self.value)


class Solution:

    def derive(self, input: list[str]) -> str:
        if not input:
            return ""
        node_map = self.construct_graph(input)
        loop_found = self.rank_and_find_loops(node_map)
        if loop_found:
            return ""
        brackets = self.construct_rank_brackets(node_map.values())
        return self.rank_brackets_to_string(brackets)

    # Preconditions:
    #   1. len(input) > 0
    # Possible optimizations:
    #   1. Better map: instead of using a dict, use a fixed size list as a map with
    #      ch - 'a' as the hash function.
    def construct_graph(self, input: list[str]) -> dict[str, Node]:
        node_map: dict[str, Node] = {}
        for ch in input[0]:
            node_map[ch] = Node(ch)
        for i in range(1, len(input)):
            first_word = input[i - 1]
            second_word = input[i]
            first_ch, second_ch = self.find_char_pair_after_common_prefix(
                first_word, second_word
            )
            if first_ch == "" or second_ch == "":
                continue
            first_node = self.get_or_create_node(first_ch, node_map)
            second_node = self.get_or_create_node(second_ch, node_map)
            first_node.children.add(second_node)
        return node_map

    # Preconditions:
    #   1. Neither first_word nor second_word are None.
    def find_char_pair_after_common_prefix(
        self, first_word: str, second_word: str
    ) -> tuple[str, str]:
        for i in range(0, min(len(first_word), len(second_word))):
            first_word_ch = first_word[i]
            second_word_ch = second_word[i]
            if first_word_ch != second_word_ch:
                return (first_word_ch, second_word_ch)
        return ("", "")

    # Mutations:
    #   1. Nodes in node_map will be mutated to set their rank values.
    # Returns true if a loop was found, else false.
    def rank_and_find_loops(self, node_map: dict[str, Node]) -> bool:

        def get_rank(node: Node, depth: int) -> Optional[int]:
            if depth >= len(node_map):
                # Loop detected.
                return None
            if node.rank != -1:
                return node.rank
            max_child_rank = -1
            for child in node.children:
                child_rank = get_rank(child, depth + 1)
                if child_rank == None:
                    return None
                if child_rank > max_child_rank:
                    max_child_rank = child_rank
            node.rank = max_child_rank + 1
            return node.rank

        for node in node_map.values():
            rank = get_rank(node, 0)
            if rank == None:
                return True

        return False

    # Preconditions:
    #   1. len(nodes) > 0
    def construct_rank_brackets(self, nodes: set[Node]) -> list[list[str]]:
        max_rank = reduce(lambda a, b: max(a, b.rank), nodes, 0)
        brackets = [[] for _ in range(max_rank + 1)]
        for node in nodes:
            brackets[node.rank].append(node.value)
        return brackets

    def rank_brackets_to_string(self, brackets: list[list[str]]) -> Node:
        chars = []
        for bracket in brackets[::-1]:
            for char in bracket:
                chars.append(char)
        return "".join(chars)

    def get_or_create_node(self, key: str, map: dict[str, Node]) -> Node:
        if key in map:
            return map[key]
        val = Node(key)
        map[key] = val
        return val


class TestSolution(unittest.TestCase):

    def test_linear(self):
        input = ["wrt", "wrf", "er", "ett", "rftt"]
        want = "wertf"
        solution = Solution()
        got = solution.derive(input)
        self.assertEqual(want, got)

    def test_linear_disjoint_imbalanced(self):
        input = ["wrt", "wrf", "er", "ett"]
        want = "rwtfe"  # Just one of multiple possible solutions.
        solution = Solution()
        got = solution.derive(input)
        self.assertEqual(want, got)

    def test_choose_max_rank_of_children(self):
        input = ["ab", "bc", "c"]
        want = "abc"
        solution = Solution()
        got = solution.derive(input)
        self.assertEqual(want, got)

    def test_diamond(self):
        input = ["x", "ax", "ad", "ab", "b", "c"]
        want = "xadbc"
        solution = Solution()
        got = solution.derive(input)
        self.assertEqual(want, got)

    def test_connected_branches_basic(self):
        input = ["a", "b", "c", "cd", "ce", "cf", "cg", "cgb", "cgf"]
        want = "adbefcg"  # Just one of multiple possible solutions.
        solution = Solution()
        got = solution.derive(input)
        self.assertEqual(want, got)

    def test_connected_branches_advanced(self):
        input = ["d", "e", "f", "g", "gd", "gb", "gf", "gc", "gca", "gcb"]
        want = "daebfgc"  # Just one of multiple possible solutions.
        solution = Solution()
        got = solution.derive(input)
        self.assertEqual(want, got)

    def test_small_loop(self):
        input = ["z", "x", "z"]
        want = ""
        solution = Solution()
        got = solution.derive(input)
        self.assertEqual(want, got)


if __name__ == "__main__":
    unittest.main()
