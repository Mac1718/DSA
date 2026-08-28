/*
Problem:
Given a list of strings, group the ones that are anagrams of each other together.
For example ["eat", "tea", "tan", "ate", "nat", "bat"] becomes
[["eat","tea","ate"], ["tan","nat"], ["bat"]].

Approach:
For each word, we build a "signature" that is the same for all anagrams of it.
A simple signature is a count of each letter turned into a string like
"1a1b0c...". Words with the same signature are anagrams. We use a map from
signature to the list of words we have seen with that signature, then collect
all the lists at the end.

Why this works:
Anagrams share the exact same letter counts, so their signature string is
identical. Grouping by that string therefore groups exactly the anagrams.

Time Complexity:
O(n * k) where n is the number of words and k is the average word length, since
we count letters in each word.

Space Complexity:
O(n * k) to store the words grouped by their signatures.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            int[] count = new int[26];
            for (int i = 0; i < word.length(); i++) {
                count[word.charAt(i) - 'a']++;
            }

            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                key.append(count[i]);
                key.append('#');
            }

            String signature = key.toString();
            if (!groups.containsKey(signature)) {
                groups.put(signature, new ArrayList<>());
            }
            groups.get(signature).add(word);
        }

        return new ArrayList<>(groups.values());
    }
}
