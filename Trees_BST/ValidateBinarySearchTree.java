/*
Problem:
Given the root of a binary tree, return true if it is a valid Binary Search Tree
(BST). In a BST every node's value is greater than all values in its left subtree
and less than all values in its right subtree.

Approach:
Recurse down the tree, carrying the valid range (low, high) for each node. A node
is valid if its value is strictly between low and high. We then recurse left with
an updated upper bound and right with an updated lower bound.

Why this works:
A node only being bigger than its parent is not enough; it must stay within the
full range inherited from ancestors. Passing down (low, high) enforces this.

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
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean check(TreeNode node, long low, long high) {
        if (node == null) {
            return true;
        }
        if (node.val <= low || node.val >= high) {
            return false;
        }
        return check(node.left, low, node.val) && check(node.right, node.val, high);
    }
}
