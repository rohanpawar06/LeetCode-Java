# 2911. Minimum Changes to Make K Semi-palindromes

- **Difficulty:** Hard
- **Problem:** [LeetCode 2911](https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/)
- **Tags:** String, Dynamic Programming, Number Theory
- **Language:** Java

## Problem Statement

Given a string `s` and an integer `k`, divide the string into exactly `k` non-empty contiguous substrings. Each substring must have length at least two and be a semi-palindrome. A semi-palindrome can be formed by choosing a proper divisor `d` of its length, splitting its characters into `d` sequences by positions modulo `d`, and making every sequence a palindrome. Return the minimum number of character replacements needed to make all `k` substrings semi-palindromes.

## Intuition

Separate the problem into two parts: precompute the minimum changes needed to make every substring semi-palindromic, then use partition DP to choose exactly `k` valid substrings with minimum total cost.

## Approach

- For each substring length and starting index, enumerate every proper divisor `d` of that length.
- For each divisor, compare mirrored characters in each of the `d` residue-class sequences. Each mismatch requires one replacement.
- Store the minimum mismatch count as the substring's semi-palindrome conversion cost.
- Define `dp[parts][end]` as the minimum cost to partition the first `end` characters into exactly `parts` valid semi-palindromes.
- Try every valid previous cut, ensuring each new part has length at least two.

## Algorithm

1. Build `cost[left][right]` for all substrings of length at least two.
2. For each candidate divisor `d`, split the substring into `d` interleaved sequences and count mirrored mismatches.
3. Initialize the DP table to infinity and set `dp[0][0] = 0`.
4. For each part count and ending position, try all previous cut positions that leave at least two characters in the final part.
5. Update the DP with the previous partition cost plus the precomputed substring cost.
6. Return `dp[k][n]`.

## Example

**Input:** `s = "abcac", k = 2`

**Output:** `1`

One optimal split is `"ab"` and `"cac"`. The second substring is already a palindrome, and one replacement makes `"ab"` a palindrome.

## Key Learning

When a string partition problem has a costly property check, precompute the cost for every substring and then use dynamic programming over partition boundaries.

## Complexity

- **Time:** `O(n^3 * τ(n) + k * n^2)`, where `τ(n)` is the maximum number of divisors considered for a substring length.
- **Space:** `O(n^2 + k * n)`.
