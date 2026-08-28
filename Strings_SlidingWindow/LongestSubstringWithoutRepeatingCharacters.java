/*
Problem:
Given a string, find the length of the longest part of it where no character
appears more than once. For example, in "abcabcbb" the longest is "abc" which
has length 3.

Approach:
We use a sliding window with two pointers, left and right. As we move right
forward, we add each character to a set. If we see a character that is already
in the set, we move left forward and remove characters from the set until the
duplicate is gone. The window always holds only unique characters, and we
track the biggest window size we ever see.

Why this works:
The window only ever contains distinct characters, so its size is a valid
candidate for the answer. By shrinking from the left whenever we hit a repeat,
we keep the window valid while still trying to make it as large as possible.

Time Complexity:
O(n) where n is the length of the string, since each pointer moves at most n times.

Space Complexity:
O(min(n, 26)) for the set of characters in the window (at most all distinct chars).
*/

import java.util.HashSet;
import java.util.Set;

class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> seen = new HashSet<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (seen.contains(c)) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(c);
            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
            }
        }

        return maxLen;
    }
}
