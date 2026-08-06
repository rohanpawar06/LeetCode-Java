# 3345. Smallest Divisible Digit Product I

## 🟢 Difficulty

Easy

---

## 🔗 Problem Link

https://leetcode.com/problems/smallest-divisible-digit-product-i/

---

## 🏷️ Tags

- Math
- Enumeration
- Simulation

---

## 📖 Problem Statement

Given two integers `n` and `t`, find the smallest integer greater than or equal to `n` such that the product of its digits is divisible by `t`.

Return that integer.

---

## 💡 Intuition

Start checking numbers from `n`.

For each number:

- Calculate the product of its digits.
- If the product is divisible by `t`, return that number.
- Otherwise, continue with the next integer.

Since every 10 consecutive numbers contain a number ending in `0` (whose digit product is `0`), a valid answer is guaranteed to appear quickly. :contentReference[oaicite:0]{index=0}

---

## 🚀 Approach

1. Start from `n`.
2. Compute the product of its digits.
3. Check if the product is divisible by `t`.
4. If yes, return the current number.
5. Otherwise, increment the number and repeat.

---

## ✅ Algorithm

1. Initialize `num = n`.
2. Repeat:
   - Find the product of all digits of `num`.
   - If `product % t == 0`, return `num`.
   - Otherwise, increment `num`.
3. Continue until a valid number is found.

---

## ⏱️ Time Complexity

**O(k × d)**

- `k` = Number of integers checked.
- `d` = Number of digits in each integer.

Since `n ≤ 100`, this is efficient for the given constraints. :contentReference[oaicite:1]{index=1}

---

## 💾 Space Complexity

**O(1)**

---

## 📌 Example

### Input

```text
n = 15
t = 3
```

### Output

```text
16
```

### Explanation

```
15 → 1 × 5 = 5 (Not divisible by 3)

16 → 1 × 6 = 6 (Divisible by 3)
```

Therefore, the answer is **16**.

---

## 🧠 Key Learning

- Enumeration (Brute Force)
- Digit Manipulation
- Product of Digits
- Simulation

---

## 💻 Language

Java