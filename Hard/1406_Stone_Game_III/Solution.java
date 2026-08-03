class Solution {

    private Integer[] dp;

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        dp = new Integer[n];

        int diff = solve(stoneValue, 0);

        if (diff > 0) {
            return "Alice";
        } else if (diff < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }

    private int solve(int[] stoneValue, int index) {

        if (index >= stoneValue.length) {
            return 0;
        }

        if (dp[index] != null) {
            return dp[index];
        }

        int maxDiff = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int i = index; i < Math.min(index + 3, stoneValue.length); i++) {
            currentSum += stoneValue[i];
            maxDiff = Math.max(maxDiff, currentSum - solve(stoneValue, i + 1));
        }

        return dp[index] = maxDiff;
    }
}