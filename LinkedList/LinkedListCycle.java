/*
Problem:
Given the head of a linked list, return true if the list has a cycle,
meaning some node can be reached again by following next pointers.

Approach:
Use two pointers, a slow one that moves one step at a time and a fast one
that moves two steps. If there is a cycle, the fast pointer will
eventually lap the slow pointer and they will meet.

Why this works:
In a cycle the fast pointer gains one node on the slow pointer each step,
so it must eventually catch up. If there is no cycle, the fast pointer
reaches the end (null) and we stop.

Time Complexity:
O(n) in the worst case we traverse the list once.

Space Complexity:
O(1) only two pointers are used.
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }
}
