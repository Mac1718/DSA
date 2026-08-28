/*
Problem:
We have a list of tasks (letters like 'A', 'B', 'C') that a CPU must run.
The same task cannot run again until n other tasks (or idle slots) have
passed. We want the smallest number of time units needed to finish all
tasks. For example, with tasks ['A','A','A','B','B','B'] and n = 2, after
running A we must wait for 2 different things before A can run again.

Approach:
Greedy scheduling with a max-heap.

The greedy idea: always run the task that still has the most remaining
copies, because leaving the most frequent task for later forces long idle
waiting. We use a max-heap (PriorityQueue) that stores how many copies of
each task remain, so the most frequent task is always on top.

We process the work in "rounds" of length (n + 1). In each round we pull
up to n + 1 tasks from the heap and run them once each. After the round,
any task that still has copies left goes back into the heap. If the heap
runs out before the round finishes, those leftover slots are idle time.

Why this works:
By always choosing the most frequent task first and spacing identical tasks
exactly n apart, we avoid ever being forced into extra idle gaps. The math
shortcut (see below) confirms the greedy plan is optimal: the total time is
at least (maxCount - 1) * (n + 1) + (number of tasks tied for the max).

Time Complexity:
O(n log u) where n is the number of tasks and u is the number of distinct
tasks (at most 26 for uppercase letters).
Space Complexity:
O(u), the size of the frequency map and heap.
*/

import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Count how many of each task we have.
        int[] freq = new int[26];
        for (char t : tasks) {
            freq[t - 'A']++;
        }

        // Max-heap of remaining counts: the biggest count is on top,
        // so we always pick the task we still need to do the most.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int c : freq) {
            if (c > 0) pq.offer(c);
        }

        int time = 0;
        while (!pq.isEmpty()) {
            // One round can hold at most (n + 1) different tasks.
            List<Integer> left = new ArrayList<>();
            int round = Math.min(n + 1, pq.size());

            for (int i = 0; i < round; i++) {
                int count = pq.poll();
                time++;
                if (count - 1 > 0) {
                    left.add(count - 1); // still has copies to do later
                }
            }

            // Put the survivors back so the next round can use them.
            pq.addAll(left);
        }
        return time;
    }

    // Shortcut version using the same greedy reasoning, no heap needed:
    // the answer is max( tasks.length, (maxCount - 1) * (n + 1) + maxTied ).
    // public int leastInterval(char[] tasks, int n) {
    //     int[] freq = new int[26];
    //     int maxCount = 0, maxTied = 0;
    //     for (char t : tasks) {
    //         freq[t - 'A']++;
    //         if (freq[t - 'A'] > maxCount) {
    //             maxCount = freq[t - 'A'];
    //             maxTied = 1;
    //         } else if (freq[t - 'A'] == maxCount) {
    //             maxTied++;
    //         }
    //     }
    //     int withIdle = (maxCount - 1) * (n + 1) + maxTied;
    //     return Math.max(tasks.length, withIdle);
    // }
}
