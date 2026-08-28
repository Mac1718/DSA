/*
Problem:
Given a grid of '1' (land) and '0' (water), count how many islands there are.
An island is a group of connected land cells (up, down, left, right).

Approach:
We use DFS. For every land cell we find, we "sink" it and all land
connected to it by visiting neighbors recursively. Each time we start
a new DFS from a still-unchanged land cell, that is one new island.

Why this works:
DFS explores the whole connected component of land in one go and marks
it as visited, so we never count the same island twice.

Time Complexity:
O(rows * cols) we visit each cell at most once.
Space Complexity:
O(rows * cols) for the recursion stack in the worst case.
*/
class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islands++;
                    dfs(grid, r, c);
                }
            }
        }
        return islands;
    }

    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (r < 0 || c < 0 || r >= rows || c >= cols) return;
        if (grid[r][c] != '1') return;

        grid[r][c] = '0'; // mark as visited by sinking it
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}
