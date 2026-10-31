# LeetCode 3665 - Twisted Mirror Path Count

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/twisted-mirror-path-count/

**Tags:** Dynamic Programming, Grid, Simulation

## Problem Statement

A robot starts at the top-left of a binary grid and wants to reach the bottom-right, moving right or down. A 0 is an empty cell. A 1 is a mirror: trying to move right into it redirects the robot down past that cell, and trying to move down into it redirects the robot right past that cell. Consecutive mirrors keep redirecting the robot. A path is invalid if a reflection moves outside the grid.

Return the number of valid paths modulo 1,000,000,007.

## Intuition

The direction used to reach a cell matters when the cell is a mirror. Therefore, keep two counts per cell: paths arriving from the left while moving right, and paths arriving from above while moving down.

At an empty cell, every arriving path can continue in either direction. At a mirror, paths arriving from the left are redirected down, and paths arriving from above are redirected right. Processing the grid row by row and left to right handles chains of mirrors because every reflection moves to a later cell in that order.

## Approach

1. Set the starting cell's initial path count to 1.
2. Visit each cell in row-major order.
3. For an empty cell, add the total incoming paths to its right and lower neighbors when those neighbors are inside the grid.
4. For a mirror, redirect each incoming direction to the reflected neighbor. Ignore a reflection that leaves the grid.
5. Return the sum of both direction counts at the bottom-right cell, modulo 1,000,000,007.

## Example

**Input:** grid = [[0,1,0],[0,0,1],[1,0,0]]

The mirror at (0, 1) redirects a rightward path down. Paths that reach a mirror can be redirected again, while reflections outside the grid are discarded.

**Output:** 5

## Key Learning

When a grid transition depends on how a cell was entered, store the incoming direction as part of the DP state. This lets each mirror apply the correct deterministic redirection.

## Java Solution

```java
class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Direction 0: arriving from the left (moving right).
        // Direction 1: arriving from above (moving down).
        int[][][] dp = new int[m][n][2];
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int fromRight = dp[i][j][0];
                int fromDown = dp[i][j][1];

                if (grid[i][j] == 0) {
                    int total = (fromRight + fromDown) % MOD;

                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                                (dp[i][j + 1][0] + total) % MOD;
                    }
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                                (dp[i + 1][j][1] + total) % MOD;
                    }
                } else {
                    // Moving right into a mirror redirects down.
                    if (fromRight > 0 && i + 1 < m) {
                        dp[i + 1][j][1] =
                                (dp[i + 1][j][1] + fromRight) % MOD;
                    }

                    // Moving down into a mirror redirects right.
                    if (fromDown > 0 && j + 1 < n) {
                        dp[i][j + 1][0] =
                                (dp[i][j + 1][0] + fromDown) % MOD;
                    }
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}
```

## Complexity

- **Time Complexity:** O(m * n)
- **Space Complexity:** O(m * n)

**Language:** Java
