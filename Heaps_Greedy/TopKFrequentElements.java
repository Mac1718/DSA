/*
Problem:
Given an array of numbers and an integer k, return the k most frequent
elements. For example, if 1 appears 5 times and 2 appears 3 times, and
k = 1, we return [1]. The answer can be in any order.

Approach:
There are two common ways.

1) Bucket sort:
   First count how many times each number appears (using a HashMap). Then
   create an array of "buckets" where the index is a frequency and the
   bucket holds all numbers with that frequency. We scan the buckets from
   the highest frequency down and collect the first k numbers we find.

2) Max-heap (PriorityQueue):
   Count frequencies, then put every (number, frequency) pair into a heap
   ordered so the most frequent is on top. Pop k times.

Why this works:
Bucket sort works because frequencies are bounded by the array length, so
we can place each number directly into a slot by its count and then walk
backwards from the biggest frequency. The heap version works because each
pop gives us the next most frequent element for free.

Time Complexity:
Bucket sort: O(n). Max-heap: O(n log n).
Space Complexity:
O(n) for both.
*/

import java.util.*;

class Solution {
    // Method 1: bucket sort.
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: count how often each number shows up.
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // Step 2: buckets[f] holds numbers that appear exactly f times.
        // The biggest possible frequency is nums.length.
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int num : count.keySet()) {
            int freq = count.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }

        // Step 3: walk from the highest frequency down, grabbing numbers
        // until we have k of them.
        int[] result = new int[k];
        int idx = 0;
        for (int freq = buckets.length - 1; freq >= 0 && idx < k; freq--) {
            if (buckets[freq] != null) {
                for (int num : buckets[freq]) {
                    result[idx++] = num;
                    if (idx == k) break;
                }
            }
        }
        return result;
    }

    // Method 2: using a PriorityQueue (max-heap).
    // public int[] topKFrequent(int[] nums, int k) {
    //     HashMap<Integer, Integer> count = new HashMap<>();
    //     for (int num : nums) {
    //         count.put(num, count.getOrDefault(num, 0) + 1);
    //     }
    //
    //     // Heap ordered by frequency (bigger frequency = closer to top).
    //     PriorityQueue<Integer> pq = new PriorityQueue<>(
    //         (a, b) -> count.get(b) - count.get(a)
    //     );
    //     pq.addAll(count.keySet());
    //
    //     int[] result = new int[k];
    //     for (int i = 0; i < k; i++) {
    //         result[i] = pq.poll();
    //     }
    //     return result;
    // }
}
