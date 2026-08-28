/*
Problem:
Given a string of only uppercase letters and an integer k, you may change at most
k characters to any letter. Find the length of the longest part of the string
that can become all the same letter after those changes.

Approach:
Sliding window again. We keep a count of each letter in the window. The number of
changes needed to make the whole window one letter equals: window size minus the
count of the most frequent letter. If that needed count is more than k, the window
is too big, so we shrink it from the left. Otherwise we try to grow it. We remember
the biggest valid window size.

Why this works:
No matter which letter we pick, we only ever need to replace the non-majority
letters, and the best choice is always the current most frequent letter. As long
as replacements needed <= k, the window can be made uniform, so tracking the max
window works.

Time Complexity:
O(n) where n is the length of the string, since each pointer moves at most once.

Space Complexity:
O(26) for the fixed-size letter count array.
*/

class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'A';
            count[idx]++;
            if (count[idx] > maxFreq) {
                maxFreq = count[idx];
            }

            int windowSize = right - left + 1;
            int replacementsNeeded = windowSize - maxFreq;
            if (replacementsNeeded > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            windowSize = right - left + 1;
            if (windowSize > maxLen) {
                maxLen = windowSize;
            }
        }

        return maxLen;
    }
}
