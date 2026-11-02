# LeetCode 462 - Minimum Moves to Equal Array Elements II

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/

**Tags:** Array, Math, Sorting

## Problem Statement

Given an integer array, find the minimum number of moves needed to make all elements equal. In each move, you may increment or decrement one element by 1.

## Intuition

For a chosen target, the total moves are the sum of the absolute differences from every array value to that target. A median minimizes this sum: values on either side can be paired, and moving the target away from the median cannot reduce the total distance.

After sorting, choose the middle element as the target. For an even-length array, either middle element is optimal.

## Approach

1. Sort the array.
2. Choose nums[nums.length / 2] as the target.
3. Sum the absolute difference between each value and the target.
4. Return the total. Use a long accumulator before casting to int, as the problem guarantees the answer fits in a 32-bit integer.

## Example

**Input:** nums = [1, 2, 3]

The median is 2. The required moves are |1 - 2| + |2 - 2| + |3 - 2| = 2.

**Output:** 2

## Key Learning

When minimizing the sum of absolute distances to a single target, a median is optimal.

## Java Solution

```java
import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);

        int median = nums[nums.length / 2];
        long moves = 0;

        for (int num : nums) {
            moves += Math.abs((long) num - median);
        }

        return (int) moves;
    }
}
```

## Complexity

- **Time Complexity:** O(n log n)
- **Space Complexity:** O(log n) auxiliary stack for sorting

**Language:** Java
