/*
Problem:
Given a beginWord, endWord, and a wordList, find the length of the shortest
transformation sequence where each step changes exactly one letter and the
new word is in the wordList. Return 0 if no such sequence exists.

Approach:
We use BFS on words. Each word is a node; two words are connected if they
differ by exactly one letter. We start from beginWord, explore one letter
change at a time, and the first time we reach endWord we have the shortest
path. A HashSet of the wordList makes lookups fast.

Why this works:
BFS explores words level by level, so the first time we see the endWord it is
reached with the minimum number of steps. We remove visited words so we do
not loop forever.

Time Complexity:
O(M^2 * N) where N is word count and M is word length (M^2 for generating
neighbors of each M-length word).
Space Complexity:
O(M * N) for the word set and the queue.
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordSet.remove(beginWord);

        int steps = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                char[] chars = word.toCharArray();

                for (int pos = 0; pos < chars.length; pos++) {
                    char original = chars[pos];

                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == original) continue;
                        chars[pos] = ch;
                        String next = new String(chars);

                        if (next.equals(endWord)) return steps + 1;

                        if (wordSet.contains(next)) {
                            wordSet.remove(next);
                            queue.offer(next);
                        }
                    }
                    chars[pos] = original;
                }
            }
            steps++;
        }
        return 0;
    }
}
