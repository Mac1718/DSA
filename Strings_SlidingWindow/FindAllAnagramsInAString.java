/*
Problem:
Given a string s and a pattern p, find all the starting positions in s where a
contiguous substring is an anagram of p. So if p is "abc", any window of s with
exactly the letters a, b, c counts, like "bac" or "cab".

Approach:
We use a sliding window of length equal to p. First we count the letters of p.
Then we slide a window across s, keeping a count of letters in the window. When
the window moves, we add the new right character and remove the old left one.
If the window's letter counts match p's counts, its start index is an answer.

Why this works:
Two strings are anagrams exactly when their letter counts are equal. By moving a
fixed-size window and updating counts in constant time, we can check each position
quickly instead of recounting from scratch.

Time Complexity:
O(|s| + |p|) because each index of s is added and removed at most once.

Space Complexity:
O(26) for the two fixed-size count arrays.
*/

import java.util.ArrayList;
import java.util.List;

class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }

        int[] pCount = new int[26];
        int[] wCount = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
            wCount[s.charAt(i) - 'a']++;
        }

        if (matches(pCount, wCount)) {
            result.add(0);
        }

        for (int i = p.length(); i < s.length(); i++) {
            wCount[s.charAt(i) - 'a']++;
            wCount[s.charAt(i - p.length()) - 'a']--;
            if (matches(pCount, wCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    private boolean matches(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
