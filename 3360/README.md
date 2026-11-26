# 3360. Stone Removal Game

**Difficulty:** Easy

**Problem Link:** https://leetcode.com/problems/stone-removal-game/

**Tags:** Math, Simulation, Game Theory

## Problem Statement

Alice and Bob play a game starting with `n` stones. Alice must remove 10 stones on her first turn. On each subsequent turn, the player must remove one fewer stone than the previous turn. Alice and Bob alternate turns. A player who cannot remove the required number of stones loses. Return `true` if Alice wins, otherwise return `false`.

## Intuition

The number of stones removed decreases by one after every successful turn: 10, 9, 8, 7, and so on. We can simulate these turns directly. Keep track of whose turn it is and stop when the remaining stones are fewer than the required removal.

## Approach

- Start with the given number of stones.
- Set the first removal amount to 10.
- Track whether it is Alice's turn.
- While enough stones remain, remove the required amount, decrease the next removal amount, and switch turns.
- When the loop stops, the current player cannot move. Alice wins exactly when it is Bob's turn.

## Algorithm

1. Initialize `stones = n`, `remove = 10`, and `aliceTurn = true`.
2. While `stones >= remove`:
   - Subtract `remove` from `stones`.
   - Decrease `remove` by 1.
   - Switch the current player.
3. Return `!aliceTurn`.

## Example

**Input:** `n = 12`

- Alice removes 10 stones. Remaining: 2.
- Bob must remove 9 stones, but only 2 remain.
- Bob cannot make a move, so Alice wins.

**Output:** `true`

## Key Learning

A turn-based game with a predictable sequence of moves can be solved using straightforward simulation and a boolean variable to track the current player.

## Complexity Analysis

- **Time Complexity:** O(1), because at most 10 turns can be completed.
- **Space Complexity:** O(1), as only a few variables are used.

## Language

Java
