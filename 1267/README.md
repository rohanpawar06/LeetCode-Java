# 1267. Count Servers that Communicate

- **Difficulty:** Medium
- **Problem:** [LeetCode 1267](https://leetcode.com/problems/count-servers-that-communicate/)
- **Tags:** Array, Matrix

## Problem Statement
Given an m × n binary grid, count the servers that can communicate with at least one other server. Two servers communicate if they are in the same row or column.

## Intuition
A server communicates when its row or column contains more than one server. Count the servers in each row and column first, then inspect each server.

## Approach
Use two arrays to store the number of servers in every row and column. In a second traversal, count a server if its row count or column count is greater than one.

## Algorithm
1. Create row and column count arrays.
2. Traverse the grid and count every server.
3. Traverse again and count servers whose row or column has multiple servers.
4. Return the count.

## Example
**Input:** grid = [[1,0],[0,1]]  
**Output:** 0

**Input:** grid = [[1,0],[1,1]]  
**Output:** 3

## Key Learning
Precomputing row and column frequencies avoids repeatedly scanning the matrix for each server.

## Complexity
- **Time:** O(m × n)
- **Space:** O(m + n)

## Language
Java
