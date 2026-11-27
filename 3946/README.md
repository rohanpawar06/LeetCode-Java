# 3946. Maximum Number of Items From Sale I

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/maximum-number-of-items-from-sale-i/

**Tags:** Array, Dynamic Programming, Greedy, Knapsack

## Problem Statement

You are given an array `items`, where `items[i] = [factor_i, price_i]`, and an integer `budget`. There are unlimited copies of every item type. When you buy at least one copy of item type `i`, you receive one free copy of every different item type `j` whose factor is divisible by `factor_i`. Buying more copies of the same type does not give additional free copies. Return the maximum total number of purchased and free items obtainable without spending more than the budget.

## Intuition

The first purchase of an item type is special: it gives the purchased item plus one free copy of every other item type whose factor is divisible by its factor. The total gain from activating that item type is therefore the number of item factors divisible by its factor.

Every additional copy of an already activated type gives only one extra item. After choosing which item types to activate, the remaining budget is best spent buying the cheapest item repeatedly.

This leads to a 0/1 knapsack DP for the first purchase of each type, followed by a greedy calculation for the remaining budget.

## Approach

- Find the minimum price among all item types.
- For each item type, count how many item factors are divisible by its factor. This count includes the purchased item itself, so it equals the total gain from its first purchase: one purchased copy plus all eligible free copies.
- Use 0/1 knapsack to choose which item types to activate. Process budget values in descending order so each type's first-purchase benefit is counted at most once.
- For every reachable spending amount, use the remaining budget to buy as many copies as possible of the cheapest item.

## Algorithm

1. Initialize `dp[0] = 0` and all other DP states to -1, representing unreachable exact spending amounts.
2. Find the minimum item price.
3. For each item type:
   - Count all item types whose factor is divisible by the current factor.
   - Iterate spending amounts downward from `budget` to the current price.
   - If the previous spending amount is reachable, update the state by adding the current type's gain.
4. For each reachable spending amount, calculate:
   `dp[spent] + (budget - spent) / minPrice`.
5. Return the maximum value.

## Example

**Input:** `items = [[6,2],[2,6],[3,4]], budget = 9`

Activating the item with factor 6 costs 2 and gives a total gain of 1 item (the purchased item itself). The remaining budget is 7, which can buy 3 more copies of the cheapest item at price 2.

Total items = 1 + 3 = 4.

**Output:** `4`

## Key Learning

When an item provides a one-time bonus on its first purchase but repeated purchases provide only one additional unit, separate the one-time activation decision from the repeated-purchase decision. A 0/1 knapsack handles activation, and a greedy minimum-price choice handles additional copies.

## Complexity Analysis

Let n be the number of item types and B be the budget.

- **Time Complexity:** O(n² + nB), for counting divisibility gains and performing the knapsack transitions.
- **Space Complexity:** O(B), for the DP array.

## Language

Java
