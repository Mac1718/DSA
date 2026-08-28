/*
Problem:
Given the root of a binary tree, invert it. Inverting means every left child
becomes a right child and every right child becomes a left child, for all nodes.

Approach:
Recurse down the tree. For each node, swap its left and right pointers, then
invert both subtrees. The base case is a null node, which we return as is.

Why this works:
Swapping a node's children and then recursively inverting each subtree flips the
entire tree bottom-up, leaving mirrored structure at every level.

Time Complexity:
O(n) since we touch each node once.

Space Complexity:
O(h) for the recursion stack, where h is the tree height.
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
