/*
Problem:
Given the root of a binary tree, return its maximum depth. The depth is the number
of nodes along the longest path from the root down to a leaf.

Approach:
Go down the tree with recursion. For any node, its depth is 1 plus the larger of
the depths of its left and right children. A null node has depth 0.

Why this works:
The deepest path must pass through the root and then keep choosing the deeper
side at every step, which is exactly what the recursion computes.

Time Complexity:
O(n) where n is the number of nodes, since we visit each node once.

Space Complexity:
O(h) for the recursion stack, where h is the height of the tree.
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
