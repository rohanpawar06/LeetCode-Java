# 3364. Minimum Positive Sum Subarray

- **Difficulty:** Easy
- **Problem:** [LeetCode 3364](https://leetcode.com/problems/minimum-positive-sum-subarray/)
- **Tags:** Array, Sliding Window
- **Language:** Java

## Problem Statement

Given an integer array `nums` and two integers `l` and `r`, find the minimum positive sum among all contiguous subarrays whose lengths are between `l` and `r`, inclusive. Return `-1` if no such positive-sum subarray exists.

## Intuition

For a fixed subarray length, adjacent windows differ by only one removed element and one added element. Maintain the window sum and slide across the array instead of recalculating every subarray.

## Approach

- Iterate over every permitted length from `l` through `r`.
- Calculate the sum of the first window of that length.
- Slide the window by subtracting the element leaving and adding the new element.
- Update the answer whenever the current sum is positive and smaller than the best found so far.
- Return `-1` if no positive sum was found.

## Algorithm

1. Initialize the minimum positive sum to `Integer.MAX_VALUE`.
2. For each length from `l` to `r`, compute the first window sum.
3. Check whether the sum is positive.
4. Move the window across the array, updating its sum in constant time.
5. Check each updated sum and maintain the minimum positive value.
6. Return the minimum, or `-1` if it remains unchanged.

## Example

**Input:** `nums = [3, -2, 1, 4], l = 2, r = 3`

**Output:** `1`

The subarray `[3, -2]` has sum `1`, which is the minimum positive sum among the allowed lengths.

## Key Learning

For fixed-size contiguous subarrays, sliding window provides an `O(1)` sum update when moving from one window to the next.

## Complexity

- **Time:** `O(n * (r - l + 1))`
- **Space:** `O(1)`
