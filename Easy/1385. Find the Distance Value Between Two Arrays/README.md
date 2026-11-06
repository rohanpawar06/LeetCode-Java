# 1385. Find the Distance Value Between Two Arrays

**Difficulty:** Easy

**Problem Link:** https://leetcode.com/problems/find-the-distance-value-between-two-arrays/

**Tags:** Array, Sorting

## Problem Statement

Given two integer arrays arr1 and arr2 and an integer d, count arr1 elements whose absolute difference from every element in arr2 is greater than d.

## Intuition

For each value in arr1, compare it with every value in arr2. If any difference is at most d, it is not counted.

## Approach

Use nested loops and a validity flag. Count an arr1 value only when no arr2 value violates the distance condition.

## Algorithm

1. Initialize count to zero.
2. Iterate through arr1.
3. Compare the current value with every value in arr2.
4. If an absolute difference is at most d, mark it invalid and stop checking.
5. Increment count if valid.
6. Return count.

## Example

Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2

Output: 2

## Key Learning

An element qualifies only when its absolute difference from every element in the other array is greater than d.

## Time Complexity

O(n * m), where n and m are the array lengths.

## Space Complexity

O(1) auxiliary space.

## Language

Java
