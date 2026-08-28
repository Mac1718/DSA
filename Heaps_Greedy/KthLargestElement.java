/*
Problem:
Given an unsorted array of numbers and an integer k, return the kth largest
element in the array. The kth largest means, for example, if k = 2 we want
the second biggest number. (Note: this is kth largest by value, not by
distinct value.)

Approach:
There are two common ways to solve this.

1) Min-heap (PriorityQueue) of size k:
   We walk through the array and keep a small heap that always holds the k
   largest numbers we have seen so far. A min-heap lets us quickly drop the
   smallest of those k numbers whenever the heap grows past k. After looking
   at every number, the smallest thing left in the heap is the kth largest.

2) Quickselect:
   This picks a random "pivot", splits the array into smaller / equal /
   larger parts, and only recurses into the side that contains the kth
   largest. On average it runs in O(n) time and uses O(1) extra space.

Why this works:
The min-heap method works because the heap only ever keeps the k biggest
candidates. Once it has exactly k items, the root (the minimum of the heap)
is by definition the kth largest overall. Quickselect works because after
partitioning, we know exactly how many numbers are bigger than the pivot,
so we can ignore the half that can't contain our answer.

Time Complexity:
Min-heap: O(n log k). Quickselect: average O(n), worst case O(n^2).
Space Complexity:
Min-heap: O(k). Quickselect: O(1) average (in-place).
*/

import java.util.PriorityQueue;

class Solution {
    // Method 1: using a PriorityQueue (min-heap) of size k.
    public int findKthLargest(int[] nums, int k) {
        // A min-heap: the smallest element sits at the top.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : nums) {
            pq.offer(num);
            // If the heap grew bigger than k, remove the current smallest.
            // That keeps the k largest numbers seen so far inside the heap.
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // The heap now holds the k largest numbers; the smallest of them
        // (the root) is exactly the kth largest.
        return pq.peek();
    }

    // Method 2: quickselect (uncomment to use instead of the heap version).
    // public int findKthLargest(int[] nums, int k) {
    //     // kth largest is the (n - k)th smallest if we count from 0.
    //     return quickselect(nums, 0, nums.length - 1, nums.length - k);
    // }
    //
    // private int quickselect(int[] nums, int left, int right, int target) {
    //     int pivot = nums[right];
    //     int store = left;
    //     for (int i = left; i < right; i++) {
    //         if (nums[i] <= pivot) {
    //             int tmp = nums[i]; nums[i] = nums[store]; nums[store] = tmp;
    //             store++;
    //         }
    //     }
    //     int tmp = nums[store]; nums[store] = nums[right]; nums[right] = tmp;
    //
    //     if (store == target) return nums[store];
    //     if (store < target) return quickselect(nums, store + 1, right, target);
    //     return quickselect(nums, left, store - 1, target);
    // }
}
