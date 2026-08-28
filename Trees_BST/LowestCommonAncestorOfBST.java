/*
Problem:
Given the root of a BST and two nodes p and q, find their Lowest Common Ancestor
(LCA): the deepest node that is an ancestor of both p and q.

Approach:
Use BST ordering. Start at the root. If both p and q are smaller, go left. If
both are larger, go right. The first node where p and q split to different sides
(or one equals the node) is the LCA.

Why this works:
In a BST, the LCA is the first node whose value sits between p.val and q.val
(inclusive). Below it the two nodes diverge into separate subtrees.

Time Complexity:
O(h) where h is the height of the tree; we follow a single path down.

Space Complexity:
O(1) extra space if done iteratively, or O(h) with recursion.
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        while (current != null) {
            if (p.val < current.val && q.val < current.val) {
                current = current.left;
            } else if (p.val > current.val && q.val > current.val) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }
}
