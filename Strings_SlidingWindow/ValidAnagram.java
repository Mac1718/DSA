/*
Problem:
Given two strings s and t, decide whether t is an anagram of s. That means t has
exactly the same letters as s, just possibly in a different order. For example,
"listen" and "silent" are anagrams.

Approach:
First, if they have different lengths they can't be anagrams, so return false.
Otherwise, count the letters of s in an array of size 26. Then go through t and
subtract one for each letter. If any count goes below zero, t has a letter s
doesn't (or too many of one), so return false. If we finish, they match.

Why this works:
An anagram must have identical letter frequencies. Adding for s and subtracting
for t checks exactly that: a perfect match leaves every count at zero.

Time Complexity:
O(n) where n is the length of the longer string, from the two passes.

Space Complexity:
O(26) for the fixed count array.
*/

class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            int idx = t.charAt(i) - 'a';
            count[idx]--;
            if (count[idx] < 0) {
                return false;
            }
        }

        return true;
    }
}
