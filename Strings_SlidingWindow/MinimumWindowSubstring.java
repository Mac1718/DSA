/*
Problem:
Given two strings s and t, find the smallest window (contiguous part) of s that
contains every character of t, including repeats. If t has two 'a's, the window
must have at least two 'a's. Return that window as a string, or "" if none exists.

Approach:
We count how many of each character we still need from t using a map. We slide a
window with left and right pointers over s. We grow right and decrease the need
for each character we pass. Once the window has everything we need (a counter of
matched characters reaches the size of t), we try to shrink it from the left to
make it smaller, while it still covers all of t. We remember the smallest window
we ever find.

Why this works:
We only start shrinking once the window is valid, so every recorded window truly
contains all characters of t. Shrinking greedily from the left finds the smallest
valid window ending at the current right.

Time Complexity:
O(|s| + |t|) since both pointers move at most once across s and we scan t once.

Space Complexity:
O(|t|) for the frequency maps of characters.
*/

import java.util.HashMap;
import java.util.Map;

class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int required = need.size();
        int formed = 0;
        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }

            while (formed == required) {
                int len = right - left + 1;
                if (len < minLen) {
                    minLen = len;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }
                left++;
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(minStart, minStart + minLen);
    }
}
