/*
Problem:
Design a way to turn a binary tree into a string (serialize) and rebuild the
exact same tree from that string (deserialize). The two operations must be
inverse of each other.

Approach:
We use preorder traversal (visit node, then left, then right). For each node we
write its value, and for every missing child we write "X" as a null marker. So a
leaf's two children both become "X". The serialize step walks the tree building
that string; the deserialize step reads the values in the same preorder order and
rebuilds nodes, treating "X" as null.

Why this works:
Preorder with explicit null markers fully describes the tree shape. Because we
always traverse in node-left-right order and mark every empty spot, the
deserializer can replay the exact same steps to reconstruct each node and wire up
its children correctly.

Time Complexity:
O(n) for both serialize and deserialize, one pass over the nodes each way.

Space Complexity:
O(n) to store the string and O(h) for the recursion stack.
*/
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("X,");
            return;
        }
        sb.append(node.val).append(",");
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // Decodes a string to a tree.
    public TreeNode deserialize(String data) {
        String[] parts = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(parts));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("X")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }
}
