/*
Problem:
Given a string, return the longest part of it that reads the same forwards and
backwards (a palindrome). For example, in "babad" the answer is "bab" (or "aba").

Approach:
Every palindrome has a center. It can be one character (odd length, like "aba")
or the space between two characters (even length, like "abba"). For each position
we expand outwards from that center, comparing the left and right characters. As
long as they match, we keep growing. We track the longest palindrome we find.

Why this works:
Any palindrome is symmetric around its center, so starting at each possible center
and expanding as far as matches allow will find every palindrome, including the
longest one.

Time Complexity:
O(n^2) because there are about 2n centers and each can expand up to n characters.

Space Complexity:
O(1) extra space, since we only keep a few indices and build the result substring.
*/

class LongestPalindromicSubstring {
    private int start = 0;
    private int maxLen = 0;

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        for (int i = 0; i < s.length(); i++) {
            expand(s, i, i);       // odd length palindrome
            expand(s, i, i + 1);   // even length palindrome
        }

        return s.substring(start, start + maxLen);
    }

    private void expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            int len = right - left + 1;
            if (len > maxLen) {
                maxLen = len;
                start = left;
            }
            left--;
            right++;
        }
    }
}
