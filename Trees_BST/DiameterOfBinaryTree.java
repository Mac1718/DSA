/*
Problem:
Given the root of a binary tree, return its diameter. The diameter is the length
of the longest path between any two nodes, measured by the number of edges. The
path may or may not pass through the root.

Approach:
For every node, the longest path through it equals the depth of its left subtree
plus the depth of its right subtree. Recurse to compute subtree depths while
keeping track of the largest such sum seen so far in a global variable.

Why this works:
Any path between two nodes goes up from one node to their LCA and down to the
other. That path length is exactly leftDepth + rightDepth at the LCA, so checking
every node finds the overall maximum.

Time Complexity:
O(n) since each node is visited once.

Space Complexity:
O(h) for the recursion stack, h being the tree height.
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxDiameter;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
