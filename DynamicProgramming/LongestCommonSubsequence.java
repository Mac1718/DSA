/*
Problem:
Given two strings, find the length of their longest common subsequence.
A subsequence keeps order but can skip characters.

Approach (normal DP):
Let dp[i][j] = length of the longest common subsequence of text1's first
i characters and text2's first j characters.
Base case: dp[0][*] = dp[*][0] = 0 (empty string has no subsequence).
Transition:
 - If text1[i-1] == text2[j-1], they match: dp[i][j] = dp[i-1][j-1] + 1.
 - Else, drop one character from either side and take the better:
   dp[i][j] = max(dp[i-1][j], dp[i][j-1]).

Why this works:
When characters match we extend the common subsequence by one. When they
do not, the best we can do is the best ignoring one of the two ends.

Time Complexity:
O(m * n) for the two nested loops.

Space Complexity:
O(m * n) for the 2D dp table.
*/

import java.util.*;

class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
