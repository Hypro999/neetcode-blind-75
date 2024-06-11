class ListNode {

    int val;

    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class SolutionSimple {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int sz = getLength(head);
        int k = sz - n; // Index of the node we need to remove.

        // Removing the head is a bit of a special case.
        if (k == 0) {
            return head.next;
        }

        // Go till the node before the node to be removed.
        ListNode cur = head;
        while (k > 1) {
            cur = cur.next;
            k--;
        }

        // Disconnect said node.
        cur.next = (cur.next == null ? null : cur.next.next);

        return head;
    }

    int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }
}
