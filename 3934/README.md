# 3934. Smallest Unique Subarray

**Difficulty:** Hard

**Problem Link:** https://leetcode.com/problems/smallest-unique-subarray/

**Tags:** Array, Binary Search, Sliding Window, Rolling Hash

## Problem Statement

You are given an integer array `nums`. Find the minimum length of a contiguous subarray that is not identical to any other subarray in the array. Two subarrays are identical when they have the same length and the same elements in corresponding positions.

## Intuition

For a fixed length, slide a window across the array and count how often each subarray occurs. If at least one window occurs exactly once, that length is valid.

The validity is monotonic: if a unique subarray exists at a certain length, extending that subarray by one element on either available side produces a longer subarray that must also be unique. Therefore, binary search can find the smallest valid length.

To compare windows efficiently, use rolling hashes. This implementation maintains two independent polynomial hashes to make accidental collisions extremely unlikely.

## Approach

- Binary search for the minimum valid subarray length.
- For each candidate length, calculate the hash of the first window.
- Slide the window through the array and update both hashes in constant time.
- Store the combined pair of hashes in a frequency map.
- A candidate length is valid if any hash occurs exactly once.
- Use two different prime moduli and bases to reduce collision risk.

## Algorithm

1. Initialize binary search boundaries to 1 and `n`.
2. For each middle length:
   - Calculate the base powers for that window length under both moduli.
   - Compute the two hashes of the first window.
   - Slide the window across the array, removing the outgoing value's contribution and adding the incoming value.
   - Combine the two hash values into one map key and count its frequency.
   - If any frequency is exactly 1, search smaller lengths; otherwise, search larger lengths.
3. Return the smallest valid length.

## Example

**Input:** `nums = [1,1,2,2,1]`

- Length 1: `[1]` occurs 3 times and `[2]` occurs 2 times, so no unique subarray exists.
- Length 2: `[1,1]`, `[1,2]`, `[2,2]`, and `[2,1]` each occur once.

**Output:** `2`

## Key Learning

Binary search is useful when a condition is monotonic. Rolling hash makes fixed-length subarray comparisons efficient by updating a window's hash in constant time.

## Complexity Analysis

- **Time Complexity:** O(n log n) expected, because binary search performs O(log n) checks and each check processes O(n) windows using expected O(1) HashMap operations.
- **Space Complexity:** O(n), for the frequency map.

## Language

Java
