class ListNode {

    int val;

    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

// Floyd's Tortoise and the Hare Algorithm.
public class Solution {

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode tortoise = head;
        ListNode hare = head;
        while (true) {
            hare = hare.next;
            if (hare == null) {
                return false;
            }
            hare = hare.next;
            if (hare == null) {
                return false;
            }
            tortoise = tortoise.next;
            if (tortoise == hare) {
                break;
            }
        }

        // At this point, to find the first node of the loop, move either
        // animal/pointer back to the start and then have them both move
        // with the same cadence of 1. The point where they meet will be
        // the start of the loop.

        return true;
    }
}
