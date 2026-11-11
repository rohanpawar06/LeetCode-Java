# 3789. Minimum Cost to Acquire Required Items

- **Difficulty:** Medium
- **Problem:** [LeetCode 3789](https://leetcode.com/problems/minimum-cost-to-acquire-required-items/)
- **Tags:** Greedy, Math
- **Language:** Java

## Problem Statement

You are given `cost1`, `cost2`, `costBoth`, `need1`, and `need2`. Type 1 items satisfy one unit of requirement 1, type 2 items satisfy one unit of requirement 2, and type 3 items satisfy one unit of both requirements. Return the minimum cost needed to meet or exceed both requirements.

## Intuition

For units required by both types, compare the combined item with buying one item of each type. For any remaining units of one type, compare its individual item cost with the combined item cost, since extra contribution to the other type is allowed.

## Approach

- Let `common = min(need1, need2)`.
- For these common units, pay the cheaper of `costBoth` and `cost1 + cost2`.
- For the remaining type 1 units, pay the cheaper of `cost1` and `costBoth`.
- For the remaining type 2 units, pay the cheaper of `cost2` and `costBoth`.
- Use `long` for the total because the requirements can be large.

## Algorithm

1. Calculate the common requirement.
2. Add `common * min(costBoth, cost1 + cost2)` to the answer.
3. Add the remaining type 1 cost using `min(cost1, costBoth)`.
4. Add the remaining type 2 cost using `min(cost2, costBoth)`.
5. Return the total.

## Example

**Input:** `cost1 = 3, cost2 = 2, costBoth = 1, need1 = 3, need2 = 2`

**Output:** `3`

Buying three type 3 items costs `3` and satisfies both requirements.

## Key Learning

When one purchase can satisfy multiple requirements, compare its price against the combined cost of separate purchases for shared demand, and against the individual cost for leftover demand.

## Complexity

- **Time:** `O(1)`
- **Space:** `O(1)`
