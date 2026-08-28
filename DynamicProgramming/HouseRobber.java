/*
Problem:
You are a robber planning to rob houses arranged in a line. Each house
has some amount of money. You cannot rob two adjacent houses (an alarm
would trigger). Find the maximum amount you can rob.

Approach:
For each house i, you have two choices:
 - Skip it: the best you can get is the best up to the previous house,
   which is dp[i-1].
 - Rob it: you add its money to the best up to two houses back, dp[i-2].
So dp[i] = max(dp[i-1], nums[i] + dp[i-2]).

dp[i] means: the maximum money you can rob from the first i+1 houses.

Why this works:
At every house we pick the better of robbing it (plus non-adjacent
earlier loot) or leaving it (keeping earlier best). The optimal choice
carries forward.

Time Complexity:
O(n) - one pass over the array.

Space Complexity:
O(n) for dp. (Can be reduced to O(1) with two variables.)
*/

import java.util.*;

class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[n - 1];
    }
}
