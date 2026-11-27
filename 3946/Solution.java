class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        int[] dp = new int[budget + 1];
        java.util.Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];

            minPrice = Math.min(minPrice, price);

            int gain = 0;

            for (int j = 0; j < n; j++) {
                if (items[j][0] % factor == 0) {
                    gain++;
                }
            }

            for (int spent = budget; spent >= price; spent--) {
                if (dp[spent - price] != -1) {
                    dp[spent] = Math.max(
                        dp[spent],
                        dp[spent - price] + gain
                    );
                }
            }
        }

        int answer = 0;

        for (int spent = 0; spent <= budget; spent++) {
            if (dp[spent] != -1) {
                int extra = (budget - spent) / minPrice;
                answer = Math.max(answer, dp[spent] + extra);
            }
        }

        return answer;
    }
}