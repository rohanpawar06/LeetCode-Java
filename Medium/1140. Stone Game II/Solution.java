class Solution {

    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int M) {

        if (i >= n) {
            return 0;
        }

        if (2 * M >= n - i) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        for (int x = 1; x <= 2 * M; x++) {

            int taken = suffix[i] - suffix[i + x];

            int opponent = solve(
                piles,
                i + x,
                Math.max(M, x)
            );

            best = Math.max(
                best,
                suffix[i] - opponent
            );
        }

        return dp[i][M] = best;
    }
}