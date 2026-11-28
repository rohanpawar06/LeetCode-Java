# 3947. Maximum Number of Items From Sale II

**Difficulty:** Medium  
**LeetCode:** [3947. Maximum Number of Items From Sale II](https://leetcode.com/problems/maximum-number-of-items-from-sale-ii/)  
**Topics:** Array, Greedy, Sorting, Number Theory

---

## Problem Statement

You are given `items`, where `items[i] = [factor, price]`, and an integer `budget`.

You can buy unlimited copies of each item, spending at most the given budget. Each purchased copy of item `i` can provide at most one free copy of another item `j), provided that:

- `i != j`
- `factor[i]` divides `factor[j]`
- Each ordered pair `(i, j)` can provide a free item only once, no matter how many copies of item `i` are purchased.

Return the maximum number of purchased and free items.

---

## Intuition

For a particular item type `i`, let `gain[i]` be the number of *other* item types whose factors are divisible by `factor[i]`.

The first `gain[i]` purchased copies of item `i` can each unlock a different free item. Therefore, each of those purchases gives **two items**: one purchased and one free. Further copies of the same type give only one item each.

Let `minPrice` be the cheapest item price. A bonus-enabled purchase costing at least `2 * minPrice` is never worthwhile: spending that amount on the cheapest item gives at least as many items. So only bonus purchases priced below `2 * minPrice` need to be considered.

All bonus purchases have the same benefit of two items. We should therefore take the cheapest available bonus purchases first, then spend the remaining budget on the cheapest item.

---

## Approach

1. Find the maximum factor and the minimum item price.
2. Count how many items have each factor using a frequency array.
3. For every factor `f`, sum the frequencies of its multiples. Subtract one to exclude the item itself. This gives the number of free-item opportunities for an item with factor `f`.
4. Create a candidate for each item type that has at least one free-item opportunity and whose price is strictly less than twice the minimum price.
5. Sort candidates by price.
6. Buy as many bonus-enabled copies as possible in ascending price order, limited by both the remaining budget and the available opportunities.
7. Use the remaining budget to buy copies of the cheapest item.

---

## Java Solution

```java
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
```

---

## Example

**Input:**
```
items = [[1,6],[2,4],[3,5]], budget = 19
```

**Output:**
```
5
```

**Explanation:**

- Factor `1` divides the factors `2` and `3`, so buying two copies of the first item can unlock two different free items.
- Buy two copies at price `6`, spending `12`. This gives four items in total.
- The remaining budget is `7`. Buy one copy of the cheapest item at price `4`.
- Total: three purchased items and two free items = **5 items**.

---

## Algorithm

- Build a frequency array for item factors.
- Count multiples for each factor to calculate its free-item opportunities.
- Keep only profitable bonus purchases.
- Sort them by price and greedily use the budget.
- Spend any remainder on the cheapest item.

---

## Complexity Analysis

- **Time Complexity:** `O(M log M + M log M + n log n)`, where `M` is the maximum factor and `n` is the number of items. The multiples counting takes `O(M log M)); sorting candidates takes `O(n log n)). Since `M <= n`, the overall complexity is `O(n log n)`.
- **Space Complexity:** `O(M + n)` for the frequency/gain arrays and candidate list, which is `O(n)` because `M <= n`.

---

## Key Learning

When each purchase has a limited number of bonus rewards, count those opportunities first. If every bonus-enabled purchase has the same item-count benefit, sorting by purchase price allows a greedy budget allocation. A multiples sieve efficiently handles divisibility relationships.
