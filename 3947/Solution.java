import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] gain = new int[maxFactor + 1];

        for (int factor = 1; factor <= maxFactor; factor++) {
            for (int multiple = factor; multiple <= maxFactor; multiple += factor) {
                gain[factor] += freq[multiple];
            }
            gain[factor]--;
        }

        List<int[]> candidates = new ArrayList<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (gain[factor] > 0 && (long) price < 2L * minPrice) {
                candidates.add(new int[]{price, gain[factor]});
            }
        }

        candidates.sort(Comparator.comparingInt(a -> a[0]));

        long remaining = budget;
        long answer = 0;

        for (int[] candidate : candidates) {
            int price = candidate[0];
            int availableBonusPurchases = candidate[1];

            long take = Math.min(remaining / price, availableBonusPurchases);
            answer += 2L * take;
            remaining -= take * price;
        }

        answer += remaining / minPrice;

        return (int) answer;
    }
}