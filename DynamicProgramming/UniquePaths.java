/*
Problem:
A robot starts at the top-left corner of an m x n grid and can only
move down or right. How many unique paths are there to the bottom-right
corner?

Approach (normal DP):
Let dp[i][j] = number of ways to reach cell (i, j).
Base case: the first row and first column each have exactly 1 way
(straight line). For any other cell, the robot can only arrive from the
cell above (i-1, j) or the cell to the left (i, j-1), so
dp[i][j] = dp[i-1][j] + dp[i][j-1].

Why this works:
Paths to a cell are exactly the sum of paths to the two cells it can be
reached from, with no overlap between those route groups.

Time Complexity:
O(m * n) for filling the table.

Space Complexity:
O(m * n) for the 2D dp table. (Can be reduced to O(n) with one row.)
*/

import java.util.*;

class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
