/*
Problem:
A message is encoded using '1' to '9' for 'A' to 'I' and '10' to '26'
for 'J' to 'Z'. Given a string of digits, count how many ways it can be
decoded. A leading zero makes a group invalid.

Approach (normal DP):
Let dp[i] = number of ways to decode the first i characters.
Base case: dp[0] = 1 (one way to decode an empty string).
Transition for position i (looking at digit s[i-1]):
 - If s[i-1] != '0', it can stand alone, so add dp[i-1].
 - If the two-digit number s[i-2..i-1] is between 10 and 26, it can be
   one letter, so add dp[i-2].

Why this works:
Each digit can finish a decode either as a single letter or, when valid,
as part of a two-digit letter. We sum both possibilities from already
solved smaller prefixes.

Time Complexity:
O(n) - one pass over the string.

Space Complexity:
O(n) for the dp array. (Can be reduced to O(1) with two variables.)
*/

import java.util.*;

class DecodeWays {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = (s.charAt(0) != '0') ? 1 : 0;

        for (int i = 2; i <= n; i++) {
            char one = s.charAt(i - 1);
            char two = s.charAt(i - 2);

            if (one != '0') {
                dp[i] += dp[i - 1];
            }

            int twoDigit = (two - '0') * 10 + (one - '0');
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
