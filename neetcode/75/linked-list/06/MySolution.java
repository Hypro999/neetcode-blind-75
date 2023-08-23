import java.util.PriorityQueue;

class ListNode {

    int val;

    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        ListNode head = new ListNode();
        PriorityQueue<ListNode> heap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }

        ListNode tail = head;
        while (!heap.isEmpty()) {
            ListNode node = heap.remove();
            if (node.next != null) {
                heap.add(node.next);
            }
            tail.next = node;
            tail = node;
        }

        // The first value is just a sentinal, skip it.
        return head.next;
    }
}