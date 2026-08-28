/*
Problem:
You are climbing a staircase. It takes n steps to reach the top.
Each time you can take either 1 step or 2 steps. Count the number of
distinct ways you can climb to the top.

Approach:
Think about the last move. To reach step n, your last move was either a
1-step from n-1 or a 2-step from n-2. So the number of ways to reach
step n is just ways(n-1) + ways(n-2). That is the Fibonacci pattern.
We store the counts in an array dp where dp[i] is the number of ways to
reach step i.

Why this works:
Every path to step n is made of valid smaller paths to n-1 or n-2, and
we count all of them without overlap, so adding them is correct.

Time Complexity:
O(n) - we fill the array once.

Space Complexity:
O(n) for the dp array. (Can be reduced to O(1) with two variables.)
*/

import java.util.*;

class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
