public class BetterSolution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode lag = head;
        ListNode lead = head;
        for (int i = 0; i < n; i++) {
            lead = lead.next;
        }
        if (lead == null) {
            return lag.next;
        }
        while (lead.next != null) {
            lead = lead.next;
            lag = lag.next;
        }
        lag.next = lag.next.next;
        return head;
    }
}