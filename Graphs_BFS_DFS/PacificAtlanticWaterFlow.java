/*
Problem:
Given a grid of heights, water flows from a cell to a neighboring cell of
equal or lower height. Find all cells from which water can reach BOTH the
Pacific (top/left borders) and the Atlantic (bottom/right borders).

Approach:
We use BFS (could also be DFS). Instead of flowing downhill from every cell,
we reverse the thinking: start from the ocean borders and flow inward toward
equal or higher ground. We keep one visited set for the Pacific and one for
the Atlantic. Any cell visited by both is a valid answer.

Why this works:
Reversed flow (ocean -> land, only going up or equal) is the same set of
cells as land -> ocean, but we only need two BFS runs instead of one per cell.

Time Complexity:
O(rows * cols) each cell is processed at most twice.
Space Complexity:
O(rows * cols) for the visited sets and the queues.
*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int rows = heights.length;
        if (rows == 0) return result;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();

        for (int c = 0; c < cols; c++) {
            pQueue.offer(new int[]{0, c});
            pacific[0][c] = true;
            aQueue.offer(new int[]{rows - 1, c});
            atlantic[rows - 1][c] = true;
        }
        for (int r = 0; r < rows; r++) {
            pQueue.offer(new int[]{r, 0});
            pacific[r][0] = true;
            aQueue.offer(new int[]{r, cols - 1});
            atlantic[r][cols - 1] = true;
        }

        bfs(heights, pQueue, pacific);
        bfs(heights, aQueue, atlantic);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    List<Integer> cell = new ArrayList<>();
                    cell.add(r);
                    cell.add(c);
                    result.add(cell);
                }
            }
        }
        return result;
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited) {
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
                if (visited[nr][nc]) continue;
                if (heights[nr][nc] < heights[r][c]) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
    }
}
