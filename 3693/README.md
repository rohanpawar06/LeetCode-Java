# 3693. Climbing Stairs II

- **Difficulty:** Medium
- **Problem:** [LeetCode 3693](https://leetcode.com/problems/climbing-stairs-ii/)
- **Tags:** Dynamic Programming
- **Language:** Java

## Problem Statement

You are climbing a staircase with steps numbered from `0` to `n`. You are given a 1-indexed array `costs` of length `n`. From step `i`, you can jump to step `i + 1`, `i + 2`, or `i + 3`. The cost of jumping from `i` to `j` is `costs[j] + (j - i)^2`. Starting at step `0` with cost `0`, return the minimum total cost to reach step `n`.

## Intuition

The minimum cost to reach a step depends only on the best costs of the previous three steps. Therefore, dynamic programming can calculate the answer from left to right.

## Approach

- `dp[i]` stores the minimum cost to reach step `i`.
- Initialize `dp[0] = 0` and all other states to a large value.
- For each step `i`, try the previous up to three steps.
- Add the destination step cost and the squared jump length.
- Return `dp[n]`.

## Algorithm

1. Create a DP array of size `n + 1` and initialize it to infinity.
2. Set `dp[0] = 0`.
3. For every step `i` from `1` through `n`, check predecessor steps `j` from `max(0, i - 3)` through `i - 1`.
4. Update `dp[i]` with `dp[j] + costs[i - 1] + (i - j)^2`.
5. Return `dp[n]`.

## Example

**Input:** `n = 4`, `costs = [1,2,3,4]`

**Output:** `13`

One optimal path is `0 → 1 → 2 → 4`, with total cost `2 + 3 + 8 = 13`.

## Key Learning

For a limited set of possible jump lengths, DP only needs to inspect those previous states. The destination step cost is paid whenever that step is reached, plus the squared jump distance.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(n)`
