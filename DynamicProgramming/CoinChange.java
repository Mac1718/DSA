/*
Problem:
You are given coins of different values and a total amount. Return the
fewest number of coins needed to make up that amount. If it cannot be
made, return -1.

Approach (normal DP):
Let dp[a] = the minimum coins needed to make amount a.
Start with dp[0] = 0 (zero coins for zero amount) and all others at a
large value (infinity).
For every coin value c, go through amounts from c up to the target and
update: dp[a] = min(dp[a], dp[a - c] + 1). This means "use one coin c,
then the best way to make the remaining a - c".

Why this works:
Trying every coin at every amount and always keeping the smaller count
builds the optimal answer from optimal sub-amounts.

Time Complexity:
O(amount * coins) - for each coin we scan the amounts.

Space Complexity:
O(amount) for the dp array.
*/

import java.util.*;

class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // fill with a value bigger than any possible answer
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        dp[0] = 0;

        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                if (dp[a - coin] + 1 < dp[a]) {
                    dp[a] = dp[a - coin] + 1;
                }
            }
        }

        if (dp[amount] > amount) {
            return -1;
        }
        return dp[amount];
    }
}
