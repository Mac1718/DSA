/*
Problem:
Given an array of positive numbers, can you split it into two subsets
that have the same total sum?

Approach (normal DP):
If the total sum is odd, it is impossible. Otherwise our target is half
the total. Now the problem becomes: can we pick some numbers that sum
exactly to target?
Let dp[s] = true if a subset summing to s can be formed.
Base case: dp[0] = true (empty subset sums to 0).
For each number x, scan sums from target down to x and update
dp[s] = dp[s] || dp[s - x]. Going backwards avoids reusing the same
number twice.

Why this works:
We are checking the subset-sum question. If we can reach exactly half
the total, the remaining numbers make up the other half automatically.

Time Complexity:
O(n * target) where target is half the total sum.

Space Complexity:
O(target) for the dp array.
*/

import java.util.*;

class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int x : nums) {
            total += x;
        }
        if (total % 2 != 0) {
            return false;
        }
        int target = total / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int x : nums) {
            for (int s = target; s >= x; s--) {
                if (dp[s - x]) {
                    dp[s] = true;
                }
            }
        }
        return dp[target];
    }
}
