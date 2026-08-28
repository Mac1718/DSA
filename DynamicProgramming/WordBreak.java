/*
Problem:
Given a string s and a dictionary of words, decide if s can be split
into a sequence of words that all exist in the dictionary.

Approach (normal DP):
Let dp[i] = true if the prefix s[0..i-1] can be formed by dictionary
words.
Base case: dp[0] = true (empty prefix is always possible).
For each position i from 1 to n, and for each j < i, if dp[j] is true
and the piece s[j..i-1] is a word in the dictionary, then dp[i] = true.

Why this works:
If a smaller prefix is breakable and the next chunk is a real word, the
whole prefix is breakable. We build this up to the full string.

Time Complexity:
O(n^2 * w) where n is length of s and w is word lookup cost (we use a
HashSet so lookup is O(1)).

Space Complexity:
O(n) for dp plus O(words) for the set.
*/

import java.util.*;

class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
