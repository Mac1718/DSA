/*
Problem:
Given two words, find the minimum number of operations to turn word1
into word2. Allowed operations: insert a character, delete a character,
or replace a character (each costs 1).

Approach (normal DP):
Let dp[i][j] = minimum operations to convert word1's first i chars into
word2's first j chars.
Base cases:
 - dp[0][j] = j (insert all j chars)
 - dp[i][0] = i (delete all i chars)
Transition:
 - If word1[i-1] == word2[j-1], no operation needed: dp[i][j] = dp[i-1][j-1].
 - Else take the cheapest of insert, delete, replace:
   dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]).

Why this works:
We align the two words left to right. Matching chars cost nothing; a
mismatch costs one operation and we keep the cheapest prior alignment.

Time Complexity:
O(m * n) for the two nested loops.

Space Complexity:
O(m * n) for the 2D dp table.
*/

import java.util.*;

class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];
                    int best = Math.min(insert, delete);
                    best = Math.min(best, replace);
                    dp[i][j] = 1 + best;
                }
            }
        }
        return dp[m][n];
    }
}
