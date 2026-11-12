# 3105. Longest Strictly Increasing or Strictly Decreasing Subarray

- **Difficulty:** Easy
- **Problem:** [LeetCode 3105](https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/)
- **Tags:** Array, Greedy
- **Language:** Java

## Problem Statement

Given an integer array `nums`, return the length of the longest subarray that is either strictly increasing or strictly decreasing.

## Intuition

A monotonic subarray can be extended by one element if the new element continues the same strict direction. Track increasing and decreasing runs as the array is scanned.

## Approach

- Maintain `inc` for the current strictly increasing run.
- Maintain `dec` for the current strictly decreasing run.
- When the current value is greater than the previous value, extend `inc` and reset `dec`.
- When it is smaller, extend `dec` and reset `inc`.
- When equal, reset both runs to one.
- Track the maximum run length.

## Algorithm

1. Initialize `inc`, `dec`, and `maxLen` to `1`.
2. Iterate from the second element to the end.
3. Compare each element with its predecessor and update the two run lengths.
4. Update `maxLen` after every comparison.
5. Return `maxLen`.

## Example

**Input:** `nums = [1,4,3,3,2]`

**Output:** `2`

The longest strictly increasing or decreasing subarray has length `2`, such as `[1,4]`, `[4,3]`, or `[3,2]`.

## Key Learning

A single traversal with two counters is enough to track both increasing and decreasing contiguous runs without using extra data structures.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

