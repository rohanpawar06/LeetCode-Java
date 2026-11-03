# 3736. Minimum Moves to Equal Array Elements III

## Difficulty
Easy

## Problem Link
[LeetCode - Minimum Moves to Equal Array Elements III](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii/)

## Tags
Array, Math

## Problem Statement
Given an integer array `nums), in one move you can increment one element by 1. Return the minimum number of moves needed to make all array elements equal.

## Intuition
To make every element equal using only increments, the target must be at least the largest element. Choosing the largest element as the target minimizes the total increments.

## Approach
Find the maximum value and the sum of all elements. The number of moves needed is the sum of the differences between the maximum and each element.

## Algorithm
1. Initialize `max` with the first array element and `sum` with 0.
2. Traverse the array, updating the maximum and adding each value to the sum.
3. Return `max * nums.length - sum`.

## Example
Input: `nums = [2, 1, 3]`

Target: `3`

Moves: `(3 - 2) + (3 - 1) + (3 - 3) = 3`

Output: `3`

## Key Learning
When only increment operations are allowed, making every value equal to the current maximum gives the minimum number of moves.

## Time Complexity
`O(n)` — one traversal of the array.

## Space Complexity
`O(1)` — only a few variables are used.

## Language
Java
