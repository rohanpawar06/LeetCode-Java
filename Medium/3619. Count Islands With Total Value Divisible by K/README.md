# LeetCode 3619 - Count Islands With Total Value Divisible by K

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/

**Tags:** Array, Depth-First Search, Breadth-First Search, Matrix, Connected Components

## Problem Statement

Given an integer grid and an integer `k`, an island is a group of connected non-zero cells connected horizontally or vertically. Count the islands whose total cell value is divisible by `k`.

## Intuition

Explore each unvisited non-zero cell and use iterative DFS to visit every cell in that island. Add the values to the island sum, then check whether the sum is divisible by `k`.

## Approach

1. Traverse every cell in the grid.
2. When an unvisited non-zero cell is found, start DFS with a stack.
3. Mark cells as visited when adding them to the stack so each cell is processed once.
4. Sum all values in the connected island.
5. Increase the answer if the sum modulo `k` is zero.

## Java Solution

    import java.util.*;

    class Solution {
        public int countIslands(int[][] grid, int k) {
            int m = grid.length;
            int n = grid[0].length;
            int[] dirs = {-1, 0, 1, 0, -1};
            int count = 0;
            Deque<int[]> stack = new ArrayDeque<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }

                    long sum = 0;
                    grid[i][j] = -grid[i][j];
                    stack.push(new int[]{i, j});

                    while (!stack.isEmpty()) {
                        int[] cell = stack.pop();
                        int row = cell[0];
                        int col = cell[1];
                        sum -= grid[row][col];

                        for (int d = 0; d < 4; d++) {
                            int nr = row + dirs[d];
                            int nc = col + dirs[d + 1];

                            if (nr >= 0 && nr < m && nc >= 0 && nc < n
                                    && grid[nr][nc] > 0) {
                                grid[nr][nc] = -grid[nr][nc];
                                stack.push(new int[]{nr, nc});
                            }
                        }
                    }

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

## Key Learning

Use DFS or BFS to find connected components in a grid. Compute each component's sum and check divisibility.

## Complexity

- **Time Complexity:** `O(m × n)`
- **Space Complexity:** `O(m × n)` in the worst case for the DFS stack.

**Language:** Java