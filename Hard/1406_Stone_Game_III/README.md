# 1406. Stone Game III

- **Difficulty:** Hard
- **Language:** Java
- **Topic:** Dynamic Programming, Game Theory

## Problem Link

https://leetcode.com/problems/stone-game-iii/

## Approach

- Let `dp[i]` represent the maximum score difference (Current Player − Opponent) starting from index `i`.
- At every position, the player can take 1, 2, or 3 stones.
- Choose the move that maximizes the score difference.

## Time Complexity

- O(n)

## Space Complexity

- O(n)