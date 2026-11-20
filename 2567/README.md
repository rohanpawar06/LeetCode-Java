# 2567. Minimum Score by Changing Two Elements

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/minimum-score-by-changing-two-elements/

**Tags:** Array, Sorting

## Problem Statement

You are given an integer array `nums`. You can change at most two elements in the array to any value. The score of the array is the difference between the maximum and minimum elements. Return the minimum possible score after changing at most two elements.

## Intuition

After sorting the array, changing two elements can eliminate the effect of at most two values at the extremes. The best result must come from one of three cases: changing the two smallest elements, changing the smallest and largest elements, or changing the two largest elements.

## Approach

- Sort the array in ascending order.
- Calculate the remaining range for each of the three possible cases.
- Return the minimum of those three ranges.

## Algorithm

1. Sort `nums`.
2. Let `n` be the array length.
3. Calculate `nums[n - 1] - nums[2]` (change the two smallest elements).
4. Calculate `nums[n - 2] - nums[1]` (change the smallest and largest elements).
5. Calculate `nums[n - 3] - nums[0]` (change the two largest elements).
6. Return the minimum of the three values.

## Example

**Input:** `nums = [1,4,7,8,5]`

**Sorted:** `[1,4,5,7,8]`

- Change two smallest: `8 - 5 = 3`
- Change smallest and largest: `7 - 4 = 3`
- Change two largest: `5 - 1 = 4`

**Output:** `3`

## Key Learning

Sorting helps reduce a problem involving arbitrary element changes to a small number of cases involving the array's smallest and largest values.

## Complexity Analysis

- **Time Complexity:** O(n log n), due to sorting.
- **Space Complexity:** O(log n) auxiliary space for sorting, implementation-dependent.

## Language

Java
