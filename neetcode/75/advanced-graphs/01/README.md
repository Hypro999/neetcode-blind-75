# 269: Alien Dictionary

https://leetcode.com/problems/alien-dictionary/

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

If the multiple orders can be derived, return any valid order.

If no orders can be applied, return "".


### Notes

- A simpler way to implement this would be to just perform a post-order DFS and reverse the final string
    - https://www.youtube.com/watch?v=6kTZYvNNyps
    - Avoid cycles with a seen[] updated as a wrapper around the recursive call.
    - You actually need 2 seen[] values - one to indicate that it was consumed, and another value to say that it was seen in the current branch

- Another cool algorithm that's quite similar to my "rank" approach is "Kahn's Algorithm"
    - https://yuminlee2.medium.com/topological-sort-cf9f8e43af6a
    - It uses BFS and the idea of idea of "indegree"
        - Similar to, but is another way of looking at, my approach - which was DFS + "rank" 
