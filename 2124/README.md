# 2124. Check if All A's Appears Before All B's

**Difficulty:** Easy

**Problem Link:** https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/

**Tags:** String

## Problem Statement

Given a string `s` consisting only of characters `'a'` and `'b'`, return `true` if every `'a'` appears before every `'b'` in the string. Otherwise, return `false`.

## Intuition

The string is valid if no `'a'` appears after a `'b'`. Therefore, the only invalid adjacent pattern that needs to be checked is `"ba"`. If this substring exists, the string is invalid; otherwise, it is valid.

## Approach

- Use the built-in `contains()` method to check whether the string contains `"ba"`.
- Return the opposite of that result.

## Algorithm

1. Check whether `s` contains the substring `"ba"`.
2. If it does, return `false`.
3. Otherwise, return `true`.

## Example

**Input:** `s = "aaabbb"`

There is no `"ba"` substring, so all `'a'` characters appear before all `'b'` characters.

**Output:** `true`

**Input:** `s = "abab"`

The substring `"ba"` occurs, so an `'a'` appears after a `'b'`.

**Output:** `false`

## Key Learning

A global ordering condition can sometimes be verified by checking for one forbidden adjacent pattern.

## Complexity Analysis

- **Time Complexity:** O(n), where n is the length of the string.
- **Space Complexity:** O(1) auxiliary space.

## Language

Java
