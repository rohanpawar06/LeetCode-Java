class Solution {
    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        dp = new int[n][n];
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(stoneValue, 0, n - 1);
    }

    private int solve(int[] stones, int left, int right) {
        if (left == right) {
            return 0;
        }

        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        int best = 0;

        for (int mid = left; mid < right; mid++) {
            int leftSum = prefix[mid + 1] - prefix[left];
            int rightSum = prefix[right + 1] - prefix[mid + 1];

            if (leftSum < rightSum) {
                best = Math.max(
                    best,
                    leftSum + solve(stones, left, mid)
                );
            } else if (leftSum > rightSum) {
                best = Math.max(
                    best,
                    rightSum + solve(stones, mid + 1, right)
                );
            } else {
                best = Math.max(
                    best,
                    leftSum + Math.max(
                        solve(stones, left, mid),
                        solve(stones, mid + 1, right)
                    )
                );
            }
        }

        return dp[left][right] = best;
    }
}