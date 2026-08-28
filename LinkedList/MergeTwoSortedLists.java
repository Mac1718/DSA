/*
Problem:
You are given the heads of two sorted linked lists. Merge them into one
sorted linked list and return its head.

Approach:
Use a dummy node to build the result easily. Compare the front of both
lists, take the smaller one, and move that list's pointer forward. Once
one list is empty, attach the rest of the other list.

Why this works:
Because both input lists are already sorted, always picking the smallest
available front node keeps the merged list sorted. The dummy node lets us
avoid special handling for the very first node.

Time Complexity:
O(n + m) where n and m are the lengths of the two lists.

Space Complexity:
O(1) extra space, we only reuse the existing nodes.
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        return dummy.next;
    }
}
