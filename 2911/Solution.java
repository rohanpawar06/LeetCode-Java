import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int left = 0; left + len <= n; left++) {
                int best = Integer.MAX_VALUE;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;
                    int groups = len / d;

                    for (int r = 0; r < d; r++) {
                        for (int x = 0; x < groups / 2; x++) {
                            int a = left + r + x * d;
                            int b = left + r + (groups - 1 - x) * d;
                            if (s.charAt(a) != s.charAt(b)) changes++;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[left][left + len - 1] = best;
            }
        }

        int inf = 1_000_000;
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, inf);
        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int end = 2 * parts; end <= n; end++) {
                for (int start = 2 * (parts - 1); start <= end - 2; start++) {
                    if (dp[parts - 1][start] == inf) continue;
                    dp[parts][end] = Math.min(dp[parts][end],
                        dp[parts - 1][start] + cost[start][end - 1]);
                }
            }
        }

        return dp[k][n];
    }
}
