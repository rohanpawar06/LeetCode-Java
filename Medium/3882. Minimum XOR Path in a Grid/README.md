# LeetCode 3882 - Minimum XOR Path in a Grid

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/minimum-xor-path-in-a-grid/

**Tags:** Array, Dynamic Programming, Matrix, Bit Manipulation

## Problem Statement

Given an integer grid, move from the top-left cell to the bottom-right cell using only right or down moves. A path's cost is the XOR of all values along it, including both endpoints. Return the smallest cost among all valid paths.

## Intuition

We cannot keep only the smallest XOR reaching each cell. A later XOR can make a numerically larger prefix produce a smaller final result. Instead, track every XOR value that can reach each cell.

## Approach

1. Each cell stores which XOR values are reachable by a path ending there.
2. The start cell is reachable with XOR equal to its own value.
3. For every other cell, consider paths from above and from the left.
4. If the current cell has value `v`, a desired XOR `x` at this cell requires the previous XOR `x ^ v`.
5. Scan the destination's reachable XOR values from smallest to largest and return the first one.

Every grid value is at most `1023`, so all path XOR values fit in at most 10 bits. The number of states is therefore at most `1024`.

## Algorithm

```text
Find the largest grid value.
Set the XOR state count to the smallest power of two greater than that value.
Create reachable[m][n][xorStates].
Mark grid[0][0] as reachable with its own value.
For each other cell:
    For each possible XOR:
        previousXor = xor ^ currentCellValue
        Mark it reachable if previousXor reaches from above or from the left.
Return the smallest reachable XOR at the bottom-right cell.
```

## Java Solution

```java
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int maxValue = 0;
        for (int[] row : grid) {
            for (int value : row) {
                maxValue = Math.max(maxValue, value);
            }
        }

        int xorStates = 1;
        while (xorStates <= maxValue) {
            xorStates <<= 1;
        }

        boolean[][][] reachable = new boolean[m][n][xorStates];
        reachable[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int value = grid[i][j];
                for (int xor = 0; xor < xorStates; xor++) {
                    int previousXor = xor ^ value;

                    if ((i > 0 && reachable[i - 1][j][previousXor])
                            || (j > 0 && reachable[i][j - 1][previousXor])) {
                        reachable[i][j][xor] = true;
                    }
                }
            }
        }

        for (int xor = 0; xor < xorStates; xor++) {
            if (reachable[m - 1][n - 1][xor]) {
                return xor;
            }
        }

        return -1;
    }
}
```

## Example

**Input:** `grid = [[1, 2], [3, 4]]`

**Output:** `6`

The two path XOR values are `1 ^ 2 ^ 4 = 7` and `1 ^ 3 ^ 4 = 6`.

## Key Learning

For XOR path problems, numeric order is not preserved by XOR. Keep all reachable XOR states instead of only a local minimum.

## Complexity

Let `R` be the number of XOR states, where `R <= 1024`.

- **Time Complexity:** `O(m × n × R)`
- **Space Complexity:** `O(m × n × R)`

**Language:** Java