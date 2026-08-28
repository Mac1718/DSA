/*
Problem:
Given a reference to a node in a connected undirected graph, return a deep
copy (clone) of the whole graph. The clone must have the same structure and
values, but be made of brand new Node objects.

Approach:
We use DFS. We keep a HashMap that maps an original node to its cloned copy.
When we visit a node, if it is already cloned we return the clone; otherwise
we create the clone, then recursively clone and link each neighbor.

Why this works:
The HashMap makes sure each original node gets exactly one clone, and we
link neighbors after both endpoints exist, so the structure is preserved.

Time Complexity:
O(V + E) we visit each node and edge once.
Space Complexity:
O(V) for the map and the recursion stack.
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Node {
    int val;
    List<Node> neighbors;
    Node(int v) { val = v; neighbors = new ArrayList<>(); }
}

class CloneGraph {
    private HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        return dfs(node);
    }

    private Node dfs(Node node) {
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        Node copy = new Node(node.val);
        visited.put(node, copy);

        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(dfs(neighbor));
        }
        return copy;
    }
}
