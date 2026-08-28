/*
Problem:
We are given k sorted linked lists and we must combine them into one single
sorted linked list. Each input list is already sorted on its own, and the
final list must be sorted too.

Approach:
Use a min-heap (PriorityQueue) that always hands us the smallest node among
the heads of all the lists.

We start by putting the head node of every non-empty list into the heap.
Then we repeatedly take the smallest node off the top, attach it to our
result, and if that node had a next node, we push that next node into the
heap. We keep going until the heap is empty, which means every list is
fully drained.

Why this works:
Because every list is already sorted, the very next candidate to add to the
merged result is always one of the current head nodes. The min-heap lets us
find the smallest of those k heads in O(log k) instead of scanning all k by
hand every time, which keeps the whole merge efficient.

Time Complexity:
O(N log k) where N is the total number of nodes across all lists and k is
the number of lists. Every node is inserted and removed from the heap once.
Space Complexity:
O(k) for the heap (we only store at most one node per list).
*/

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min-heap of list nodes, ordered by their value so the smallest
        // node currently available sits at the top.
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // Put the head of each list into the heap.
        for (ListNode head : lists) {
            if (head != null) pq.offer(head);
        }

        ListNode dummy = new ListNode(0); // helps avoid null checks
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll(); // the next node in sorted order
            tail.next = smallest;
            tail = tail.next;

            // If that node had a next, its next becomes a new candidate.
            if (smallest.next != null) {
                pq.offer(smallest.next);
            }
        }

        return dummy.next;
    }
}
