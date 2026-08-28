/*
Problem:
Given n nodes labeled 0..n-1 and a list of undirected edges, count how many
connected components (separate groups of nodes) the graph has.

Approach:
We use Union Find (Disjoint Set Union). For every edge we unite its two
endpoints into one group. After all edges are processed, we count how many
distinct roots (parents that point to themselves) remain. That number is the
number of connected components. Each isolated node also counts as one.

Why this works:
Union Find keeps merging connected pieces together. Whatever is left as its
own root in the end is a separate component, and counting roots gives the
answer directly.

Time Complexity:
O(E * alpha(N)) nearly linear thanks to path compression and union by rank.
Space Complexity:
O(N) for the parent and rank arrays.
*/
class NumberOfConnectedComponents {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] edge : edges) {
            union(parent, rank, edge[0], edge[1]);
        }

        int components = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) components++;
        }
        return components;
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
