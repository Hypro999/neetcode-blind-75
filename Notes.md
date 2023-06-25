### Min Heap in Java and Python:

    - Python: heapq and normal lists
    - Java: PriorityQueue

### Heaps and time complexity:

    - heapify takes O(lg(n)) time.
    - While heapsort takes O(nlg(n)) time, it's possible to build a heap out or randomly ordered
    elements in O(n) time. Some non-trivial math is needed to prove this though.
    - https://levelup.gitconnected.com/how-to-build-a-heap-in-linear-time-complexity-c923466c6fbd

### Queue in any language:

    -   A list

### Stack in Java and Python:

    - Python: Deque
    - Java: Stack
        - Comparator.reverseOrder()

### Deque in Java:

    - ArrayDeque and LinkedList both implement the Deque interface

## Java specific Notes:

-   Streams: mapping to primitive values
    -   There are map() counterparts like mapToInt().
-   Stream to array: .toArray() no need for collectors.
-   Array to stream: Arrays.asList(int[] {1, 2, 3}) will create a single list with a single
    element: the full array. This is a gotcha. Instead you need to manually create a new
    ArrayList and populate it.
