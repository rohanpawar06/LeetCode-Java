# 1563. Stone Game V

## 🟠 Difficulty

Hard

---

## 🔗 Problem Link

https://leetcode.com/problems/stone-game-v/

---

## 🏷️ Tags

- Array
- Dynamic Programming
- Prefix Sum
- Game Theory
- Recursion
- Memoization

---

## 📖 Problem Statement

Alice has a row of stones represented by the array `stoneValue`.

On each turn, Alice divides the current row of stones into two non-empty parts.

Let:

- `leftSum` = sum of stones in the left part
- `rightSum` = sum of stones in the right part

The rules are:

- If `leftSum < rightSum`, Alice keeps the left part and gains `leftSum`.
- If `leftSum > rightSum`, Alice keeps the right part and gains `rightSum`.
- If `leftSum == rightSum`, Alice can choose either part.

The process continues until only one stone remains.

Return the maximum score Alice can achieve.

---

## 💡 Intuition

For every subarray, we can try all possible positions where it can be divided.

The same subarray may appear multiple times during recursion, so we use **Dynamic Programming with Memoization**.

We define:

`dp[left][right]`

as the maximum score Alice can obtain from the subarray between `left` and `right`.

We also use a **Prefix Sum** array to calculate the sum of any subarray efficiently.

---

## 🚀 Approach

1. Build a prefix sum array.
2. Create a 2D DP array.
3. Start with the complete array.
4. Try every possible split position.
5. Calculate `leftSum` and `rightSum`.
6. If `leftSum < rightSum`, continue with the left part.
7. If `leftSum > rightSum`, continue with the right part.
8. If both sums are equal, choose the side that gives the maximum score.
9. Store the result in `dp[left][right]`.
10. Return the maximum score.

---

## 🔍 Algorithm

1. Create the prefix sum array.
2. Call `solve(0, n - 1)`.
3. If `left == right`, return `0`.
4. For every possible `mid`:
   - Calculate `leftSum`.
   - Calculate `rightSum`.
5. If `leftSum < rightSum`:
   - Add `leftSum`.
   - Recursively solve the left part.
6. If `leftSum > rightSum`:
   - Add `rightSum`.
   - Recursively solve the right part.
7. If both sums are equal:
   - Try both sides.
   - Choose the maximum result.
8. Store the result in DP.
9. Return the result.

---

## 📌 Example

### Input

`stoneValue = [6, 2, 3, 4, 5, 5]`

Consider splitting the array as:

`[6, 2] | [3, 4, 5, 5]`

The sums are:

`leftSum = 8`

`rightSum = 17`

Since:

`8 < 17`

Alice keeps:

`[6, 2]`

and gains:

`8`

The game continues recursively on the remaining part.

The DP checks every possible split and stores the maximum score that can be obtained.

---

## 🧠 Key Learning

### Pattern: Dynamic Programming + Prefix Sum

The main pattern is:

`Subarray → Try Every Split → Compare Sums → Choose Valid Side → Recursion → Memoization`

The Prefix Sum array allows us to calculate a range sum in `O(1)` time.

For example:

`leftSum = prefix[mid + 1] - prefix[left]`

and:

`rightSum = prefix[right + 1] - prefix[mid + 1]`

This avoids calculating the same subarray sums repeatedly.

---

## ⏱️ Time Complexity

**O(n³)**

There are `O(n²)` possible DP states.

For every state, we may try up to `O(n)` split positions.

Therefore:

`O(n² × n) = O(n³)`

---

## 💾 Space Complexity

**O(n²)**

The DP table requires `O(n²)` space.

The prefix sum array requires `O(n)` additional space.

---

## 💻 Language

Java