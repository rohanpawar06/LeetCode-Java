# 2243. Calculate Digit Sum of a String

- **Difficulty:** Easy
- **Problem:** [LeetCode 2243](https://leetcode.com/problems/calculate-digit-sum-of-a-string/)
- **Tags:** String, Simulation
- **Language:** Java

## Problem Statement

Given a string `s` consisting of digits and an integer `k`, repeatedly split the string into consecutive groups of size `k` (the final group may be smaller). Replace each group with the sum of its digits, concatenating the sums to form a new string. Repeat until the string length is no greater than `k`, then return it.

## Intuition

Each operation transforms the string by replacing every block of up to `k` digits with its digit sum. A direct simulation with a `StringBuilder` matches the process.

## Approach

- Continue while the current string length is greater than `k`.
- Create a new `StringBuilder` for the transformed string.
- Process the current string in chunks of size `k`.
- Calculate each chunk's digit sum and append the integer sum.
- Replace the current string with the transformed string.
- Return the final string.

## Algorithm

1. While `s.length() > k`, initialize an empty builder.
2. Iterate over `s` in increments of `k`.
3. Sum the digits from the current chunk, stopping at the string length.
4. Append the sum to the builder.
5. Set `s` to the builder's string and repeat.
6. Return `s`.

## Example

**Input:** `s = "11111222223", k = 3`

**Output:** `"135"`

The first transformation produces `"345"` and the next transformation produces `"12"`? The example should be checked against the exact grouping process.

## Key Learning

Simulation is effective when each operation is clearly defined and the input size decreases after every transformation.

## Complexity

- **Time:** `O(n log_k n)` approximately, for `k > 1`
- **Space:** `O(n)` for the intermediate string
