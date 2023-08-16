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

public class MySolution {

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode middle = findMiddle(head);
        ListNode secondHalfHead = middle.next;
        middle.next = null;

        ListNode reversedSecondHalfHead = reverse(secondHalfHead);

        ListNode curr = head;
        head = head.next;
        while (head != null && reversedSecondHalfHead != null) {
            curr.next = reversedSecondHalfHead;
            reversedSecondHalfHead = reversedSecondHalfHead.next;
            curr = curr.next;

            curr.next = head;
            head = head.next;
            curr = curr.next;
        }
        if (reversedSecondHalfHead != null) {
            // For when the length of the input linked list is even.
            curr.next = reversedSecondHalfHead;
        }
    }

    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
            if (fast == null) {
                break;
            }
            slow = slow.next;
        }

        return slow;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode a = null;
        ListNode b = head;
        while (b != null) {
            ListNode c = b.next;
            b.next = a;
            a = b;
            b = c;
        }

        return a;
    }
}