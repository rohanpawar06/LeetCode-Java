# 2914. Minimum Number of Changes to Make Binary String Beautiful

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/

**Tags:** String, Greedy

## Problem Statement

You are given a binary string `s` of even length. A string is beautiful if it can be divided into substrings of length 2, where every substring contains two identical characters. Return the minimum number of changes needed to make the string beautiful. In one change, you can replace a character with either `0` or `1`.

## Intuition

Since every beautiful substring must contain exactly two equal characters, we can divide the string into independent adjacent pairs: (0, 1), (2, 3), and so on. If both characters in a pair are different, changing either one makes that pair valid. Therefore, each mismatched pair requires exactly one change.

## Approach

- Traverse the string two characters at a time.
- Compare the characters at indices `i` and `i + 1`.
- If they are different, increment the change counter.
- Return the total number of mismatched pairs.

## Algorithm

1. Initialize `changes = 0`.
2. Iterate through the string with a step of 2.
3. For each pair, check whether the two characters are different.
4. If they are different, increment `changes`.
5. Return `changes`.

## Example

**Input:** `s = "1001"`

**Pairs:** `10`, `01`

Both pairs contain different characters, so each pair needs one change.

**Output:** `2`

## Key Learning

When a problem requires fixed-size, non-overlapping groups, process each group independently. Here, every mismatched pair contributes exactly one required change.

## Complexity Analysis

- **Time Complexity:** O(n), where n is the length of the string. Each character is visited as part of one pair.
- **Space Complexity:** O(1), as only a counter is used.

## Language

Java
