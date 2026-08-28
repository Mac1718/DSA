/*
Problem:
Given the head of a singly linked list, reverse it and return the new head.
For example 1 -> 2 -> 3 becomes 3 -> 2 -> 1.

Approach:
Walk through the list and flip each node's next pointer to point at the
previous node. We keep three pointers: previous, current, and the node
after current so we don't lose the rest of the list.

Why this works:
By saving the next node before we overwrite current.next, we can safely
rewire each link backwards one step at a time until current runs off the
end. The last node we visited becomes the new head.

Time Complexity:
O(n) where n is the number of nodes, since we touch each node once.

Space Complexity:
O(1) extra space, only a few pointers are used.
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
