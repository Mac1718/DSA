/*
Problem:
Given an unsorted array of numbers, find the length of the longest
strictly increasing subsequence (not necessarily contiguous).

Approach (normal DP):
Let dp[i] = length of the longest increasing subsequence that ends at
index i.
For each i, look at all earlier j < i. If nums[j] < nums[i], then we can
extend the subsequence ending at j, so dp[i] = max(dp[i], dp[j] + 1).
The answer is the biggest value in dp.

Why this works:
Any valid increasing subsequence ending at i must come from some earlier
smaller element j. By checking all of them we capture the longest.

Time Complexity:
O(n^2) - for each i we check all j before it.

Space Complexity:
O(n) for the dp array.
*/

import java.util.*;

class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        int answer = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // each element alone is a subsequence of length 1
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
}
