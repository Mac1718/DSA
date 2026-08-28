/*
Problem:
Given a singly linked list, reorder it to: L0 -> Ln -> L1 -> Ln-1 -> L2 ->
Ln-2 ... So the first node, then the last, then the second, then the
second last, and so on.

Approach:
1. Find the middle of the list with slow/fast pointers.
2. Reverse the second half.
3. Merge the two halves by taking one node from each in turn.

Why this works:
Splitting at the middle and reversing the back half turns the problem into
weaving two lists together, which is easy with two pointers. The two halves
are about equal length so the interleave works out exactly.

Time Complexity:
O(n) for finding the middle, reversing, and merging.

Space Complexity:
O(1) we only rearrange the existing nodes.
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: find the middle.
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: reverse the second half.
        ListNode second = slow.next;
        slow.next = null;

        ListNode prev = null;
        ListNode curr = second;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        second = prev;

        // Step 3: merge the two halves.
        ListNode first = head;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            first.next = second;
            second.next = tmp1;

            first = tmp1;
            second = tmp2;
        }
    }
}
