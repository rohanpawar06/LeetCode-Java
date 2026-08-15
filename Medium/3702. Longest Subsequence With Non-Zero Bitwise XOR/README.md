# 3702. Longest Subsequence With Non-Zero Bitwise XOR

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/longest-subsequence-with-non-zero-bitwise-xor/

---

## 🏷️ Tags

- Array
- Bit Manipulation
- XOR
- Subsequence

---

## 📖 Problem Statement

Given an integer array `nums`, find the length of the longest subsequence whose bitwise XOR is not equal to `0`.

If no such subsequence exists, return `0`.

---

## 💡 Intuition

The first thing to check is the XOR of the entire array.

If the total XOR is non-zero, the entire array is already a valid subsequence, so the answer is `n`.

If the total XOR is zero, we cannot use the complete array.

However, if there is at least one non-zero element, removing one non-zero element makes the XOR of the remaining elements non-zero.

Therefore, the answer becomes `n - 1`.

The only case where no valid subsequence exists is when every element is `0`.

---

## 🚀 Approach

1. Calculate the XOR of all elements.
2. Count the number of zero elements.
3. If the total XOR is non-zero, return `nums.length`.
4. If all elements are zero, return `0`.
5. Otherwise, return `nums.length - 1`.

---

## 🔍 Algorithm

```text
Calculate XOR of all elements
Count zero elements

If XOR != 0:
    return n

If all elements are zero:
    return 0

Otherwise:
    return n - 1

## 💻 Language

Java