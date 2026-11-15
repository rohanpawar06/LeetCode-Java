# 1524. Number of Sub-arrays With Odd Sum

- **Difficulty:** Medium
- **Problem:** [LeetCode 1524](https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/)
- **Tags:** Array, Prefix Sum, Dynamic Programming
- **Language:** Java

## Problem Statement

Given an array of integers `arr`, return the number of subarrays with an odd sum. Since the answer can be large, return it modulo `1,000,000,007`.

## Intuition

A subarray sum is odd exactly when the prefix sums at its two boundaries have different parity. Count how many previous prefix sums are odd and even while traversing the array.

## Approach

- Maintain the running prefix sum and its parity.
- Track the number of previous odd prefix sums in `odd` and even prefix sums in `even`.
- Initialize `even = 1` to represent the empty prefix before the array.
- If the current prefix is even, pair it with previous odd prefixes.
- If the current prefix is odd, pair it with previous even prefixes.
- Keep the answer modulo `1,000,000,007`.

## Algorithm

1. Initialize `odd = 0`, `even = 1`, `prefix = 0`, and `result = 0`.
2. Traverse each element and add it to the prefix sum.
3. If the prefix is even, add `odd` to the result and increment `even`.
4. Otherwise, add `even` to the result and increment `odd`.
5. Apply the modulo and return the result.

## Example

**Input:** `arr = [1,3,5]`

**Output:** `4`

The odd-sum subarrays are `[1]`, `[3]`, `[5]`, and `[1,3,5]`.

## Key Learning

Prefix-sum parity avoids checking every subarray. Different prefix parities produce an odd subarray sum, allowing a linear-time counting solution.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`
