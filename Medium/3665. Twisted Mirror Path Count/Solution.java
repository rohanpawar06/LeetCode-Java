class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Direction 0: arriving from the left (moving right).
        // Direction 1: arriving from above (moving down).
        int[][][] dp = new int[m][n][2];
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int fromRight = dp[i][j][0];
                int fromDown = dp[i][j][1];

                if (grid[i][j] == 0) {
                    int total = (fromRight + fromDown) % MOD;

                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                                (dp[i][j + 1][0] + total) % MOD;
                    }
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                                (dp[i + 1][j][1] + total) % MOD;
                    }
                } else {
                    // Moving right into a mirror redirects down.
                    if (fromRight > 0 && i + 1 < m) {
                        dp[i + 1][j][1] =
                                (dp[i + 1][j][1] + fromRight) % MOD;
                    }

                    // Moving down into a mirror redirects right.
                    if (fromDown > 0 && j + 1 < n) {
                        dp[i][j + 1][0] =
                                (dp[i][j + 1][0] + fromDown) % MOD;
                    }
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}
