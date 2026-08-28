/*
Problem:
Given n nodes labeled 0..n-1 and a list of undirected edges, decide whether
the edges form a valid tree. A valid tree has exactly n-1 edges, is fully
connected, and has no cycles.

Approach:
We use Union Find (Disjoint Set Union). For each edge we try to unite its two
endpoints. If the two endpoints already share the same root, that edge would
create a cycle, so it is not a tree. After processing all edges, the graph is
a tree only if there were no cycles AND every node ended up in one group.

Why this works:
Union Find detects cycles quickly: a cycle appears when an edge joins two
nodes that are already connected. With no cycles and n-1 edges, the graph is
automatically connected and therefore a tree.

Time Complexity:
O(E * alpha(N)) nearly linear with path compression and union by rank.
Space Complexity:
O(N) for the parent and rank arrays.
*/
class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;

        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (find(parent, u) == find(parent, v)) {
                return false; // cycle detected
            }
            union(parent, rank, u, v);
        }
        return true;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private void union(int[] parent, int[] rank, int x, int y) {
        int rx = find(parent, x);
        int ry = find(parent, y);
        if (rx == ry) return;
        if (rank[rx] < rank[ry]) parent[rx] = ry;
        else if (rank[rx] > rank[ry]) parent[ry] = rx;
        else { parent[ry] = rx; rank[rx]++; }
    }
}
