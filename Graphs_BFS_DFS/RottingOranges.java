/*
Problem:
A grid has 0 (empty), 1 (fresh orange), 2 (rotten orange). Every minute, a
rotten orange makes its fresh neighbors rotten. Return the number of minutes
until no fresh oranges remain, or -1 if that is impossible.

Approach:
We use multi-source BFS. We put ALL initially rotten oranges into the queue
at minute 0, then do BFS level by level. Each level we advance is one minute.
We keep counting how many fresh oranges turn rotten.

Why this works:
Starting BFS from every rotten orange at once means the first time we reach
a fresh orange is its earliest possible rotting time (shortest distance).
Counting processed levels gives the total minutes.

Time Complexity:
O(rows * cols) each cell is visited at most once.
Space Complexity:
O(rows * cols) for the queue in the worst case.
*/
import java.util.LinkedList;
import java.util.Queue;

class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) queue.offer(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
            }
        }

        if (fresh == 0) return 0;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int minutes = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisLevel = false;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
                    if (grid[nr][nc] != 1) continue;

                    grid[nr][nc] = 2;
                    fresh--;
                    rottedThisLevel = true;
                    queue.offer(new int[]{nr, nc});
                }
            }

            if (rottedThisLevel) minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}
