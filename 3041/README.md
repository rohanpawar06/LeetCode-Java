# 3041. Maximize Consecutive Elements in an Array After Modification

**Difficulty:** Hard

**Problem Link:** https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification/

**Tags:** Array, Dynamic Programming, Sorting, Hash Table

## Problem Statement

You are given a 0-indexed integer array `nums`. You can increase the value of any element by at most one. After performing the operations, select a subset of the array such that every pair of consecutive elements in the subset differs by exactly one. Return the maximum possible size of such a subset.

## Intuition

Each array element can contribute either its original value or its value increased by one. After sorting, dynamic programming can track the longest consecutive sequence ending at each possible value.

Let `dp[x]` represent the maximum length of a valid sequence ending at value `x`.

For each number:
- Keep it unchanged and extend a sequence ending at `num - 1`.
- Increase it by one and extend a sequence ending at `num`.

## Approach

- Sort the array so values are processed in non-decreasing order.
- Use a HashMap to store DP lengths for possible ending values.
- For each number, calculate the best sequence length if it remains unchanged and if it is increased by one.
- Update the corresponding DP states and maintain the maximum answer.

## Algorithm

1. Sort `nums`.
2. Initialize a HashMap `dp` and an answer variable.
3. For each `num` in the sorted array:
   - Compute `keep = dp[num - 1] + 1`.
   - Compute `change = dp[num] + 1`.
   - Update `dp[num]` with the maximum of its current value and `keep`.
   - Update `dp[num + 1]` with the maximum of its current value and `change`.
4. Update the answer using the two affected DP states.
5. Return the maximum answer.

## Example

**Input:** `nums = [2,1,5,1,1]`

After sorting: `[1,1,1,2,5]`

One optimal selection after allowed increases is `[1,2,3]`.

**Output:** `3`

## Key Learning

Sorting combined with dynamic programming is useful when elements can be adjusted within a small range. A map can efficiently store the best result for each possible ending value.

## Complexity Analysis

- **Time Complexity:** O(n log n), dominated by sorting. The DP pass takes O(n) expected time using a HashMap.
- **Space Complexity:** O(n), for the HashMap and sorting auxiliary space.

## Language

Java
