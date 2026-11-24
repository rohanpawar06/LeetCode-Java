# 3411. Maximum Subarray With Equal Products

**Difficulty:** Easy

**Problem Link:** https://leetcode.com/problems/maximum-subarray-with-equal-products/

**Tags:** Array, Math, Enumeration, Number Theory

## Problem Statement

You are given an array of positive integers `nums`. A subarray is valid if the product of its elements is equal to the product of its greatest common divisor (GCD) and least common multiple (LCM). Return the maximum length of a valid subarray.

## Intuition

Enumerate every contiguous subarray. While extending a subarray, maintain its product, GCD, and LCM. These values can be updated incrementally when a new element is added, so we do not need to recalculate them from scratch.

For the given constraints, every element is at most 10. Therefore, the maximum possible GCD multiplied by LCM is 10 × 2520 = 25200. Once the running product exceeds 25200, it can never equal GCD × LCM for that subarray or any longer extension, so the inner loop can stop.

## Approach

- Fix each possible starting index.
- Extend the subarray one element at a time.
- Update the running product, GCD, and LCM.
- If the product equals GCD multiplied by LCM, update the maximum length.
- Stop extending when the product exceeds 25200.

## Algorithm

1. Initialize the answer to 0.
2. For every starting index `i`:
   - Initialize product = 1, GCD = 0, and LCM = 1.
   - Iterate through each ending index `j` from `i` onward.
   - Multiply the running product by `nums[j]`.
   - Update GCD using the Euclidean algorithm.
   - Update LCM using `lcm / gcd(lcm, nums[j]) * nums[j]`.
   - If product equals GCD × LCM, update the answer with the current subarray length.
   - If product exceeds 25200, stop this inner loop.
3. Return the maximum length.

## Example

**Input:** `nums = [1,2,1,2]`

For the subarray `[1,2]`:

- Product = 1 × 2 = 2
- GCD = 1
- LCM = 2
- GCD × LCM = 1 × 2 = 2

The condition is satisfied, so its length is 2.

The full subarray `[1,2,1,2]` has product 4, GCD 1, and LCM 2, so it is not valid.

**Output:** `2`

## Key Learning

When enumerating subarrays, maintain aggregate values incrementally. GCD and LCM can both be updated efficiently as each new element is included.

## Complexity Analysis

- **Time Complexity:** O(n² log M), where n is the array length and M is the maximum element. Each subarray extension performs GCD/LCM updates.
- **Space Complexity:** O(1), as only a fixed number of variables are used.

## Language

Java
