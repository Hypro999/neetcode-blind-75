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

class Solution {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode middle = findMiddle(head);
        ListNode latter = middle.next;
        middle.next = null;
        latter = reverse(latter);
        mash(head, latter);
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast.next == null) {
                break;
            }
            fast = fast.next;
            if (fast.next == null) {
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode lag = head;
        ListNode lead = lag.next;
        while (lead != null) {
            ListNode next = lead.next;
            lead.next = lag;
            lag = lead;
            lead = next;
        }
        head.next = null;
        return lag;
    }

    private void mash(ListNode first, ListNode second) {
        ListNode nextFirst, nextSecond;
        while (second != null) {
            nextFirst = first.next;
            nextSecond = second.next;
            first.next = second;
            second.next = nextFirst;
            first = nextFirst;
            second = nextSecond;
        }
    }
}
