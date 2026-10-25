# 1254. Number of Closed Islands

- **Difficulty:** Medium
- **Problem Link:** https://leetcode.com/problems/number-of-closed-islands/
- **Tags:** Array, Depth-First Search, Breadth-First Search, Union Find, Matrix

## Problem Statement

Given a 2D grid consisting of `0` (land) and `1` (water), a closed island is a group of land cells connected horizontally or vertically that is completely surrounded by water. Return the number of closed islands. Land touching any boundary of the grid is not closed.

## Intuition

Explore each unvisited land component. If any cell in that component touches the grid boundary, the component is not closed. Otherwise, it contributes one closed island.

## Approach

Use iterative depth-first search (DFS) with a stack. Whenever an unvisited land cell is found, explore its entire component, marking cells as visited by changing `0` to `1`. Track whether any visited cell lies on the boundary. Count the component only if no cell touches the boundary.

## Algorithm

1. Iterate through every grid cell.
2. When an unvisited land cell (`0`) is found, start a DFS stack and mark it visited.
3. During traversal, mark the component as open if a cell lies on the grid boundary.
4. Visit all adjacent unvisited land cells.
5. If the component never touches the boundary, increment the answer.
6. Return the answer after scanning the grid.

## Example

**Input:** `grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[0,0,0,0,0,1,1,0],[1,1,1,1,1,1,1,0]]`

**Output:** `2`

## Key Learning

A connected land component is closed only when none of its cells touches the outer boundary. Iterative DFS avoids recursion-depth issues on large grids.

## Complexity

- **Time Complexity:** `O(m * n)` — each cell is visited at most once.
- **Space Complexity:** `O(m * n)` in the worst case for the DFS stack.

## Language

Java
