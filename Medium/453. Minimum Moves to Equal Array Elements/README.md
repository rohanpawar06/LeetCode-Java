# LeetCode 453 - Minimum Moves to Equal Array Elements

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/minimum-moves-to-equal-array-elements/

**Tags:** Array, Math

## Problem Statement

Given an integer array, find the fewest moves needed to make all values equal. In one move, increment exactly n - 1 elements by 1.

## Intuition

Incrementing n - 1 values by 1 has the same effect on the values' differences as decrementing the one value left out by 1. Repeating this idea means the best final value is the array's minimum. Each number needs to be reduced to that minimum, so the answer is the sum of all differences from the minimum.

## Approach

1. Find the smallest value in the array.
2. Add num - minimum for every value.
3. Return that sum. Use a long accumulator so intermediate arithmetic is safe; the problem guarantees the final answer fits in an int.

## Example

**Input:** nums = [1, 2, 3]

The differences from the minimum value 1 are 0, 1, and 2. Their sum is 3.

**Output:** 3

## Key Learning

An operation that changes all but one element can sometimes be reframed as changing only the excluded element, while tracking relative differences.

## Java Solution

```java
class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
        }

        long moves = 0;
        for (int num : nums) {
            moves += (long) num - min;
        }

        return (int) moves;
    }
}
```

## Complexity

- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

**Language:** Java
