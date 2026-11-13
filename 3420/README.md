# 3420. Count Non-Decreasing Subarrays After K Operations

- **Difficulty:** Hard
- **Problem:** [LeetCode 3420](https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations/)
- **Tags:** Array, Sliding Window, Monotonic Stack, Monotonic Queue
- **Language:** Java

## Problem Statement

Given an integer array `nums` and an integer `k`, each subarray can be independently modified by incrementing elements. Return the number of subarrays that can be made non-decreasing using at most `k` increments.

## Intuition

To make a subarray non-decreasing with the fewest increments, each element is raised to at least the maximum value before it. Process possible left boundaries from right to left and maintain the cost of the current window using a monotonic deque.

## Approach

- Use a sliding window `[left, right]`, moving `left` from right to left.
- Store indices in a deque so their corresponding values are non-increasing.
- When a new left value is larger, it raises a contiguous group of smaller values to that value; update the operation cost for the entire group.
- If the cost exceeds `k`, shrink the window from the right and subtract the cost contributed by the removed element.
- For each `left`, all subarrays ending between `left` and `right` are valid, contributing `right - left + 1`.

## Algorithm

1. Initialize an empty deque, `right = n - 1`, and `operations = answer = 0`.
2. Iterate `left` from `n - 1` down to `0`.
3. While the deque's last value is smaller than `nums[left]`, remove its index and calculate the added increments for the affected segment.
4. Add `left` to the deque.
5. While `operations > k`, remove the rightmost element's contribution and move `right` one position left. Remove the deque front if it represents that index.
6. Add `right - left + 1` to the answer.
7. Return the answer.

## Examples

**Example 1**

Input: `nums = [6,3,1,2,4,4], k = 7`

Output: `17`

**Example 2**

Input: `nums = [6,3,1,3,6], k = 4`

Output: `12`

## Key Learning

A monotonic deque can represent groups of elements that must be raised to a shared prefix maximum. Combining it with a sliding window allows each index to enter and leave the deque at most once.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(n)`
