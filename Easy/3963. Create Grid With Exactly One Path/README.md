# LeetCode 3963 - Create Grid With Exactly One Path

**Difficulty:** Easy

**Problem Link:** https://leetcode.com/problems/create-grid-with-exactly-one-path/

**Tags:** Construction

## Problem Statement

Given the number of rows `m` and columns `n`, build an `m x n` grid using `.` for free cells and `#` for blocked cells. Movement is allowed only to the right or downward. Return a grid with exactly one path from the top-left cell to the bottom-right cell.

## Intuition

Leave one clear route from the start to the destination and block every other cell. A simple route goes across the first row, then down the last column.

## Approach

1. Fill the entire grid with `#`.
2. Set every cell in the first row to `.`.
3. Set every cell in the last column to `.`.
4. Convert each character row into a string and return the string array.

The first row leads to the last column, and the last column leads to the destination. All other cells below the first row are blocked, so there is no alternate route.

## Algorithm

```text
Create an m x n character grid.
Fill every row with '#'.
Fill the first row with '.'.
For each row, set the last column to '.'.
Convert each row to a string and return the resulting array.
```

## Java Solution

```java
import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        Arrays.fill(grid[0], '.');

        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }

        return result;
    }
}
```

## Example

**Input:** `m = 2, n = 3`

**Output:** `["...", "##."]`

The only path moves right across the first row, then down the last column.

## Key Learning

For a grid path construction problem, opening one deliberate route and blocking all other cells guarantees the desired path count.

## Complexity

- **Time Complexity:** `O(m × n)`
- **Space Complexity:** `O(m × n)`

**Language:** Java