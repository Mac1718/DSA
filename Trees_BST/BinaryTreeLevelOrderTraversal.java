/*
Problem:
Given the root of a binary tree, return the level order traversal: a list of
lists, where each inner list holds the values of one level, top to bottom.

Approach:
Use a Queue. Start by adding the root. Then, while the queue is not empty, take
all nodes currently in the queue (one full level), record their values, and add
their children for the next level.

Why this works:
A queue is first-in-first-out, so nodes are processed in the exact order they
appear level by level, and counting the level size keeps levels separated.

Time Complexity:
O(n) because every node is enqueued and dequeued once.

Space Complexity:
O(n) for the queue, which at most holds about half the nodes (the widest level).
*/
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(currentLevel);
        }
        return result;
    }
}
