/*
Problem:
Given the roots of two binary trees, p and q, decide whether they are exactly
the same. Same means identical structure and identical values at every position.

Approach:
Use recursion to compare the two trees node by node. Both are null -> they match.
Exactly one is null -> they differ. Both hold the same value -> compare their
left and right subtrees.

Why this works:
Two trees are identical only if every corresponding pair of nodes matches, and
recursion handles this pair-by-pair from the root downward.

Time Complexity:
O(min(n, m)) where n and m are the node counts, since we stop at the first mismatch.

Space Complexity:
O(h) for the recursion stack, h being the smaller tree's height.
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
