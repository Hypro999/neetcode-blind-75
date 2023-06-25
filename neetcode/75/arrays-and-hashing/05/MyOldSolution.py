import heapq
from typing import *


# O(klog(k)) to build the initial heap + O((n - k)log(k)) to add remaining elements to the heap.
# So overall O(nlog(k)).
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        frequencies_map = {}
        for num in nums:
            frequencies_map[num] = frequencies_map.get(num, 0) + 1
        kheap = []
        for num, freq in frequencies_map.items():
            if len(kheap) < k:
                heapq.heappush(kheap, (freq, num))
            else:
                if freq > kheap[0][0]:
                    heapq.heappop(kheap)
                    heapq.heappush(kheap, (freq, num))
        return [x for _, x in kheap]
