# 3471. Find the Largest Almost Missing Integer

## 🟢 Difficulty

Easy

---

## 🔗 Problem Link

https://leetcode.com/problems/find-the-largest-almost-missing-integer/

---

## 🏷️ Tags

- Array
- HashMap
- Frequency Counting
- Sliding Window

---

## 📖 Problem Statement

You are given an integer array `nums` and an integer `k`.

An integer is called **almost missing** if it appears in exactly one subarray of length `k`.

Return the largest almost missing integer.

If no such integer exists, return `-1`.

---

## 💡 Intuition

We need to determine how many subarrays of length `k` contain each number.

A direct approach would be to generate every subarray and count the elements, but this is unnecessary.

There is an important observation:

- If `k == 1`, every element forms its own subarray, so we need the largest element that appears exactly once.
- If `k == nums.length`, there is only one subarray, so the answer is simply the maximum element.
- If `1 < k < n`, only the first and last elements can appear in exactly one subarray of length `k`.

We can use a frequency map to check whether a candidate occurs only once in the entire array.

---

## 🚀 Approach

1. Count the frequency of every element in `nums`.
2. If `k == 1`, find the largest element whose frequency is `1`.
3. If `k == nums.length`, return the maximum element.
4. Otherwise, check the first and last elements.
5. An element can be almost missing only if its total frequency is `1`.
6. Return the largest valid candidate.
7. If there is no valid candidate, return `-1`.

---

## 🔍 Algorithm

1. Create a `HashMap` to store the frequency of every element.
2. Traverse the array and update the frequency map.
3. If `k == 1`:
   - Traverse the array.
   - Find the largest element with frequency `1`.
4. If `k == n`:
   - Find and return the maximum element.
5. Otherwise:
   - Check `nums[0]`.
   - Check `nums[n - 1]`.
   - If their frequency is `1`, consider them as candidates.
6. Return the maximum candidate.

---

## 📌 Example

### Input

`nums = [3, 9, 2, 1, 7]`

`k = 3`

The subarrays of length `3` are:

`[3, 9, 2]`

`[9, 2, 1]`

`[2, 1, 7]`

The element `3` appears in exactly one subarray.

The element `7` also appears in exactly one subarray.

Therefore, the largest almost missing integer is:

`7`

### Output

`7`

---

## 🧠 Key Learning

### Pattern: Frequency Counting + Case Analysis

The important observation is to understand how many subarrays of size `k` can contain an element.

For `1 < k < n`, an element in the middle of the array appears in multiple length-`k` subarrays.

Only the first and last positions can belong to exactly one such subarray.

Therefore, we can reduce the problem to checking:

`k == 1`

`k == n`

`1 < k < n`

A `HashMap` helps us efficiently check whether a candidate appears exactly once in the complete array.

---

## ⏱️ Time Complexity

**O(n)**

We traverse the array a constant number of times.

---

## 💾 Space Complexity

**O(n)**

The frequency map can store up to `n` different elements.

---

## 💻 Language

Java