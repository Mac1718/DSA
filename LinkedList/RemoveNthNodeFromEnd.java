/*
Problem:
Given the head of a linked list and an integer n, remove the nth node from
the end of the list and return the new head.

Approach:
Use two pointers with a gap of n nodes between them. Move the fast pointer
forward n steps first, then move both pointers together until fast reaches
the last node. The slow pointer now sits just before the node to remove.

Why this works:
Keeping a fixed gap of n between the pointers means that when the fast
pointer hits the end, the slow pointer points right before the target
node, so we can skip it with slow.next = slow.next.next. A dummy node
handles removing the very first node cleanly.

Time Complexity:
O(n) we pass through the list at most twice.

Space Complexity:
O(1) only a few pointers are used.
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }
}
